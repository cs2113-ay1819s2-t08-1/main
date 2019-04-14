package seedu.address.logic.commands.project;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.project.ProjectCommandTestUtil.showBeneficiaryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.project.TypicalProjects.PROJECT1;
import static seedu.address.testutil.project.TypicalProjects.PROJECT2;
import static seedu.address.testutil.project.TypicalProjects.PROJECT3;
import static seedu.address.testutil.project.TypicalProjects.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

public class AssignBeneficiaryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private static final CommandHistory commandHistory = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void execute_validProjectAndIndexUnfilteredList_success() {
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),
            INDEX_FIRST);
        String expectedMessage = String.format(AssignBeneficiaryCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.commitAddressBook();
        assertCommandSuccess(assignBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validProjectInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBeneficiaryList().size() + 1);
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),
            outOfBoundIndex);
        assertCommandFailure(assignBeneficiaryCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidProjectValidIndexUnfilteredList_throwsCommandException() {
        Project invalidProject = PROJECT3;
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(invalidProject
            .getProjectTitle(),INDEX_FIRST);
        assertCommandFailure(assignBeneficiaryCommand, model, commandHistory,
            Messages.MESSAGE_PROJECT_NOT_FOUND);
    }

    @Test
    public void execute_validProjectInvalidIndexFilteredList_throwsCommandException() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBeneficiaryList().size());

        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),
            outOfBoundIndex);

        assertCommandFailure(assignBeneficiaryCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBeneficiaryList().size());

        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),
            outOfBoundIndex);

        assertCommandFailure(assignBeneficiaryCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidProjectValidIndexUnFilteredList_throwCommandException() {
        Project invalidProject = PROJECT3;
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(invalidProject
            .getProjectTitle(), INDEX_FIRST);
        assertCommandFailure(assignBeneficiaryCommand, model, commandHistory, Messages.MESSAGE_PROJECT_NOT_FOUND);
    }

    @Test
    public void equals() {
        final AssignBeneficiaryCommand assignFirstCommand = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),INDEX_FIRST);
        final AssignBeneficiaryCommand assignSecondCommand = new AssignBeneficiaryCommand(PROJECT2.getProjectTitle(),INDEX_SECOND);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignBeneficiaryCommand assignFirstCommandCopy =  new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),INDEX_FIRST);
        assertTrue(assignFirstCommand.equals(assignFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different beneficiary -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }
}
