//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BENEFICIARIES;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all beneficiaries in the address book to the user.
 */
public class ListBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "listBeneficiary";
    public static final String COMMAND_WORD_ALIAS = "lb";

    public static final String MESSAGE_SUCCESS = "Listed all beneficiaries";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredBeneficiaryList(PREDICATE_SHOW_ALL_BENEFICIARIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
