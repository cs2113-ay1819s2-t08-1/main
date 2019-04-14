//@@author ndhuu
package seedu.address.model.beneficiary;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.project.ProjectTitle;

/**
 * Represents a Beneficiary in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Beneficiary {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    // Data fields
    private final Address address;
    private Set<ProjectTitle> attachedProjectList = new HashSet<ProjectTitle>();

    /**
     * Every field must be present and not null.
     */
    public Beneficiary(Name name, Phone phone, Email email, Address address) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * @return
     */
    public Beneficiary(Beneficiary b) {
        this.name = b.getName();
        this.phone = b.getPhone();
        this.email = b.getEmail();
        this.address = b.getAddress();
        this.attachedProjectList = b.getHashAttachedProjectLists();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }


    /**
     * Returns true if both Beneficiarys of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Beneficiarys.
     */
    public boolean isSameBeneficiary(Beneficiary otherBeneficiary) {
        if (otherBeneficiary == this) {
            return true;
        }

        return otherBeneficiary != null
            && (otherBeneficiary.getName().equals(getName())
            || (otherBeneficiary.getPhone().equals(getPhone())
            && otherBeneficiary.getEmail().equals(getEmail())));
    }

    /**
     * Returns true if both Beneficiaries have the same identity and data fields.
     * This defines a stronger notion of equality between two Beneficiarys.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Beneficiary)) {
            return false;
        }

        Beneficiary otherBeneficiary = (Beneficiary) other;
        return otherBeneficiary.getName().equals(getName())
            || (otherBeneficiary.getPhone().equals(getPhone())
            && otherBeneficiary.getEmail().equals(getEmail()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, attachedProjectList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Phone: ")
            .append(getPhone())
            .append(" Email: ")
            .append(getEmail())
            .append(" Address: ")
            .append(getAddress())
            .append("Attached Project List: ")
            .append(getAttachedProjectLists());
        return builder.toString();
    }

    /**
     * Add a project title to the attached Project Líst.
     */
    public void addAttachedProject(ProjectTitle title) {
        this.attachedProjectList.add(title);
    }

    /**
     * update a project title in the attached Project Líst.
     */
    public void updateAttachedProject(ProjectTitle oldTitle, ProjectTitle newTitle) {
        this.attachedProjectList.remove(oldTitle);
        addAttachedProject(newTitle);
    }

    /**
     * delete a project title in the attached Project Líst.
     */
    public void deleteAttachedProject(ProjectTitle title) {
        this.attachedProjectList.remove(title);
    }

    /**
     * Check attached project List before deletion.
     * Return true if attached project list is empty.
     */
    public boolean hasAttachedProjects() {
        return !this.attachedProjectList.isEmpty();
    }

    /**
     * Check if the project is assigned for this beneficiary.
     */
    public boolean hasProjectTitle(ProjectTitle projectTitle) {
        return this.attachedProjectList.contains(projectTitle);
    }

    /**
     * .
     * Get method for attached project list.
     *
     * @return a set of project titles.
     */
    public Set<ProjectTitle> getAttachedProjectLists() {
        return Collections.unmodifiableSet(this.attachedProjectList);
    }

    /**
     * Add all the project attached to this beneficiary.
     */
    public void setProjectLists(Set<ProjectTitle> projectList) {
        this.attachedProjectList.addAll(projectList);
    }

    /**
     * .
     * Get method for attached project list.
     *
     * @return a set of project titles.
     */
    public HashSet<ProjectTitle> getHashAttachedProjectLists() {
        return new HashSet<>(this.attachedProjectList);
    }
}

