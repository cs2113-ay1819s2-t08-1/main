//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.showBeneficiaryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.ATTACHED_PROJECT_B1;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.ATTACHED_PROJECT_B2;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.BENEFICIARY_B;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.getAandBBeneficiaries;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.getAddressBookForBeneficiarySyncTest;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.getProjectA2B;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.getTypicalAddressBook;

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
import seedu.address.model.beneficiary.Beneficiary;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteBeneficiaryCommand}.
 */
public class DeleteBeneficiaryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Beneficiary beneficiaryToDelete = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(INDEX_FIRST,
                false);

        String expectedMessage = String.format(DeleteBeneficiaryCommand.MESSAGE_DELETE_BENEFICIARY_SUCCESS,
                beneficiaryToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteBeneficiary(beneficiaryToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBeneficiaryList().size() + 1);
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(
                outOfBoundIndex, false);

        assertCommandFailure(deleteBeneficiaryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);

        Beneficiary beneficiaryToDelete = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(
                INDEX_FIRST, false);

        String expectedMessage = String.format(DeleteBeneficiaryCommand.MESSAGE_DELETE_BENEFICIARY_SUCCESS,
                beneficiaryToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteBeneficiary(beneficiaryToDelete);
        expectedModel.commitAddressBook();
        showNoBeneficiary(expectedModel);

        assertCommandSuccess(deleteBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBeneficiaryList().size());

        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(
                outOfBoundIndex, false);

        assertCommandFailure(deleteBeneficiaryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Beneficiary beneficiaryToDelete = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(
                INDEX_FIRST, false);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteBeneficiary(beneficiaryToDelete);
        expectedModel.commitAddressBook();

        // delete -> first beneficiary deleted
        deleteBeneficiaryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered beneficiary list to show all beneficiaries
        expectedModel.undoAddressBook();
        CommandTestUtil.assertCommandSuccess(
                new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first beneficiary deleted again
        expectedModel.redoAddressBook();
        CommandTestUtil.assertCommandSuccess(
                new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBeneficiaryList().size() + 1);
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(
                outOfBoundIndex, false);

        // execution failed -> address book state not added into model
        assertCommandFailure(deleteBeneficiaryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Beneficiary} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted beneficiary in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the beneficiary object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameBeneficiaryDeleted() throws Exception {
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(INDEX_FIRST,
                false);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showBeneficiaryAtIndex(model, INDEX_SECOND);
        Beneficiary beneficiaryToDelete = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        expectedModel.deleteBeneficiary(beneficiaryToDelete);
        expectedModel.commitAddressBook();

        // delete -> deletes second beneficiary in unfiltered beneficiary list /
        // first beneficiary in filtered beneficiary list
        deleteBeneficiaryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered beneficiary list to show all beneficiaries
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(beneficiaryToDelete, model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased()));
        // redo -> deletes same second beneficiary in unfiltered beneficiary list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteBeneficiaryCommand deleteBeneficiaryFirstCommand = new DeleteBeneficiaryCommand(INDEX_FIRST,
                false);
        DeleteBeneficiaryCommand deleteSecondCommand = new DeleteBeneficiaryCommand(INDEX_SECOND,
                false);

        // same object -> returns true
        assertTrue(deleteBeneficiaryFirstCommand.equals(deleteBeneficiaryFirstCommand));

        // same values -> returns true
        DeleteBeneficiaryCommand deleteBeneficiaryFirstCommandCopy = new DeleteBeneficiaryCommand(INDEX_FIRST,
                false);
        assertTrue(deleteBeneficiaryFirstCommand.equals(deleteBeneficiaryFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteBeneficiaryFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteBeneficiaryFirstCommand.equals(null));

        // different beneficiary -> returns false
        assertFalse(deleteBeneficiaryFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void execute_invalidDelete_haveAttachedProjects() throws CommandException {
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(INDEX_FIRST,
                false);
        model.setAddressBook(getAddressBookForBeneficiarySyncTest(getAandBBeneficiaries(), getProjectA2B()));
        Beneficiary beneficiaryToDelete = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        assertCommandFailure(deleteBeneficiaryCommand, model, commandHistory,
                String.format(DeleteBeneficiaryCommand.MESSAGE_BENEFICIARY_HAS_PROJECTS_ATTACHED,
                        beneficiaryToDelete.getName(),
                        beneficiaryToDelete.getAttachedProjectLists()));
    }

    @Test
    public void execute_validDeleteHardDelete_success() {
        DeleteBeneficiaryCommand deleteBeneficiaryCommand = new DeleteBeneficiaryCommand(INDEX_FIRST,
                true);
        Model model = new ModelManager(getAddressBookForBeneficiarySyncTest(getAandBBeneficiaries(), getProjectA2B()),
                new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        CommandHistory commandHistory = new CommandHistory();
        expectedModel.deleteBeneficiary(BENEFICIARY_B);
        expectedModel.deleteProject(ATTACHED_PROJECT_B1);
        expectedModel.deleteProject(ATTACHED_PROJECT_B2);
        expectedModel.commitAddressBook();
        String expectedMessage = String.format(
                DeleteBeneficiaryCommand.MESSAGE_DELETE_BENEFICIARY_SUCCESS, BENEFICIARY_B);
        assertCommandSuccess(deleteBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBeneficiary(Model model) {
        model.updateFilteredBeneficiaryList(p -> false);

        assertTrue(model.getFilteredBeneficiaryList().isEmpty());
    }
}
