package seedu.address.model.beneficiary.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateBeneficiaryException extends RuntimeException {
    public DuplicateBeneficiaryException() {
        super("Operation would result in duplicate persons");
    }
}
