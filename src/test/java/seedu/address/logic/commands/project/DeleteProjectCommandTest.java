package seedu.address.logic.commands.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.project.ProjectCommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.project.TypicalProjects.getTypicalAddressBook;


import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteProjectCommand}.
 */
public class DeleteProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS,
            projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteProjectCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);

        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS,
            projectToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        expectedModel.commitAddressBook();
        showNoProject(expectedModel);

        assertCommandSuccess(deleteProjectCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getProjectList().size());

        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        expectedModel.commitAddressBook();

        // delete -> first project deleted
        deleteProjectCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered project list to show all beneficiaries
        expectedModel.undoAddressBook();
        CommandTestUtil.assertCommandSuccess(
            new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first project deleted again
        expectedModel.redoAddressBook();
        CommandTestUtil.assertCommandSuccess(
            new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        // execution failed -> address book state not added into model
        assertCommandFailure(deleteProjectCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Project} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted project in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the project object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameProjectDeleted() throws Exception {
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showProjectAtIndex(model, INDEX_SECOND);
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        expectedModel.deleteProject(projectToDelete);
        expectedModel.commitAddressBook();

        // delete -> deletes second project in unfiltered project list /
        // first project in filtered project list
        deleteProjectCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered project list to show all beneficiaries
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(projectToDelete, model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()));
        // redo -> deletes same second project in unfiltered project list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteProjectFirstCommand = new DeleteProjectCommand(INDEX_FIRST);
        DeleteProjectCommand deleteSecondCommand = new DeleteProjectCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteProjectFirstCommand.equals(deleteProjectFirstCommand));

        // same values -> returns true
        DeleteProjectCommand deleteProjectFirstCommandCopy = new DeleteProjectCommand(INDEX_FIRST);
        assertTrue(deleteProjectFirstCommand.equals(deleteProjectFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteProjectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteProjectFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(deleteProjectFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}
