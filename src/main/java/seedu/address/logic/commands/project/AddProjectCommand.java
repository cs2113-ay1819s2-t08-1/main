//@@author quinnzzzzz
package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_PROJECT_TITLE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Adds a new project to VolunCHeer.
 */
public class AddProjectCommand extends Command {

    public static final String COMMAND_WORD = "addProject";
<<<<<<< HEAD
    public static final String COMMAND_PARAMETERS = PREFIX_PROJECT_TITLE + "Project Title "
            + PREFIX_DATE + "DATE ";
=======
    public static final String COMMAND_WORD_ALIAS = "ap";
>>>>>>> 3ba3fae3591cd5ac236a1ea50b55b2d158e17b28

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new project to VolunCHeer. "
        + "Parameters: "
        + PREFIX_PROJECT_TITLE + "Project Title "
        + PREFIX_DATE + "Date of Project \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_PROJECT_TITLE + "Charity Run "
        + PREFIX_DATE + "01/06/2019 ";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s\n";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in VolunCHeer";

    private final Project toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Project}
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        this.toAdd = project;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }
        model.addProject(toAdd);
        model.sortProjectByDate(); //Sorts the list of project in ascending date order
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddProjectCommand // instanceof handles nulls
            && toAdd.equals(((AddProjectCommand) other).toAdd));
    }
}

