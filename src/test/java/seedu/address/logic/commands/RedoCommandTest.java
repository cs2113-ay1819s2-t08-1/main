package seedu.address.logic.commands;


import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.volunteer.TypicalVolunteers.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {

    private final Model model;
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    public RedoCommandTest() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Before
    public void setUp() {
        // set up of both models' undo/redo history
        model.undoAddressBook();
        model.undoAddressBook();

        expectedModel.undoAddressBook();
        expectedModel.undoAddressBook();
    }


    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

}
