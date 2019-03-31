package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectTitle;

/**
 * Assigns a beneficiary to a project.
 */
public class AssignBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a beneficiary to a project, "
            + "only 1 beneficiary can be assigned to each project.\n"
            + "Parameters: "
            + "[PROJECT_TITLE] "
            + "[INDEX]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Project Sunshine"
            + "1 ";

    public static final String MESSAGE_PARAMETERS = "[PROJECT_TITLE] "
            + PREFIX_INDEX + "INDEX ";

    public static final String MESSAGE_SUCCESS = "Beneficiary successfully assigned to project.";
    public static final String MESSAGE_DUPLICATE_BENEFICIARY =
            "There is already the same beneficiary that exists in the project.";

    private final ProjectTitle targetProject;
    private final Index targetBeneficiaryIndex;

    /**
     * Creates an AssignBeneficiaryCommand to assign beneficiary to {@code Project}
     */
    public AssignBeneficiaryCommand(ProjectTitle targetProject, Index targetBeneficiary) {
        requireNonNull(targetProject);
        requireNonNull(targetBeneficiary);

        this.targetProject = targetProject;
        this.targetBeneficiaryIndex = targetBeneficiary;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Beneficiary> lastShownList = model.getFilteredBeneficiaryList();
        Beneficiary beneficiaryAssigned = lastShownList.get(targetBeneficiaryIndex.getZeroBased());
        if (model.getFilteredProjectList().filtered(x -> x.getProjectTitle().equals(targetProject))== null){
            throw new CommandException("Project does not exist.");
        }
        else {
            Project projectToAssign = model.getFilteredProjectList().filtered(x -> x.getProjectTitle().equals(targetProject)).get(0);
            projectToAssign.setAssignedBeneficiary(beneficiaryAssigned);
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        }

    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AssignBeneficiaryCommand // instanceof handles nulls
                && this.targetProject.equals(((AssignBeneficiaryCommand) other).targetProject)) // state check
                && this.targetBeneficiaryIndex.equals(((AssignBeneficiaryCommand) other).targetBeneficiaryIndex);
    }
}
