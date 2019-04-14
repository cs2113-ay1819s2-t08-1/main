package seedu.address.logic.commands.project;

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
import seedu.address.model.volunteer.Volunteer;

public class AddProjectCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddProjectCommand(null);
    }

    @Test
    public void execute_projectAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProjectAdded modelStub = new ModelStubAcceptingProjectAdded();
        Project validProject = new ProjectBuilder().build();


        CommandResult commandResult = new AddProjectCommand(validProject).execute(modelStub, commandHistory);

        assertEquals(String.format(AddProjectCommand.MESSAGE_SUCCESS, validProject), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProject), modelStub.projectsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() throws Exception {
        Project validProject = new ProjectBuilder().build();
        AddProjectCommand addProjectCommand = new AddProjectCommand(validProject);
        ModelStub modelStub = new ModelStubWithProject(validProject);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddProjectCommand.MESSAGE_DUPLICATE_PROJECT);
        addProjectCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Project sunshine = new ProjectBuilder().withProjectTitle("Project Sunshine").build();
        Project recycle = new ProjectBuilder().withProjectTitle("Recycle").build();
        AddProjectCommand addSunshineCommand = new AddProjectCommand(sunshine);
        AddProjectCommand addRecycleCommand = new AddProjectCommand(recycle);

        // same object -> returns true
        assertTrue(addSunshineCommand.equals(addSunshineCommand));

        // same values -> returns true
        AddProjectCommand addSunshineCommandCopy = new AddProjectCommand(sunshine);
        assertTrue(addSunshineCommand.equals(addSunshineCommandCopy));

        // different types -> returns false
        assertFalse(addSunshineCommand.equals(1));

        // null -> returns false
        assertFalse(addSunshineCommand.equals(null));

        // different project -> returns false
        assertFalse(addSunshineCommand.equals(addRecycleCommand));
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortProjectByDate() {

        }

        @Override
        public void setProject(Project target, Project editedProject) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Beneficiary> getFilteredBeneficiaryList() {
            return null;
        }

        @Override
        public ObservableList<Volunteer> getFilteredVolunteerList() {
            return null;
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate) {

        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Project> selectedProjectProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty() {
            return null;
        }

        @Override
        public void setSelectedProject(Project project) {
            throw new AssertionError("This method should not be called.");
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
        public void setSelectedBeneficiary(Beneficiary beneficiary) {

        }

        @Override
        public boolean hasVolunteer(Volunteer volunteer) {
            return false;
        }

        @Override
        public void addVolunteer(Volunteer volunteer) {

        }

        @Override
        public void deleteVolunteer(Volunteer target) {

        }

        @Override
        public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {

        }

        @Override
        public ReadOnlyProperty<Volunteer> selectedVolunteerProperty() {
            return null;
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
            return null;
        }

        @Override
        public void setSelectedVolunteer(Volunteer volunteer) {

        }

        @Override
        public void updateFilteredVolunteerList(Predicate<Volunteer> predicate) {

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
        public List<String[]> addData(int numVolunteers, ArrayList<String> prefixToBePrinted) {
            return null;
        }
    }

    /**
     * A Model stub that contains a single project.
     */
    private class ModelStubWithProject extends ModelStub {
        private final Project project;

        ModelStubWithProject(Project project) {
            requireNonNull(project);
            this.project = project;
        }

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return this.project.isSameProject(project);
        }
    }

    /**
     * A Model stub that always accept the project being added.
     */
    private class ModelStubAcceptingProjectAdded extends ModelStub {
        final ArrayList<Project> projectsAdded = new ArrayList<>();

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return projectsAdded.stream().anyMatch(project::isSameProject);
        }

        @Override
        public void addProject(Project project) {
            requireNonNull(project);
            projectsAdded.add(project);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddProjectCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
