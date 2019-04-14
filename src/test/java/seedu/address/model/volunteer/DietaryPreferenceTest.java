package seedu.address.model.volunteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class DietaryPreferenceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new DietaryPreference(null));
    }

    @Test
    public void constructor_invalidDietaryPreference_throwsIllegalArgumentException() {
        String invalidDietaryPreference = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new DietaryPreference(invalidDietaryPreference));
    }

    @Test
    public void isValidDietaryPreference() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> DietaryPreference.isValidDietaryPreference(null));

        // invalid dietary preference

        assertFalse(DietaryPreference.isValidDietaryPreference(" ")); // spaces only
        assertFalse(DietaryPreference.isValidDietaryPreference("No 2 beef")); // // only alphanumeric characters
        assertFalse(DietaryPreference.isValidDietaryPreference("halal*")); //with special character


        // valid dietary preference
        assertFalse(DietaryPreference.isValidDietaryPreference("")); // empty string
        assertTrue(DietaryPreference.isValidDietaryPreference("vegetarian")); // alphabets only
        assertTrue(DietaryPreference.isValidDietaryPreference("Vegetarian")); // with capital letters
    }
}
