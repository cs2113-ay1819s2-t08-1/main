//package seedu.address.model.volunteer;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//
//import seedu.address.testutil.Assert;
//
//public class ReligionTest {
//
//    @Test
//    public void constructor_null_throwsNullPointerException() {
//        Assert.assertThrows(NullPointerException.class, () -> new Religion(null));
//    }
//
//    @Test
//    public void constructor_invalidReligion_throwsIllegalArgumentException() {
//        String invalidReligion = "";
//        Assert.assertThrows(IllegalArgumentException.class, () -> new Religion(invalidReligion));
//    }
//
//    @Test
//    public void isValidReligion() {
//        // null name
//        Assert.assertThrows(NullPointerException.class, () -> Religion.isValidReligion(null));
//
//        // invalid name
//        assertFalse(Religion.isValidReligion("")); // empty string
//        assertFalse(Religion.isValidReligion(" ")); // spaces only
//        assertFalse(Religion.isValidReligion("^")); // only non-alphabetic characters
//        assertFalse(Religion.isValidReligion("3213")); //digits
//        assertFalse(Religion.isValidReligion("Buddhist*")); // contains non-alphabetic characters
//
//        // valid name
//        assertTrue(Religion.isValidReligion("Muslim")); // alphabets only
//    }
//}
