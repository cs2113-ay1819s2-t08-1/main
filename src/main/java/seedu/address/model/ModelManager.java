package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.exceptions.BeneficiaryNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.project.Project;
import seedu.address.model.volunteer.Volunteer;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Project> filteredProjects;
    private final SimpleObjectProperty<Project> selectedProject = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();

    private final FilteredList<Volunteer> filteredVolunteers;
    private final SimpleObjectProperty<Volunteer> selectedVolunteer = new SimpleObjectProperty<>();
    private final FilteredList<Beneficiary> filteredBeneficiaries;
    private final SimpleObjectProperty<Beneficiary> selectedBeneficiary = new SimpleObjectProperty<>();
    private SortedList<Volunteer> sortedVolunteers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
        filteredPersons.addListener(this::ensureSelectedPersonIsValid);
        filteredProjects = new FilteredList<>(versionedAddressBook.getProjectList());

        filteredBeneficiaries = new FilteredList<>(versionedAddressBook.getBeneficiaryList());
        filteredBeneficiaries.addListener(this::ensureSelectedBeneficiaryIsValid);

        filteredVolunteers = new FilteredList<>(versionedAddressBook.getVolunteerList());
        filteredVolunteers.addListener(this::ensureSelectedVolunteerIsValid);


    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public boolean hasBeneficiary(Beneficiary beneficiary) {
        requireNonNull(beneficiary);
        return versionedAddressBook.hasBeneficiary(beneficiary);
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return versionedAddressBook.hasProject(project);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
    }

    @Override

    public void deleteProject(Project target) {
        versionedAddressBook.removeProject(target);
    }

    public void deleteBeneficiary(Beneficiary target) {
        versionedAddressBook.removeBeneficiary(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addVolunteer(Volunteer volunteer) {
        versionedAddressBook.addVolunteer(volunteer);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        versionedAddressBook.addBeneficiary(beneficiary);
        updateFilteredBeneficiaryList(PREDICATE_SHOW_ALL_BENEFICIARIES);
    }

    @Override
    public void addProject(Project project) {
        versionedAddressBook.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {
        requireAllNonNull(target, editedBeneficiary);

        versionedAddressBook.setBeneficiary(target, editedBeneficiary);
    }

    @Override
    public boolean hasVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        return versionedAddressBook.hasVolunteer(volunteer);
    }

    @Override
    public void setProject(Project targetProject, Project editedProject) {
        requireNonNull(editedProject);
        versionedAddressBook.setProject(targetProject, editedProject);
    }

    @Override
    public void deleteVolunteer(Volunteer target) {
        versionedAddressBook.removeVolunteer(target);
    }

    @Override
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireAllNonNull(target, editedVolunteer);

        versionedAddressBook.setVolunteer(target, editedVolunteer);
    }
    //=========== Filtered Volunteer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Volunteer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Volunteer> getFilteredVolunteerList() {
        return filteredVolunteers;
    }

    @Override
    public void updateFilteredVolunteerList(Predicate<Volunteer> predicate) {
        requireNonNull(predicate);
        filteredVolunteers.setPredicate(predicate);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Beneficiary> getFilteredBeneficiaryList() {
        return filteredBeneficiaries;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    @Override
    public void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate) {
        requireNonNull(predicate);
        filteredBeneficiaries.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    //=========== Selected volunteer ===========================================================================

    @Override
    public ReadOnlyProperty<Volunteer> selectedVolunteerProperty() {
        return selectedVolunteer;
    }

    @Override
    public Volunteer getSelectedVolunteer() {
        return selectedVolunteer.getValue();
    }

    @Override
    public void setSelectedVolunteer(Volunteer volunteer) {
        if (volunteer != null && !filteredVolunteers.contains(volunteer)) {
            selectedVolunteer.setValue(volunteer);
        }
    }

    //@@author articstranger
    /**
     * compares the age of the current {@code Volunteer} and the criteria in {@code MapObject}.
     */
    public int checkAge(MapObject map, Volunteer currentVol) {
        switch (map.getComparator()) {

        case "<":
            if (Integer.parseInt(currentVol.getAge().toString()) < map.getAgePair().getValue()) {
                return map.getAgePair().getKey();
            }
            break;

        case ">":
            if (Integer.parseInt(currentVol.getAge().toString()) > map.getAgePair().getValue()) {
                return map.getAgePair().getKey();
            }
            break;

        case "=":
            if (Integer.parseInt(currentVol.getAge().toString()) == map.getAgePair().getValue()) {
                return map.getAgePair().getKey();
            }
            break;

        default:
            return 0;
        }
        return 0;
    }


    /**
     * compares the race of the current {@code Volunteer} and the criteria in {@code MapObject}.
     */
    public int checkRace(MapObject map, Volunteer currentVol) {
        if (currentVol.getRace().toString().equalsIgnoreCase(map.getRacePair().getValue())) {
            return map.getRacePair().getKey();
        }
        return 0;
    }


    /**
     * compares the medical condition of the current {@code Volunteer} and the criteria in {@code MapObject}.
     */
    public int checkMedical(MapObject map, Volunteer currentVol) {
        if (currentVol.getMedicalCondition().toString().equalsIgnoreCase(map.getMedicalPair().getValue())) {
            return map.getMedicalPair().getKey();
        }
        return 0;
    }


    /**
     * Maps all volunteers in the (@code UniqueVolunteerList)
     */
    public void mapAllVolunteer(MapObject map) {
        versionedAddressBook.getVolunteerList().forEach(volunteer -> {
            volunteer.resetPoints();
            volunteer.addPoints(checkAge(map, volunteer));
            volunteer.addPoints(checkRace(map, volunteer));
            volunteer.addPoints(checkMedical(map, volunteer));
        });
    }

    /**
     * Sorts all volunteers in the (@code UniqueVolunteerList)
     * and returns a (@code sortedList)
     */

    public void sortVolunteers() {
        versionedAddressBook.sortVolunteers();
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }


    /**
     * Ensures {@code selectedVolunteer} is a valid volunteer in {@code filteredVolunteers}.
     */
    private void ensureSelectedVolunteerIsValid(ListChangeListener.Change<? extends Volunteer> change) {
        while (change.next()) {
            if (selectedVolunteer.getValue() == null) {
                // null is always a valid selected volunteer, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedVolunteerReplaced =
                change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedVolunteer.getValue());
            if (wasSelectedVolunteerReplaced) {
                // Update selectedVolunteer to its new value.
                int index = change.getRemoved().indexOf(selectedVolunteer.getValue());
                selectedVolunteer.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedVolunteerRemoved = change.getRemoved().stream()
                .anyMatch(removedVolunteer -> selectedVolunteer.getValue().isSameVolunteer(removedVolunteer));
            if (wasSelectedVolunteerRemoved) {
                // Select the volunteer that came before it in the list,
                // or clear the selection if there is no such volunteer.
                selectedVolunteer.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    //=========== Selected person ===========================================================================

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {
        return selectedPerson;
    }

    @Override
    public ReadOnlyProperty<Project> selectedProjectProperty() {
        return selectedProject;
    }

    @Override
    public ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty() {
        return selectedBeneficiary;
    }

    @Override
    public Person getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public void setSelectedPerson(Person person) {
        if (person != null && !filteredPersons.contains(person)) {
            throw new PersonNotFoundException();
        }
        selectedPerson.setValue(person);
    }

    @Override
    public Project getSelectedProject() {
        return selectedProject.getValue();
    }

    @Override
    public void setSelectedProject(Project project) {
        if (project != null && !filteredProjects.contains(project)) {
            throw new PersonNotFoundException();
        }
        selectedProject.setValue(project);
    }

    @Override
    public Beneficiary getSelectedBeneficiary() {
        return selectedBeneficiary.getValue();
    }

    @Override
    public void setSelectedBeneficiary(Beneficiary beneficiary) {
        if (beneficiary != null && !filteredBeneficiaries.contains(beneficiary)) {
            throw new BeneficiaryNotFoundException();
        }
        selectedBeneficiary.setValue(beneficiary);
    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedBeneficiaryIsValid(ListChangeListener.Change<? extends Beneficiary> change) {
        while (change.next()) {
            if (selectedBeneficiary.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedBeneficiaryReplaced = change.wasReplaced()
                && change.getAddedSize() == change.getRemovedSize()
                && change.getRemoved().contains(selectedBeneficiary.getValue());
            if (wasSelectedBeneficiaryReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedBeneficiary.getValue());
                selectedBeneficiary.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedBeneficiaryRemoved = change.getRemoved().stream()
                .anyMatch(removedBeneficiary -> selectedBeneficiary.getValue()
                    .isSameBeneficiary(removedBeneficiary));
            if (wasSelectedBeneficiaryRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedBeneficiary.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends Person> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                .anyMatch(removedPerson -> selectedPerson.getValue().isSamePerson(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedProjectIsValid(ListChangeListener.Change<? extends Project> change) {
        while (change.next()) {
            if (selectedProject.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedProjectReplaced = change.wasReplaced() && change.getAddedSize() == change
                .getRemovedSize()
                && change.getRemoved().contains(selectedProject.getValue());
            if (wasSelectedProjectReplaced) {
                // Update selectedProject to its new value.
                int index = change.getRemoved().indexOf(selectedProject.getValue());
                selectedProject.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedProjectRemoved = change.getRemoved().stream()
                .anyMatch(removedProject -> selectedProject.getValue().isSameProject(removedProject));
            if (wasSelectedProjectRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedProject.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
            && userPrefs.equals(other.userPrefs)
            && filteredPersons.equals(other.filteredPersons)
            && filteredProjects.equals(other.filteredProjects)
            && Objects.equals(selectedPerson.get(), other.selectedPerson.get());
        // && Objects.equals(selectedProject.get(), other,selectedProject.get());
    }

}
