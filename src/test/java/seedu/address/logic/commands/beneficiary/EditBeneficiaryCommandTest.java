//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.showBeneficiaryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.ATTACHED_PROJECT_A;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.ATTACH_TO_A_TITLE;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.BENEFICIARY_A;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.BENEFICIARY_A_EDITED;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.BENEFICIARY_A_EDITED_NAME;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.buildProjectStub;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.getAandBBeneficiaries;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.getAddressBookForBeneficiarySyncTest;
import static seedu.address.testutil.beneficiary.BeneficiariesSyncProjects.getProjectA2B;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand.EditBeneficiaryDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.testutil.beneficiary.BeneficiaryBuilder;
import seedu.address.testutil.beneficiary.EditBeneficiaryDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditBeneficiaryCommand.
 */
public class EditBeneficiaryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Beneficiary editedBeneficiary = new BeneficiaryBuilder().build();
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder(editedBeneficiary).build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditBeneficiaryCommand.MESSAGE_EDIT_BENEFICIARY_SUCCESS,
                editedBeneficiary);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBeneficiary(model.getFilteredBeneficiaryList().get(0), editedBeneficiary);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBeneficiary = Index.fromOneBased(model.getFilteredBeneficiaryList().size());
        Beneficiary lastBeneficiary = model.getFilteredBeneficiaryList().get(indexLastBeneficiary.getZeroBased());

        BeneficiaryBuilder beneficiaryInList = new BeneficiaryBuilder(lastBeneficiary);
        Beneficiary editedBeneficiary = beneficiaryInList.withName(VALID_NAME_BABES).withPhone(VALID_PHONE_BABES)
                .build();

        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_BABES)
                .withPhone(VALID_PHONE_BABES).build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(indexLastBeneficiary, descriptor);

        String expectedMessage = String.format(EditBeneficiaryCommand.MESSAGE_EDIT_BENEFICIARY_SUCCESS,
                editedBeneficiary);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBeneficiary(lastBeneficiary, editedBeneficiary);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(
                INDEX_FIRST, new EditBeneficiaryDescriptor());
        Beneficiary editedBeneficiary = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditBeneficiaryCommand.MESSAGE_EDIT_BENEFICIARY_SUCCESS,
                editedBeneficiary);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);

        Beneficiary beneficiaryInFilteredList = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        Beneficiary editedBeneficiary = new BeneficiaryBuilder(beneficiaryInFilteredList).withName(VALID_NAME_BABES)
                .build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(INDEX_FIRST,
                new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_BABES).build());

        String expectedMessage = String.format(EditBeneficiaryCommand.MESSAGE_EDIT_BENEFICIARY_SUCCESS,
                editedBeneficiary);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBeneficiary(model.getFilteredBeneficiaryList().get(0), editedBeneficiary);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBeneficiaryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBeneficiaryList().size() + 1);
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_BABES)
                .build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBeneficiaryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBeneficiaryIndexFilteredList_failure() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBeneficiaryList().size());

        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(outOfBoundIndex,
                new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_BABES).build());

        assertCommandFailure(editBeneficiaryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Beneficiary editedBeneficiary = new BeneficiaryBuilder().build();
        Beneficiary beneficiaryToEdit = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder(editedBeneficiary).build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(INDEX_FIRST, descriptor);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBeneficiary(beneficiaryToEdit, editedBeneficiary);
        expectedModel.commitAddressBook();

        // edit -> first beneficiary edited
        editBeneficiaryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered beneficiary list to show all beneficiarys
        expectedModel.undoAddressBook();
        BeneficiaryCommandTestUtil.assertCommandSuccess(
                new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first beneficiary edited again
        expectedModel.redoAddressBook();
        BeneficiaryCommandTestUtil.assertCommandSuccess(
                new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBeneficiaryList().size() + 1);
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_BABES)
                .build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(outOfBoundIndex, descriptor);

        // execution failed -> address book state not added into model
        assertCommandFailure(editBeneficiaryCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Beneficiary} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited beneficiary in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the beneficiary object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameBeneficiaryEdited() throws Exception {
        Beneficiary editedBeneficiary = new BeneficiaryBuilder().build();
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder(editedBeneficiary).build();
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(INDEX_FIRST, descriptor);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showBeneficiaryAtIndex(model, INDEX_SECOND);
        Beneficiary beneficiaryToEdit = model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased());
        expectedModel.setBeneficiary(beneficiaryToEdit, editedBeneficiary);
        expectedModel.commitAddressBook();

        // edit -> edits second beneficiary in unfiltered beneficiary list
        // first beneficiary in filtered beneficiary list
        editBeneficiaryCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered beneficiary list to show all beneficiarys
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(model.getFilteredBeneficiaryList().get(INDEX_FIRST.getZeroBased()), beneficiaryToEdit);
        // redo -> edits same second beneficiary in unfiltered beneficiary list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final EditBeneficiaryCommand standardCommand = new EditBeneficiaryCommand(INDEX_FIRST, DESC_ANIMAL_SHELTER);

        // same values -> returns true
        EditBeneficiaryDescriptor copyDescriptor = new EditBeneficiaryDescriptor(DESC_ANIMAL_SHELTER);
        EditBeneficiaryCommand commandWithSameValues = new EditBeneficiaryCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBeneficiaryCommand(INDEX_SECOND, DESC_ANIMAL_SHELTER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBeneficiaryCommand(INDEX_FIRST, DESC_BABES)));
    }

    /**
     * Edit beneficiary with attached projects and observe the synchronization in those projects
     */
    @Test
    public void execute_editBeneficiary_checkSyncWithAttachedProjects() {
        Model model = new ModelManager(
                getAddressBookForBeneficiarySyncTest(getAandBBeneficiaries(), getProjectA2B()), new UserPrefs());
        CommandHistory commandHistory = new CommandHistory();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        EditBeneficiaryCommand editBeneficiaryCommand = new EditBeneficiaryCommand(INDEX_SECOND, BENEFICIARY_A_EDITED);
        Beneficiary editedBeneficiary = new BeneficiaryBuilder(BENEFICIARY_A)
                .withName(BENEFICIARY_A_EDITED_NAME).build();
        expectedModel.setBeneficiary(BENEFICIARY_A, editedBeneficiary);
        Project projectAttachedToA = buildProjectStub(ATTACH_TO_A_TITLE, editedBeneficiary);
        expectedModel.setProject(ATTACHED_PROJECT_A, projectAttachedToA);
        System.out.println(projectAttachedToA);
        expectedModel.commitAddressBook();
        String expectedMessage = String.format(
                EditBeneficiaryCommand.MESSAGE_EDIT_BENEFICIARY_SUCCESS, editedBeneficiary);
        assertCommandSuccess(editBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

}
