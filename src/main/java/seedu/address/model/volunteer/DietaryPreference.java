package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Volunteer's DietaryPreference in the address book.
 * Guarantees: mutable; is valid as declared in {@link #isValidDietaryPreference(String)}
 */

public class DietaryPreference {

    public static final String MESSAGE_CONSTRAINTS = "Dietary Preference should not contain numbers";
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha}]*";
    public final String restriction;

    /**
     * Constructs a {@code DietaryPreference}.
     */
    public DietaryPreference(String dietaryPreference) {
        requireNonNull(dietaryPreference);
        checkArgument(isValidDietaryPreference(dietaryPreference), MESSAGE_CONSTRAINTS);
        restriction = dietaryPreference;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidDietaryPreference(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return restriction;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.volunteer.DietaryPreference
            // instanceof handles nulls
            && restriction.equals(((seedu.address.model.volunteer.DietaryPreference) other).restriction));
        // state check
    }

    @Override
    public int hashCode() {
        return restriction.hashCode();
    }
}
