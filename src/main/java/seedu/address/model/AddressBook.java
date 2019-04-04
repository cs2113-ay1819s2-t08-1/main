package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.UniqueBeneficiaryList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.model.volunteer.UniqueVolunteerList;
import seedu.address.model.volunteer.Volunteer;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueVolunteerList volunteers;
    private final UniqueProjectList projects;
    private final UniqueBeneficiaryList beneficiaries;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        volunteers = new UniqueVolunteerList();
        persons = new UniquePersonList();
        projects = new UniqueProjectList();
        beneficiaries = new UniqueBeneficiaryList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        indicateModified();
    }

    public void setProjects(List<Project> projects) {
        this.projects.setProjects(projects);
        indicateModified();
    }

    @Override
    public ObservableList<Beneficiary> getBeneficiaryList() {
        return beneficiaries.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Volunteer> getVolunteerList() {
        return volunteers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    /**
     * Replaces the contents of the Beneficiary list with {@code beneficiaries}.
     * {@code beneficiaries} must not contain duplicate persons.
     */
    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries.setBeneficiaries(beneficiaries);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setVolunteers(newData.getVolunteerList());
        setBeneficiaries(newData.getBeneficiaryList());
        setProjects(newData.getProjectList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a volunteer with the same identity as {@code volunteer} exists in the address book.
     */
    public boolean hasVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        return volunteers.contains(volunteer);
    }

    /**
     * Returns true if a beneficiary with the same identity as {@code beneficiary} exists in the address book.
     */
    public boolean hasBeneficiary(Beneficiary beneficiary) {
        requireNonNull(beneficiary);
        return beneficiaries.contains(beneficiary);
    }

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        indicateModified();
    }

    /**
     * Adds a volunteer to the address book.
     * The person must not already exist in the address book.
     */
    public void addVolunteer(Volunteer v) {
        volunteers.add(v);
        indicateModified();
    }

    /**
     * Adds a Project to the address book.
     * The project must not already exist in the address book.
     */
    public void addProject(Project r) {
        projects.addProject(r);
        indicateModified();
    }

    /**
     * Adds a beneficiary to the address book.
     * The beneficiary must not already exist in the address book.
     */
    public void addBeneficiary(Beneficiary b) {
        beneficiaries.add(b);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        indicateModified();
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers.setVolunteers(volunteers);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {
        requireNonNull(editedBeneficiary);
        beneficiaries.setBeneficiary(target, editedBeneficiary);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */

    public void removeProject(Project key) {
        projects.remove(key);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeBeneficiary(Beneficiary key) {
        beneficiaries.remove(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddressBook // instanceof handles nulls
            && persons.equals(((AddressBook) other).persons))
            && projects.equals(((AddressBook) other).projects);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Replaces the given volunteer {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedVolunteer} must not be the same
     * as another existing volunteer in the address book.
     */
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireNonNull(editedVolunteer);

        volunteers.setVolunteer(target, editedVolunteer);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVolunteer(Volunteer key) {
        volunteers.remove(key);
        indicateModified();
    }

    public void setProject(Project target, Project edited)
            throws DuplicateProjectException, ProjectNotFoundException {
        requireNonNull(edited);
        projects.setProject(target, edited);
    }

    //@@author articstranger
    /**
     * Sorts all volunteers by their points
     */
    public void sortVolunteers() {
        volunteers.sortByPoints();
        indicateModified();
    }


}
//    public boolean checkBeneficiaryForProject(ProjectTitle projectTitle,Index targetBeneficiaryIndex) {
//        Beneficiary beneficiary = beneficiaries.getBeneficiaryIndex(targetBeneficiaryIndex);
//        Beneficiary beneficiaryCopy = beneficiary;
//        if (beneficiary.hasProjectTitle(projectTitle)) {
//            return true;
//        }
//        else {
//            beneficiary.addAttachedProject(projectTitle);
//            //project.attachBeneficiary(beneficiary.getName());
//            beneficiaries.setBeneficiary(beneficiaryCopy, beneficiary);
//        }
//        return false;
//    }
//}
