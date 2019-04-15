package seedu.address.logic.commands.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VOLUNTEERS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all volunteers in the address book to the user.
 */
public class ListVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "listVolunteer";
    public static final String COMMAND_ALIAS = "lv";

    public static final String MESSAGE_SUCCESS = "Listed all volunteers";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredVolunteerList(PREDICATE_SHOW_ALL_VOLUNTEERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
