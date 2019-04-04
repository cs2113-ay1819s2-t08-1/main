//@@author articstranger
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.MapObject;
import seedu.address.model.Model;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 */
public class MapCommand extends Command {

    public static final String COMMAND_WORD = "map";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose data fits any of "

        + "the supplied criteria (case-insensitive, minimum 1, maximum 3) and assigns them points"
        + "based on the number given.\n"
        + "Parameters: (Corresponding prefix, points and criteria 1) "
        + "(Corresponding prefix, points and criteria 2) "
        + "(Corresponding prefix, points and criteria 3)\n"
        + "Example: map y/3>18 r/2chinese";

    public static final String MESSAGE_SUCCESS = "Mapping complete!";
    private MapObject map;

    public MapCommand(MapObject newMap) {
        requireNonNull(newMap);
        map = newMap;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.mapAllVolunteer(map);
        return new CommandResult(
            String.format(Messages.MESSAGE_MAP_SUCCESS));
    }

}
