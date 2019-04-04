//@@author articstranger
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Sorts the current list of volunteers and returns a list of volunteers
 * in descending order of points given by the map command
 */

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sorts and presents a list of volunteers who best fulfill "
        + "the criteria given in the map command, up to the number specified in this command.\n"
        + "Parameters: MAX_NUMBER\n"
        + "Example: sort 10";
    private int maxVol;


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.sortVolunteers();
        return new CommandResult(String.format("Sorted!"));
    }


}
