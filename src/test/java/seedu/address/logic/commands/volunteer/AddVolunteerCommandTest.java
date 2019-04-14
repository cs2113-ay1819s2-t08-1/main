//@@swalahlah
package seedu.address.logic.commands.volunteer;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.MapObject;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.volunteer.VolunteerBuilder;

public class AddVolunteerCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullVolunteer_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddVolunteerCommand(null);
    }

    @Test
    public void execute_volunteerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingVolunteerAdded modelStub = new ModelStubAcceptingVolunteerAdded();
        Volunteer validVolunteer = new VolunteerBuilder().build();

        CommandResult commandResult = new AddVolunteerCommand(validVolunteer).execute(modelStub, commandHistory);

        assertEquals(String.format(AddVolunteerCommand.MESSAGE_SUCCESS, validVolunteer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validVolunteer), modelStub.volunteersAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateVolunteer_throwsCommandException() throws Exception {
        Volunteer validVolunteer = new VolunteerBuilder().build();
        AddVolunteerCommand addCommand = new AddVolunteerCommand(validVolunteer);
        ModelStub modelStub = new ModelStubWithVolunteer(validVolunteer);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddVolunteerCommand.MESSAGE_DUPLICATE_VOLUNTEER);
        addCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Volunteer alice = new VolunteerBuilder().withName("Alice").build();
        Volunteer bob = new VolunteerBuilder().withName("Bob").build();
        AddVolunteerCommand addAliceCommand = new AddVolunteerCommand(alice);
        AddVolunteerCommand addBobCommand = new AddVolunteerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddVolunteerCommand addAliceCommandCopy = new AddVolunteerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different volunteer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addVolunteer(Volunteer volunteer) {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {

        }

        @Override
        public boolean hasProject(Project project) {
            return false;
        }

        @Override
        public void setProject(Project target, Project editedProject) throws DuplicateProjectException, ProjectNotFoundException {

        }

        @Override
        public void deleteProject(Project target) {

        }

        @Override
        public void sortProjectByDate() {

        }

        @Override
        public Project getSelectedProject() {
            return null;
        }

        @Override
        public void setSelectedProject(Project project) {

        }

        @Override
        public void addBeneficiary(Beneficiary beneficiary) {

        }

        @Override
        public boolean hasBeneficiary(Beneficiary beneficiary) {
            return false;
        }

        @Override
        public void deleteBeneficiary(Beneficiary target) {

        }

        @Override
        public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {

        }

        @Override
        public Beneficiary getSelectedBeneficiary() {
            return null;
        }

        @Override
        public void setSelectedBeneficiary(Beneficiary beneficiary) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasVolunteer(Volunteer volunteer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteVolunteer(Volunteer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Volunteer> getFilteredVolunteerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVolunteerList(Predicate<Volunteer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<String[]> addData(int numVolunteers, ArrayList<String> prefixToBePrinted) {
            return null;
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            return null;
        }

        @Override
        public ObservableList<Beneficiary> getFilteredBeneficiaryList() {
            return null;
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Project> selectedProjectProperty() {
            return null;
        }

        @Override
        public ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty() {
            return null;
        }

        @Override
        public ReadOnlyProperty<Volunteer> selectedVolunteerProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Volunteer getSelectedVolunteer() {
            throw new AssertionError
                    ("This method should not be called.");
        }

        @Override
        public void setSelectedVolunteer(Volunteer volunteer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int checkAge(MapObject map, Volunteer currentVol) {
            return 0;
        }

        @Override
        public int checkRace(MapObject map, Volunteer currentVol) {
            return 0;
        }

        @Override
        public int checkMedical(MapObject map, Volunteer currentVol) {
            return 0;
        }

        @Override
        public void mapAllVolunteer(MapObject map) {

        }

        @Override
        public void sortVolunteers() {

        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {

        }

        @Override
        public void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate) {

        }
    }

    /**
     * A Model stub that contains a single volunteer.
     */
    private class ModelStubWithVolunteer extends ModelStub {
        private final Volunteer volunteer;

        ModelStubWithVolunteer(Volunteer volunteer) {
            requireNonNull(volunteer);
            this.volunteer = volunteer;
        }

        @Override
        public boolean hasVolunteer(Volunteer volunteer) {
            requireNonNull(volunteer);
            return this.volunteer.isSameVolunteer(volunteer);
        }
    }

    /**
     * A Model stub that always accept the volunteer being added.
     */
    private class ModelStubAcceptingVolunteerAdded extends ModelStub {
        final ArrayList<Volunteer> volunteersAdded = new ArrayList<>();

        @Override
        public boolean hasVolunteer(Volunteer volunteer) {
            requireNonNull(volunteer);
            return volunteersAdded.stream().anyMatch(volunteer::isSameVolunteer);
        }

        @Override
        public void addVolunteer(Volunteer volunteer) {
            requireNonNull(volunteer);
            volunteersAdded.add(volunteer);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddVolunteerCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
