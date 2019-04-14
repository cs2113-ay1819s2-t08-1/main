package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.model.volunteer.Volunteer;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;
    private final SimpleObjectProperty<Project> selectedProject = new SimpleObjectProperty<>();
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
        filteredProjects = new FilteredList<>(versionedAddressBook.getProjectList());
        filteredProjects.addListener(this::ensureSelectedProjectIsValid);

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
    public void addProject(Project project) {
        versionedAddressBook.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        versionedAddressBook.addBeneficiary(beneficiary);
        updateFilteredBeneficiaryList(PREDICATE_SHOW_ALL_BENEFICIARIES);
    }

    @Override
    public void addVolunteer(Volunteer volunteer) {
        versionedAddressBook.addVolunteer(volunteer);
        updateFilteredVolunteerList(PREDICATE_SHOW_ALL_VOLUNTEERS);
    }

    @Override
    public void setProject(Project targetProject, Project editedProject) {
        requireNonNull(editedProject);
        versionedAddressBook.setProject(targetProject, editedProject);
    }

    @Override
    public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {
        requireAllNonNull(target, editedBeneficiary);

        versionedAddressBook.setBeneficiary(target, editedBeneficiary);
    }

    @Override
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireAllNonNull(target, editedVolunteer);

        versionedAddressBook.setVolunteer(target, editedVolunteer);
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return versionedAddressBook.hasProject(project);
    }

    @Override
    public boolean hasBeneficiary(Beneficiary beneficiary) {
        requireNonNull(beneficiary);
        return versionedAddressBook.hasBeneficiary(beneficiary);
    }

    @Override
    public boolean hasVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        return versionedAddressBook.hasVolunteer(volunteer);
    }

    @Override
    public void deleteProject(Project target) {
        versionedAddressBook.removeProject(target);
    }

    @Override
    public void deleteBeneficiary(Beneficiary target) {
        versionedAddressBook.removeBeneficiary(target);
    }

    @Override
    public void deleteVolunteer(Volunteer target) {
        versionedAddressBook.removeVolunteer(target);
    }

    @Override
    public void sortProjectByDate() {
        versionedAddressBook.sortProjectByDate();
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }
    //=========== Filtered Project List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    //=========== Filtered Beneficiary List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Beneficiary} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Beneficiary> getFilteredBeneficiaryList() {
        return filteredBeneficiaries;
    }

    @Override
    public void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate) {
        requireNonNull(predicate);
        filteredBeneficiaries.setPredicate(predicate);
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

    //=========== Selected properties ===========================================================================

    @Override
    public ReadOnlyProperty<Volunteer> selectedVolunteerProperty() {
        return selectedVolunteer;
    }

    @Override
    public Project getSelectedProject() {
        return null;
    }

    @Override
    public Beneficiary getSelectedBeneficiary() {
        return null;
    }

    @Override
    public Volunteer getSelectedVolunteer() {
        return selectedVolunteer.getValue();
    }


    @Override
    public void setSelectedProject(Project project) {
        if (project != null && !filteredProjects.contains(project)) {
            throw new ProjectNotFoundException();
        }
        selectedProject.setValue(project);
    }

    @Override
    public void setSelectedBeneficiary(Beneficiary beneficiary) {
        if (beneficiary != null && !filteredBeneficiaries.contains(beneficiary)) {
            throw new BeneficiaryNotFoundException();
        }
        selectedBeneficiary.setValue(beneficiary);
    }



    @Override
    public void setSelectedVolunteer(Volunteer volunteer) {
        if (volunteer != null && !filteredVolunteers.contains(volunteer)) {
            selectedVolunteer.setValue(volunteer);
        }
    }
    @Override
    public ReadOnlyProperty<Project> selectedProjectProperty() {
        return selectedProject;
    }

    @Override
    public ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty() {
        return selectedBeneficiary;
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
        sortedVolunteers = versionedAddressBook.getVolunteerList().sorted((new Comparator<Volunteer>() {
            public int compare(Volunteer s1, Volunteer s2) {
                return s2.getPoints() - s1.getPoints();
            }
        }));
        versionedAddressBook.sortVolunteers();
        updateFilteredVolunteerList(PREDICATE_SHOW_ALL_VOLUNTEERS);
    }

    //=========== Selected person ===========================================================================

    /**
     * Goes through the volunteer list and adds data to the based on what prefixes are wanted.
     * stops when the list ends or the provided limit is reached.
     */
    public List<String[]> addData(int numVolunteers, ArrayList<String> prefixToBePrinted) {
        List<String[]> finalData = new ArrayList<>();
        finalData.add(new String[]{"Volunteers"});
        ArrayList<String> tempVolunteer = new ArrayList<>();
        int i = 0;
        for (Volunteer vol : versionedAddressBook.getVolunteerList()) {
            if (i >= numVolunteers) {
                break;
            }
            prefixToBePrinted.forEach(prefix -> {
                switch (prefix) {

                    case "n/":
                        tempVolunteer.add(vol.getName().toString());
                        break;

                    case "y/":
                        tempVolunteer.add(vol.getAge().toString());
                        break;

                    case "g/":
                        tempVolunteer.add(vol.getGender().toString());
                        break;

                    case "r/":
                        tempVolunteer.add(vol.getRace().toString());
                        break;

                    case "rg/":
                        tempVolunteer.add(vol.getReligion().toString());
                        break;

                    case "p/":
                        tempVolunteer.add(vol.getPhone().toString());
                        break;

                    case "a/":
                        tempVolunteer.add(vol.getAddress().toString());
                        break;

                    case "e/":
                        tempVolunteer.add(vol.getEmail().toString());
                        break;

                    case "m/":
                        tempVolunteer.add(vol.getMedicalCondition().toString());
                        break;

                    case "dp/":
                        tempVolunteer.add(vol.getDietaryPreference().toString());
                        break;

                    case "ec/":
                        tempVolunteer.add(vol.getEmergencyContact().toString());
                        break;

                    case "t/":
                        tempVolunteer.add(vol.getTags().toString());
                        break;

                    default:
                        break;
                }
            });
            finalData.add(tempVolunteer.toArray(new String[0]));
            tempVolunteer.clear();
            i++;

        }
        return finalData;
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
     * Ensures {@code selectedProject} is a valid project in {@code filteredProjects}.
     */
    private void ensureSelectedProjectIsValid(ListChangeListener.Change<? extends Project> change) {
        while (change.next()) {
            if (selectedProject.getValue() == null) {
                // null is always a valid selected project, so we do not need to check that it is valid anymore.
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
                // Select the project that came before it in the list,
                // or clear the selection if there is no such project.
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
            && filteredProjects.equals(other.filteredProjects)
            && filteredBeneficiaries.equals(other.filteredBeneficiaries)
            && Objects.equals(selectedBeneficiary.get(), other.selectedBeneficiary.get());
        // && Objects.equals(selectedProject.get(), other,selectedProject.get());
    }

}
