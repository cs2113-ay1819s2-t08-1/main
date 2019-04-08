package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.volunteer.Volunteer;

/**
 * Selects a volunteer identified using it's displayed index from the address book.
 */
public class SelectVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "selectVolunteer";
    public static final String COMMAND_ALIAS = "sv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Selects the volunteer identified by the index number used in the displayed volunteer list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_VOLUNTEER_SUCCESS = "Selected Volunteer: %1$s";

    private final Index targetIndex;

    public SelectVolunteerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Volunteer> filteredVolunteerList = model.getFilteredVolunteerList();

        if (targetIndex.getZeroBased() >= filteredVolunteerList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
        }

        model.setSelectedVolunteer(filteredVolunteerList.get(targetIndex.getZeroBased()));
        return new CommandResult(String.format(MESSAGE_SELECT_VOLUNTEER_SUCCESS, targetIndex.getOneBased()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SelectVolunteerCommand // instanceof handles nulls
            && targetIndex.equals(((SelectVolunteerCommand) other).targetIndex)); // state check
    }
}
