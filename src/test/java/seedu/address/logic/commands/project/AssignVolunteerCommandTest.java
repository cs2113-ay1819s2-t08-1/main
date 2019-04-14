package seedu.address.logic.commands.project;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.project.TypicalProjects.PROJECT1;
import static seedu.address.testutil.project.TypicalProjects.PROJECT2;
import static seedu.address.testutil.project.TypicalProjects.PROJECT3;
import static seedu.address.testutil.project.TypicalProjects.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;


public class AssignVolunteerCommandTest {

    private static final CommandHistory commandHistory = new CommandHistory();
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validProjectAndRequiredVolunteers_success() {
        Integer requiredVolunteers = model.getFilteredVolunteerList().size();
        AssignVolunteerCommand assignVolunteerCommand = new AssignVolunteerCommand(PROJECT1.getProjectTitle(),
            requiredVolunteers);
        String expectedMessage = String.format(AssignVolunteerCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        assertCommandSuccess(assignVolunteerCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectValidRequiredVolunteers_throwCommandException() {
        Project invalidProject = PROJECT3;
        Integer requiredVolunteers = model.getFilteredVolunteerList().size();
        AssignVolunteerCommand assignVolunteerCommand = new AssignVolunteerCommand(invalidProject
            .getProjectTitle(), requiredVolunteers);
        assertCommandFailure(assignVolunteerCommand, model, commandHistory, Messages.MESSAGE_PROJECT_NOT_FOUND);
    }

    @Test
    public void execute_validProjectInvalidRequiredVolunteers_throwCommandException() {
        Integer requiredVolunteers = model.getFilteredVolunteerList().size() + 1;
        AssignVolunteerCommand assignVolunteerCommand = new AssignVolunteerCommand(PROJECT1
            .getProjectTitle(), requiredVolunteers);
        assertCommandFailure(assignVolunteerCommand, model, commandHistory, Messages.MESSAGE_NOT_ENOUGH_VOLUNTEERS);
    }

    @Test
    public void equals() {
        final AssignVolunteerCommand assignFirstCommand = new AssignVolunteerCommand(PROJECT1.getProjectTitle(),
            2);
        final AssignVolunteerCommand assignSecondCommand = new AssignVolunteerCommand(PROJECT2.getProjectTitle(),
            2);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignVolunteerCommand assignFirstCommandCopy = new AssignVolunteerCommand(PROJECT1.getProjectTitle(),
            2);
        assertTrue(assignFirstCommand.equals(assignFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different volunteer -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }
}
