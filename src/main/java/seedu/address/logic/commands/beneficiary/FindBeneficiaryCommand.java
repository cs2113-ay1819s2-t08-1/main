//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.NameContainsKeywordsPredicate;

/**
 * Finds and lists all beneficiaries in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "findBeneficiary";
    public static final String COMMAND_WORD_ALIAS = "fb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all beneficiaries whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " Orphanage";

    private final NameContainsKeywordsPredicate predicate;

    public FindBeneficiaryCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredBeneficiaryList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_BENEFICIARIES_LISTED_OVERVIEW,
                model.getFilteredBeneficiaryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindBeneficiaryCommand // instanceof handles nulls
            && predicate.equals(((FindBeneficiaryCommand) other).predicate)); // state check
    }
}
