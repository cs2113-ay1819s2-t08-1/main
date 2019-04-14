package seedu.address.model.volunteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class RaceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Race(null));
    }

    @Test
    public void constructor_invalidRace_throwsIllegalArgumentException() {
        String invalidRace = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Race(invalidRace));
    }

    @Test
    public void isValidRace() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Race.isValidRace(null));

        // invalid name
        assertFalse(Race.isValidRace("")); // empty string
        assertFalse(Race.isValidRace(" ")); // spaces only
        assertFalse(Race.isValidRace("^")); // only non-alphabetic characters
        assertFalse(Race.isValidRace("3213")); //digits
        assertFalse(Race.isValidRace("Christian*")); // contains non-alphabetic characters

        // valid name
        assertTrue(Race.isValidRace("Christian")); // alphabets only
    }
}
