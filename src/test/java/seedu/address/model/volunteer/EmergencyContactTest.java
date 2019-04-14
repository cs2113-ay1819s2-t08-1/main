package seedu.address.model.volunteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class EmergencyContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new EmergencyContact(null));
    }

    @Test
    public void constructor_invalidEmergencyContact_throwsIllegalArgumentException() {
        String invalidEmergencyContact = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(invalidEmergencyContact));
    }

    @Test
    public void isValidEmergencyContact() {
        // null emergency contact
        Assert.assertThrows(NullPointerException.class, () -> EmergencyContact.isValidEmergencyContact(null));

        // invalid emergency contact
        assertFalse(EmergencyContact.isValidEmergencyContact("")); // empty string
        assertFalse(EmergencyContact.isValidEmergencyContact(" ")); // spaces only
        assertFalse(EmergencyContact.isValidEmergencyContact("^")); // only non-alphanumeric characters
        assertFalse(EmergencyContact.isValidEmergencyContact("peter*")); // contains non-alphanumeric characters

        // valid emergency contact
        assertTrue(EmergencyContact.isValidEmergencyContact("sally mum 98222222")); // alphanumeric characters
        assertTrue(EmergencyContact.isValidEmergencyContact("Callus Brother 91112222")); // with capital letters
    }
}
