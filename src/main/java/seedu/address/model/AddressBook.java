package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.beneficiary.UniqueBeneficiaryList;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.UniqueBeneficiaryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameBeneficiary comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

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
        beneficiaries = new UniqueBeneficiaryList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Beneficiarys in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the beneficiary list with {@code beneficiaries}.
     * {@code beneficiaries} must not contain duplicate beneficiaries.
     */
    public void setBeneficiarys(List<Beneficiary> beneficiaries) {
        this.beneficiaries.setBeneficiarys(beneficiaries);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setBeneficiarys(newData.getBeneficiaryList());
    }

    //// beneficiary-level operations

    /**
     * Returns true if a beneficiary with the same identity as {@code beneficiary} exists in the address book.
     */
    public boolean hasBeneficiary(Beneficiary beneficiary) {
        requireNonNull(beneficiary);
        return beneficiaries.contains(beneficiary);
    }

    /**
     * Adds a beneficiary to the address book.
     * The beneficiary must not already exist in the address book.
     */
    public void addBeneficiary(Beneficiary p) {
        beneficiaries.add(p);
        indicateModified();
    }

    /**
     * Replaces the given beneficiary {@code target} in the list with {@code editedBeneficiary}.
     * {@code target} must exist in the address book.
     * The beneficiary identity of {@code editedBeneficiary} must not be the same as another existing beneficiary in the address book.
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
        return beneficiaries.asUnmodifiableObservableList().size() + " beneficiaries";
        // TODO: refine later
    }

    @Override
    public ObservableList<Beneficiary> getBeneficiaryList() {
        return beneficiaries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && beneficiaries.equals(((AddressBook) other).beneficiaries));
    }

    @Override
    public int hashCode() {
        return beneficiaries.hashCode();
    }
}
