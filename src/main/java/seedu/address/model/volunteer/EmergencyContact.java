package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Volunteer's EmergencyContact in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmergencyContact(String)}
 */
public class EmergencyContact {

    public static final String MESSAGE_CONSTRAINTS =
        " Format is recommended to be <Name> <Relationship> <Contact number> and separated by spaces. ";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param emergencyContact A valid name, relationship and phone number.
     */
    public EmergencyContact(String emergencyContact) {
        requireNonNull(emergencyContact);
        checkArgument(isValidEmergencyContact(emergencyContact), MESSAGE_CONSTRAINTS);
        value = emergencyContact;
    }

    /**
     * Returns true if a given string is a valid emergency contact.
     */
    public static boolean isValidEmergencyContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.volunteer.EmergencyContact // instanceof handles nulls
            && value.equals(((seedu.address.model.volunteer.EmergencyContact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
