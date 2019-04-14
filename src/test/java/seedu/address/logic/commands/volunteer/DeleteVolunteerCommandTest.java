//@@swalahlah
package seedu.address.logic.commands.volunteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.showVolunteerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_VOLUNTEER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_VOLUNTEER;
import static seedu.address.testutil.volunteer.TypicalVolunteers.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.volunteer.Volunteer;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteVolunteerCommand}.
 */
public class DeleteVolunteerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Volunteer volunteerToDelete = model.getFilteredVolunteerList().get(INDEX_FIRST_VOLUNTEER.getZeroBased());
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER, false);

        String expectedMessage = String.format
                (DeleteVolunteerCommand.MESSAGE_DELETE_VOLUNTEER_SUCCESS, volunteerToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteVolunteer(volunteerToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteVolunteerCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredVolunteerList().size() + 1);
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(outOfBoundIndex, false);

        assertCommandFailure(deleteVolunteerCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showVolunteerAtIndex(model, INDEX_FIRST_VOLUNTEER);

        Volunteer volunteerToDelete = model.getFilteredVolunteerList().get(INDEX_FIRST_VOLUNTEER.getZeroBased());
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER, false);

        String expectedMessage = String.format
                (DeleteVolunteerCommand.MESSAGE_DELETE_VOLUNTEER_SUCCESS, volunteerToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteVolunteer(volunteerToDelete);
        expectedModel.commitAddressBook();
        showNoVolunteer(expectedModel);

        assertCommandSuccess(deleteVolunteerCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showVolunteerAtIndex(model, INDEX_FIRST_VOLUNTEER);

        Index outOfBoundIndex = INDEX_SECOND_VOLUNTEER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getVolunteerList().size());

        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(outOfBoundIndex, false);

        assertCommandFailure(deleteVolunteerCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Volunteer volunteerToDelete = model.getFilteredVolunteerList().get(INDEX_FIRST_VOLUNTEER.getZeroBased());
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER, false);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteVolunteer(volunteerToDelete);
        expectedModel.commitAddressBook();

        // delete -> first volunteer deleted
        deleteVolunteerCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered volunteer list to show all volunteers
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first volunteer deleted again
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredVolunteerList().size() + 1);
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(outOfBoundIndex, false);

        // execution failed -> address book state not added into model
        assertCommandFailure(deleteVolunteerCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Volunteer} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted volunteer in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the volunteer object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameVolunteerDeleted() throws Exception {
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER, false);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showVolunteerAtIndex(model, INDEX_SECOND_VOLUNTEER);
        Volunteer volunteerToDelete = model.getFilteredVolunteerList().get(INDEX_FIRST_VOLUNTEER.getZeroBased());
        expectedModel.deleteVolunteer(volunteerToDelete);
        expectedModel.commitAddressBook();

        // delete -> deletes second volunteer in unfiltered volunteer list / first volunteer in filtered volunteer list
        deleteVolunteerCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered volunteer list to show all volunteers
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(volunteerToDelete, model.getFilteredVolunteerList().get(INDEX_FIRST_VOLUNTEER.getZeroBased()));
        // redo -> deletes same second volunteer in unfiltered volunteer list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteVolunteerCommand deleteFirstCommand = new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER, false);
        DeleteVolunteerCommand deleteSecondCommand = new DeleteVolunteerCommand(INDEX_SECOND_VOLUNTEER, false);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteVolunteerCommand deleteFirstCommandCopy = new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER, false);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different volunteer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoVolunteer(Model model) {
        model.updateFilteredVolunteerList(p -> false);

        assertTrue(model.getFilteredVolunteerList().isEmpty());
    }
}
