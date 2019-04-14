//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.HARD_DELETE_MODE;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_INDEX;

import java.util.ArrayList;
import java.util.HashSet;
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
import seedu.address.model.project.ProjectTitle;

/**
 * Deletes a beneficiary identified using it's displayed index from the address book.
 */
public class DeleteBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "deleteBeneficiary";
    public static final String COMMAND_WORD_ALIAS = "db";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the beneficiary identified by the index number used in the displayed beneficiary list.\n"
        + "Parameters: i/INDEX (must be a positive integer) -D (hard delete mode) \n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 [optional] " + HARD_DELETE_MODE;

    public static final String MESSAGE_DELETE_BENEFICIARY_SUCCESS = "Deleted Beneficiary: %1$s";

    public static final String MESSAGE_BENEFICIARY_HAS_PROJECTS_ATTACHED = "%1$s has this/these projects: %2$s"
        + " attached to it. \n "
        + "Please delete them before delete the beneficiary"
        + "or use hard mode delete: -D to delete attached projects and this beneficiary";

    private final Index targetIndex;
    private final boolean isHardDeleteMode;

    public DeleteBeneficiaryCommand(Index targetIndex, boolean isHardDeleteMode) {
        this.targetIndex = targetIndex;
        this.isHardDeleteMode = isHardDeleteMode;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Beneficiary> lastShownList = model.getFilteredBeneficiaryList();

        if (exceedBeneficiaryListSize(lastShownList)) {
            throw new CommandException(Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
        }

        Beneficiary beneficiaryToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (beneficiaryToDelete.hasAttachedProjects() && !isHardDeleteMode) {
            throw new CommandException(String.format(MESSAGE_BENEFICIARY_HAS_PROJECTS_ATTACHED,
                beneficiaryToDelete.getName(),
                beneficiaryToDelete.getAttachedProjectLists()));
        } else if (beneficiaryToDelete.hasAttachedProjects() && isHardDeleteMode) {
            deleteAttachedProjects(model, beneficiaryToDelete);
        }

        model.deleteBeneficiary(beneficiaryToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_BENEFICIARY_SUCCESS, beneficiaryToDelete));
    }

    /**
     * Delete attached projects to the beneficiary
     *
     * @param model
     * @param beneficiaryToDelete
     */
    private void deleteAttachedProjects(Model model, Beneficiary beneficiaryToDelete) {
        HashSet<ProjectTitle> attachedProjects = beneficiaryToDelete.getHashAttachedProjectLists();
        List<Project> projectsToDelete = new ArrayList<>(model.getFilteredProjectList());
        for (Project p : projectsToDelete) {
            if (attachedProjects.contains(p.getProjectTitle())) {
                model.deleteProject(p);
            }
        }
    }

    private boolean exceedBeneficiaryListSize(List<Beneficiary> lastShownList) {
        return targetIndex.getZeroBased() >= lastShownList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteBeneficiaryCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteBeneficiaryCommand) other).targetIndex)); // state check
    }
}
