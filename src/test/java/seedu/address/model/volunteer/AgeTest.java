//package seedu.address.model.volunteer;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//
//import seedu.address.testutil.Assert;
//
//public class AgeTest {
//
//    @Test
//    public void constructor_null_throwsNullPointerException() {
//        Assert.assertThrows(NullPointerException.class, () -> new Age(null));
//    }
//
//    @Test
//    public void constructor_invalidAge_throwsIllegalArgumentException() {
//        String invalidAge = "";
//        Assert.assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
//    }
//
//    @Test
//    public void isValidAge() {
//        // null age
//        Assert.assertThrows(NullPointerException.class, () -> Age.isValidAge(null));
//
//        // invalid age
//        assertFalse(Age.isValidAge("")); // empty string
//        assertFalse(Age.isValidAge(" ")); // spaces only
//        assertFalse(Age.isValidAge("ab")); // alphabets
//        assertFalse(Age.isValidAge("9")); // one digit only
//        assertFalse(Age.isValidAge("123")); // three digit and above
//
//        // valid age
//        assertTrue(Age.isValidAge("22")); // two-digit
//    }
//}
