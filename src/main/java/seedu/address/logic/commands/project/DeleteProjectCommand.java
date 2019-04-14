//@@author quinnzzzzz
package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's displayed index from the address book.
 */
public class DeleteProjectCommand extends Command {

    public static final String COMMAND_WORD = "deleteProject";
    public static final String COMMAND_WORD_ALIAS = "dp";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the project identified by the index number used in the displayed project list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";
    private static final String NULL_BENEFICIARY_NAME = "nil";
    private final Index targetIndex;

    public DeleteProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (isValidAssignedBeneficiary(model, projectToDelete)) {
            Beneficiary beneficiary = model.getFilteredBeneficiaryList()
                .filtered(x -> x.getName().equals(projectToDelete.getBeneficiaryAssigned())).get(0);
            Beneficiary edittedBeneficiary = new Beneficiary(beneficiary);
            edittedBeneficiary.deleteAttachedProject(projectToDelete.getProjectTitle());
            model.setBeneficiary(beneficiary, edittedBeneficiary);
        }

        model.deleteProject(projectToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete));
    }

    private boolean isValidAssignedBeneficiary(Model model, Project projectToDelete) {
        return projectToDelete.getBeneficiaryAssigned().toString() != NULL_BENEFICIARY_NAME
            && !model.getFilteredBeneficiaryList()
            .filtered(x -> x.getName().equals(projectToDelete.getBeneficiaryAssigned())).isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteProjectCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteProjectCommand) other).targetIndex)); // state check
    }


}
