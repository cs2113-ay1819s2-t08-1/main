//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.showBeneficiaryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListBeneficiaryCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        BeneficiaryCommandTestUtil.assertCommandSuccess(
                new ListBeneficiaryCommand(), model, commandHistory,
                ListBeneficiaryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showBeneficiaryAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(
                new ListBeneficiaryCommand(), model, commandHistory,
                ListBeneficiaryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
