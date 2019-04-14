package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.model.volunteer.Volunteer;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */

    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;
    Predicate<Beneficiary> PREDICATE_SHOW_ALL_BENEFICIARIES = unused -> true;
    Predicate<Volunteer> PREDICATE_SHOW_ALL_VOLUNTEERS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    //@@author quinnzzzzz

    /**
     * Adds the given project.
     * {@code project} must not already exist in the address book.
     */
    void addProject(Project project);

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     */
    boolean hasProject(Project project);

    /**
     * Updates the Project status
     *
     * @param target
     * @param editedProject
     * @throws DuplicateProjectException
     * @throws ProjectNotFoundException
     */
    void setProject(Project target, Project editedProject)
        throws DuplicateProjectException, ProjectNotFoundException;

    /**
     * Deletes the given project.
     * The person must exist in the address book.
     */
    void deleteProject(Project target);

    /**
     * Sorts the given project according to date
     * {@code project} must not already exist in the address book.
     */
    void sortProjectByDate();



    Project getSelectedProject();

    void setSelectedProject(Project project);

    /**
     * Add Beneficiary.
     */
    void addBeneficiary(Beneficiary beneficiary);

    //@@author ndhuu

    /**
     * Returns true if a beneficiary with the same identity as {@code beneficiary} exists in the address book.
     */
    boolean hasBeneficiary(Beneficiary beneficiary);

    /**
     * Deletes the given beneficiary.
     * The beneficiary must exist in the address book.
     */
    void deleteBeneficiary(Beneficiary target);

    /**
     * Replaces the given Beneficiary{@code target} with {@code editedBeneficiary}.
     * {@code target} must exist in the address book.
     * The Beneficiary identity of {@code editedBeneficiary} must not be the same
     * as another existing Beneficiary in the address book.
     */
    void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary);

    /**
     * Returns the selected volunteer in the filtered volunteer list.
     * null if no volunteer is selected.
     */

    /**
     * gets the selected beneficiary in the filtered beneficiary list.
     */

    Beneficiary getSelectedBeneficiary();

    void setSelectedBeneficiary(Beneficiary beneficiary);



    //@@author swalahlah

    /**
     * Add Volunteer.
     */
    void addVolunteer(Volunteer volunteer);

    /**
     *
     * @param volunteer
     * @return true if a volunteer with the same identity as {@code volunteer} exists in the address book
     */
    boolean hasVolunteer(Volunteer volunteer);

    /** Deletes the given volunteer.
     * The volunteer must exist in the address book.
     */
    void deleteVolunteer(Volunteer target);

    /**
     * Replaces the given volunteer {@code target} with {@code editedVolunteer}.
     * {@code target} must exist in the address book.
     * The volunteer identity of {@code editedVolunteer} must not be the same as
     * another existing volunteer in the address book.
     */
    void setVolunteer(Volunteer target, Volunteer editedVolunteer);



    /**
     * gets the selected volunteer in the filtered volunteer list.
     */

    Volunteer getSelectedVolunteer();

    /**
     * Sets the selected volunteer in the filtered volunteer list.
     */

    void setSelectedVolunteer(Volunteer volunteer);



    /**
     * compares the age of the current {@code Volunteer} and the criteria in {@code MapObject}.
     */
    int checkAge(MapObject map, Volunteer currentVol);

    /**
     * compares the race of the current {@code Volunteer} and the criteria in {@code MapObject}.
     */
    int checkRace(MapObject map, Volunteer currentVol);

    /**
     * compares the medical condition of the current {@code Volunteer} and the criteria in {@code MapObject}.
     */
    int checkMedical(MapObject map, Volunteer currentVol);

    /**
     * Maps all volunteers in the (@code UniqueVolunteerList)
     */

    void mapAllVolunteer(MapObject map);

    /**
     * Sorts all volunteers in the (@code UniqueVolunteerList)
     * and returns a (@code sortedList)
     */
    void sortVolunteers();

    //get FilteredLists

    /**
     * gets the selected project in the filtered project list.
     */

    void updateFilteredProjectList(Predicate<Project> predicate);

    /**
     * Updates the filter of the filtered Beneficiary list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate);

    /**
     * Updates the filter of the filtered volunteer list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVolunteerList(Predicate<Volunteer> predicate);


    /**
     * Goes throught the volunteer list and adds data to the based on what prefixes are wanted.
     * stops when the list ends or the provided limit is reached.
     */
    List<String[]> addData(int numVolunteers, ArrayList<String> prefixToBePrinted);

    /**
     * Add Beneficiary.
     * Returns an unmodifiable view of the filtered project list
     */
    ObservableList<Project> getFilteredProjectList();


    /**
     * Returns an unmodifiable view of the filtered beneficiary list
     */
    ObservableList<Beneficiary> getFilteredBeneficiaryList();

    /**
     * Returns an unmodifiable view of the filtered volunteer list
     */
    ObservableList<Volunteer> getFilteredVolunteerList();

    //update FilteredLists





    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    //selectedProperty

    /**
     * Selected project in the filtered project list.
     * null if no project is selected.
     */
    ReadOnlyProperty<Project> selectedProjectProperty();

    /**
     * Selected beneficiary in the filtered beneficiary list.
     * null if no beneficiary is selected.
     */
    ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty();

    /**
     * Selected volunteer in the filtered volunteer list.
     * null if no volunteer is selected.
     */
    ReadOnlyProperty<Volunteer> selectedVolunteerProperty();
}
