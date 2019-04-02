package seedu.address.logic.commands;

import seedu.address.model.beneficiary.Name;
import seedu.address.model.project.Complete;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDate;
import seedu.address.model.project.ProjectTitle;

/**
 * comment for PB
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_TITLE = "Project Title";
    public static final String DEFAULT_PROJECT_DATE = "20190401";
    public static final String DEFAULT_COMPLETE = "false";

    private ProjectTitle projectTitle;
    private ProjectDate projectDate;
    private Complete complete;
    private Name beneficiaryAssigned;

    public ProjectBuilder(){
        projectTitle = new ProjectTitle(DEFAULT_PROJECT_TITLE);
        projectDate = new ProjectDate(DEFAULT_PROJECT_DATE);
        complete = new Complete(DEFAULT_COMPLETE);
        beneficiaryAssigned = new Name("Nil");
    }

    /**
     * Initialises the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectTitle = projectToCopy.getProjectTitle();
        projectDate = projectToCopy.getProjectDate();
        complete = projectToCopy.getComplete();
        beneficiaryAssigned = projectToCopy.getBeneficiaryAssigned();
    }

    /**
     * Sets the {@code ProjectTitle} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectTitle(String projectTitle) {
        this.projectTitle = new ProjectTitle(projectTitle);
        return this;
    }

    /**
     * Sets the {@code ProjectDate} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDate(String projectDate) {
        this.projectDate = new ProjectDate(projectDate);
        return this;
    }
    public Project build() {
        //System.out.println("in"+projectTitle);
        return new Project(projectTitle,projectDate);
    }
    /**
     * Sets the {@code ProjectTitle} of the {@code Project} that we are building.
     */
    public ProjectBuilder withComplete(Boolean complete) {
        this.complete = new Complete(complete);
        return this;
    }
    public ProjectBuilder withBeneficiary(Name beneficiary) {
        this.beneficiaryAssigned = beneficiary;
       //System.out.println("check"+this.beneficiaryAssigned);
        return this;
    }

}
