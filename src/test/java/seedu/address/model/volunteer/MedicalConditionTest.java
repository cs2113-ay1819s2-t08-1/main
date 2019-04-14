//package seedu.address.model.volunteer;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//
//import seedu.address.testutil.Assert;
//
//public class MedicalConditionTest {
//
//    @Test
//    public void constructor_null_throwsNullPointerException() {
//        Assert.assertThrows(NullPointerException.class, () -> new MedicalCondition(null));
//    }
//
//    @Test
//    public void constructor_invalidMedicalCondition_throwsIllegalArgumentException() {
//        String invalidMedicalCondition = "";
//        Assert.assertThrows(IllegalArgumentException.class, () -> new MedicalCondition(invalidMedicalCondition));
//    }
//
//    @Test
//    public void isValidMedicalCondition() {
//        // null medical condition
//        Assert.assertThrows(NullPointerException.class, () -> MedicalCondition.isValidMedicalCondition(null));
//
//        // invalid  medical condition
//
//        assertFalse(MedicalCondition.isValidMedicalCondition(" ")); // spaces only
//        assertFalse(MedicalCondition.isValidMedicalCondition("^")); // only non-alphanumeric characters
//        // contains non-alphanumeric characters
//        assertFalse(MedicalCondition.isValidMedicalCondition("Sprained back*"));
//
//
//        // valid  medical condition
//        assertFalse(MedicalCondition.isValidMedicalCondition("")); // empty string
//        assertTrue(MedicalCondition.isValidMedicalCondition("torn acl")); // alphabets only
//        assertTrue(MedicalCondition.isValidMedicalCondition("Torn ACL")); // with capital letters
//    }
//}
