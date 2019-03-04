package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.beneficiary.Beneficiary;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Beneficiary> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

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
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

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
     * Adds the given beneficiary.
     * {@code beneficiary} must not already exist in the address book.
     */
    void addBeneficiary(Beneficiary beneficiary);

    /**
     * Replaces the given beneficiary {@code target} with {@code editedBeneficiary}.
     * {@code target} must exist in the address book.
     * The beneficiary identity of {@code editedBeneficiary} must not be the same as another existing beneficiary in the address book.
     */
    void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary);

    /** Returns an unmodifiable view of the filtered beneficiary list */
    ObservableList<Beneficiary> getFilteredBeneficiaryList();

    /**
     * Updates the filter of the filtered beneficiary list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate);

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

    /**
     * Selected beneficiary in the filtered beneficiary list.
     * null if no beneficiary is selected.
     */
    ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty();

    /**
     * Returns the selected beneficiary in the filtered beneficiary list.
     * null if no beneficiary is selected.
     */
    Beneficiary getSelectedBeneficiary();

    /**
     * Sets the selected beneficiary in the filtered beneficiary list.
     */
    void setSelectedBeneficiary(Beneficiary beneficiary);
}
