package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.volunteer.Volunteer;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of projects */
    ObservableList<Project> getFilteredProjectList();

    /** Returns an unmodifiable view of the filtered list of volunteers */
    ObservableList<Volunteer> getFilteredVolunteerList();

    /** Returns an unmodifiable view of the filtered list of beneficaries */
    ObservableList<Beneficiary> getFilteredBeneficiaryList();

    /**
     * Returns an unmodifiable view of the list of commands entered by the user.
     * The list is ordered from the least recent command to the most recent command.
     */
    ObservableList<String> getHistory();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     *
     * @see seedu.address.model.Model#selectedPersonProperty()
     */
    ReadOnlyProperty<Project> selectedProjectProperty();
    /**
     * Selected beneficiary in the filtered beneficiary list.
     * null if no beneficiary is selected.
     *
     * @see seedu.address.model.Model#selectedBeneficiaryProperty()
     */
    ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty();

    /**
     * Selected volunteer in the filtered volunteer list.
     * null if no volunteer is selected.
     *
     * @see Model#selectedVolunteerProperty()
     */
    ReadOnlyProperty<Volunteer> selectedVolunteerProperty();

    /**
     * Sets the selected person in the filtered person list.
     *
     * @see seedu.address.model.Model#setSelectedProject(Project)
     */
    void setSelectedProject(Project project);

    /**
     * Sets the selected beneficiary in the filtered beneficiary list.
     *
     * @see seedu.address.model.Model#setSelectedBeneficiary(Beneficiary)
     */
    void setSelectedBeneficiary(Beneficiary beneficiary);

    /**
     * Sets the selected beneficiary in the filtered volunteer list.
     *
     * @see seedu.address.model.Model#setSelectedVolunteer(Volunteer)
     */
    void setSelectedVolunteer(Volunteer volunteer);
}
