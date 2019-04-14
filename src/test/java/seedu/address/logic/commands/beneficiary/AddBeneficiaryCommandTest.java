//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

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
import seedu.address.testutil.beneficiary.BeneficiaryBuilder;

public class AddBeneficiaryCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullBeneficiary_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddBeneficiaryCommand(null);
    }

    @Test
    public void execute_beneficiaryAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBeneficiaryAdded modelStub = new ModelStubAcceptingBeneficiaryAdded();
        Beneficiary validBeneficiary = new BeneficiaryBuilder().build();

        CommandResult commandResult = new AddBeneficiaryCommand(validBeneficiary).execute(modelStub, commandHistory);

        assertEquals(String.format(
            AddBeneficiaryCommand.MESSAGE_SUCCESS, validBeneficiary), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBeneficiary), modelStub.beneficiariesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateBeneficiary_throwsCommandException() throws Exception {
        Beneficiary validBeneficiary = new BeneficiaryBuilder().build();
        AddBeneficiaryCommand addBeneficiaryCommand = new AddBeneficiaryCommand(validBeneficiary);
        ModelStub modelStub = new ModelStubWithBeneficiary(validBeneficiary);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddBeneficiaryCommand.MESSAGE_DUPLICATE_BENEFICIARY);
        addBeneficiaryCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Beneficiary alice = new BeneficiaryBuilder().withName("Alice").withPhone("123456789")
            .withEmail("Alice@gmail.com").build();
        Beneficiary bob = new BeneficiaryBuilder().withName("Bob").withPhone("23456789")
            .withEmail("Bob@gmail.com").build();
        AddBeneficiaryCommand addAliceCommand = new AddBeneficiaryCommand(alice);
        AddBeneficiaryCommand addBobCommand = new AddBeneficiaryCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddBeneficiaryCommand addAliceCommandCopy = new AddBeneficiaryCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different beneficiary -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBeneficiary(Beneficiary beneficiary) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void addVolunteer(Volunteer volunteer) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void sortProjectByDate() {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public boolean hasProject(Project project) {
            return false;
        }

        @Override
        public boolean hasBeneficiary(Beneficiary beneficiary) {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public void setProject(Project target, Project editedProject)
            throws DuplicateProjectException, ProjectNotFoundException {

        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public boolean hasVolunteer(Volunteer volunteer) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void deleteBeneficiary(Beneficiary target) {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Beneficiary> getFilteredBeneficiaryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            return null;
        }

        @Override
        public ObservableList<Volunteer> getFilteredVolunteerList() {
            return null;
        }

        @Override
        public void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {

        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method"
                + " should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method "
                + "should not be called.");
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
            return null;
        }

        @Override
        public ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty() {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public Project getSelectedProject() {
            return null;
        }

        @Override
        public void setSelectedProject(Project project) {

        }

        @Override
        public Beneficiary getSelectedBeneficiary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedBeneficiary(Beneficiary person) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void deleteVolunteer(Volunteer target) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public ReadOnlyProperty<Volunteer> selectedVolunteerProperty() {
            return null;
        }

        @Override
        public Volunteer getSelectedVolunteer() {
            throw new AssertionError("This method "
                + "should not be called.");
        }

        @Override
        public void setSelectedVolunteer(Volunteer volunteer) {
            throw new AssertionError("This method "
                + "should not be called.");
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
            throw new AssertionError("This method "
                + "should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithBeneficiary extends ModelStub {
        private final Beneficiary person;

        ModelStubWithBeneficiary(Beneficiary person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasBeneficiary(Beneficiary person) {
            requireNonNull(person);
            return this.person.isSameBeneficiary(person);
        }
    }

    /**
     * A Model stub that always accept the beneficiary being added.
     */
    private class ModelStubAcceptingBeneficiaryAdded extends ModelStub {
        final ArrayList<Beneficiary> beneficiariesAdded = new ArrayList<>();

        @Override
        public boolean hasBeneficiary(Beneficiary person) {
            requireNonNull(person);
            return beneficiariesAdded.stream().anyMatch(person::isSameBeneficiary);
        }

        @Override
        public void addBeneficiary(Beneficiary person) {
            requireNonNull(person);
            beneficiariesAdded.add(person);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddBeneficiaryCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
