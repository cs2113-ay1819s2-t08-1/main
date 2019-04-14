package seedu.address.model.volunteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        Assert.assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("b")); // alphabets apart from M,m, F,f
        assertFalse(Gender.isValidGender("2")); // numbers
        assertFalse(Gender.isValidGender("mm")); // multiple characters

        // valid gender
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("f"));
    }
}
