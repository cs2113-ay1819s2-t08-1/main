package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ProjectDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ProjectDate(null));
    }

    @Test
    public void constructor_invalidProjectDate_throwsIllegalArgumentException() {
        String invalidProjectDate = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ProjectDate(invalidProjectDate));
    }

    @Test
    public void isValidDate() {
        // null projectDate
        Assert.assertThrows(NullPointerException.class, () -> ProjectDate.isValidDate(null));

        // invalid startdate
        assertFalse(ProjectDate.isValidDate("")); // empty string
        assertFalse(ProjectDate.isValidDate(" ")); // spaces only
        assertFalse(ProjectDate.isValidDate("12341231")); // random numbers
        assertFalse(ProjectDate.isValidDate("12 02/1996")); // invalid format
        assertFalse(ProjectDate.isValidDate("30/02/1994")); // invalid date
        assertFalse(ProjectDate.isValidDate("1/12/2001")); // single digit day
        assertFalse(ProjectDate.isValidDate("12/1/2001")); // single digit month
        assertFalse(ProjectDate.isValidDate("1/1/2001")); // Single digit day and month
        assertFalse(ProjectDate.isValidDate("A/random/string")); // invalid date format
        assertFalse(ProjectDate.isValidDate("some string")); // invalid date format
        assertFalse(ProjectDate.isValidDate("02/03/2019")); //before current date

        // valid description
        assertTrue(ProjectDate.isValidDate("29/02/2020")); // leap year
        assertTrue(ProjectDate.isValidDate("31/05/2021")); // random date
    }
}
