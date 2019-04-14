//@@author quinnzzzzz
package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all projects in the address book to the user.
 */
public class ListProjectCommand extends Command {

    public static final String COMMAND_WORD = "listProject";
    public static final String COMMAND_WORD_ALIAS = "lp";

    public static final String MESSAGE_SUCCESS = "Listed all projects";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
