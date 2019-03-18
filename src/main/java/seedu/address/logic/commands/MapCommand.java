package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;




/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 */
public class MapCommand extends Command {

    public static final String COMMAND_WORD = "map";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose data fits any of "
            + "the supplied criteria (case-insensitive) and assigns them points"
            + "based on the order of criteria given, 3 for the first, 2 for the second, 1 for the last.\n"
            + "Parameters: (Corresponding prefix and criteria 1) (Corresponding prefix and criteria 2) "
            + "(Corresponding prefix and criteria 3)\n"
            + "Example: map y/18> r/chinese";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        throw new CommandException("not ready");

    }



}
