package seedu.address.logic.commands.volunteer;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.volunteer.NameContainsKeywordsPredicate;

/**
 * Finds and lists all volunteers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "findVolunteer";
    public static final String COMMAND_ALIAS = "fv";
    public static final String COMMAND_PARAMETERS = "Parameters: KEYWORD [MORE_KEYWORDS]...\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all volunteers whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindVolunteerCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredVolunteerList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_VOLUNTEERS_LISTED_OVERVIEW, model.getFilteredVolunteerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindVolunteerCommand // instanceof handles nulls
            && predicate.equals(((FindVolunteerCommand) other).predicate)); // state check
    }
}
