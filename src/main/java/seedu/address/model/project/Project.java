package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectTitle projectTitle;
    private final Date date;
    private final Beneficiary beneficiary;


    /**
     * Every field must be present and not null.
     */
    public Project(ProjectTitle projectTitle, Date date, Beneficiary beneficiary, Set<Tag> tags) {
        requireAllNonNull(projectTitle, date, beneficiary, tags);
        this.projectTitle = projectTitle;
        this.date = date;
        this.beneficiary = beneficiary;
    }

    public ProjectTitle getProjectTitle() {
        return projectTitle;
    }

    public Date getDate() {
        return date;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */

    /**
     * Returns true if both Projects of the same projectTitle have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Projects.
     */
    /** public boolean isSameProject(Project otherProject) {
     * if (otherProject == this) {
     *       return true;
     *   }
     *
     *    return otherProject != null
     *         && otherProject.getprojectTitle().equals(getprojectTitle())
     *         && (otherProject.getDate().equals(getDate()) || otherProject.getBeneficiary().equals(getBeneficiary()));
     */

    /**
     * Returns true if both Projects have the same identity and data fields.
     * This defines a stronger notion of equality between two Projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getProjectTitle().equals(getProjectTitle())
                && otherProject.getDate().equals(getDate())
                && otherProject.getBeneficiary().equals(getBeneficiary());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectTitle())
                .append(" Date: ")
                .append(getDate())
                .append(" Beneficiary: ")
                .append(getBeneficiary())
                .append(" Tags: ");
        //getTags().forEach(builder::append);
        return builder.toString();
    }

}
