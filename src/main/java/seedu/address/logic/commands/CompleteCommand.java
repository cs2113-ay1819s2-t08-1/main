package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;

/**
 * Add a project to completes
 */
public class CompleteCommand extends Command {
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicate a project as complete. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Project added to completes: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the address book.";

    private final Index targetProjectIndex;

    private Project targetProject;
    private Project editedProject;

    public CompleteCommand(Index targetProjectIndex) {
        requireNonNull(targetProjectIndex);
        this.targetProjectIndex = targetProjectIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireAllNonNull(targetProject, editedProject);

        try {
            model.setProject(targetProject, editedProject);
        } catch (ProjectNotFoundException pnfe) {
            throw new AssertionError("The target project cannot be missing");
        } catch (DuplicateProjectException e) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedProject.getProjectTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompleteCommand // instanceof handles null
                && ((CompleteCommand) other).targetProjectIndex == this.targetProjectIndex);

    }
}
