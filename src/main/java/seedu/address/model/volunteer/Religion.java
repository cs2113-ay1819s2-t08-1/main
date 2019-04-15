package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Volunteer's religion in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidReligion(String)}
 */

public class Religion {

    public static final String MESSAGE_CONSTRAINTS = "Religion should be alphabetic";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}]*";
    public final String religionOutput;

    /*
     * Constructs a {@code religion.
     *
     * @param religion A valid religion.
     */
    public Religion(String religion) {
        requireNonNull(religion);
        //checkArgument(isValidReligion(religion), MESSAGE_CONSTRAINTS);
        religionOutput = religion;
    }

    /**
     * Returns true if a given string is a valid religion.
     */
    public static boolean isValidReligion(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return religionOutput;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.volunteer.Religion // instanceof handles nulls
            && religionOutput.equals(((seedu.address.model.volunteer.Religion) other).religionOutput)); // state check
    }

    @Override
    public int hashCode() {
        return religionOutput.hashCode();
    }

}

