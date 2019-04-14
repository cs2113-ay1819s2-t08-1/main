package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class CompleteTest {

    @Test
    public void isValidComplete() {
        //null
        Assert.assertThrows(NullPointerException.class, () -> Complete.isValidBoolean(null));

        //invalid complete status
        assertFalse(Complete.isValidBoolean("")); // empty String
        assertFalse(Complete.isValidBoolean(" ")); // spaces only
        assertFalse(Complete.isValidBoolean("123")); //numbers
        assertFalse(Complete.isValidBoolean("abc")); //alphabets
        assertFalse(Complete.isValidBoolean("^")); //non-alphanumeric characters

        //valid complete status
        assertTrue(Complete.isValidBoolean("true")); //true status
        assertTrue(Complete.isValidBoolean("false")); //false status
    }
}
