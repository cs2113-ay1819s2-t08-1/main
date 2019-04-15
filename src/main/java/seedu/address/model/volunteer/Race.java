package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Volunteer's race in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRace(String)}
 */

public class Race {

    public static final String MESSAGE_CONSTRAINTS = "Race should be alphabetic";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";
    public final String raceOutput;

    /*
     * Constructs a {@code race.
     *
     * @param race A valid race.
     */
    public Race(String race) {
        requireNonNull(race);
        //checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
        raceOutput = race;
    }

    /**
     * Returns true if a given string is a valid race.
     */
    public static boolean isValidRace(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return raceOutput;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.volunteer.Race // instanceof handles nulls
            && raceOutput.equals(((seedu.address.model.volunteer.Race) other).raceOutput)); // state check
    }

    @Override
    public int hashCode() {
        return raceOutput.hashCode();
    }

}

