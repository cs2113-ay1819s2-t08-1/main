//@@swalahlah
package seedu.address.logic.commands.volunteer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_DIETARY_PREFERENCE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_RELIGION;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.volunteer.NameContainsKeywordsPredicate;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.volunteer.EditVolunteerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class VolunteerCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Chiang";
    public static final String VALID_AGE_AMY = "20";
    public static final String VALID_AGE_BOB = "30";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_RACE_AMY = "American";
    public static final String VALID_RACE_BOB = "Chinese";
    public static final String VALID_RELIGION_AMY = "Christian";
    public static final String VALID_RELIGION_BOB = "Buddhist";
    public static final String VALID_PHONE_AMY = "91111111";
    public static final String VALID_PHONE_BOB = "82222222";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DIETARY_PREFERENCE_AMY = "nil";
    public static final String VALID_DIETARY_PREFERENCE_BOB = "no beef";
    public static final String VALID_EMERGENCY_CONTACT_AMY = "Mary Mother 92345678";
    public static final String VALID_EMERGENCY_CONTACT_BOB = "TOM Brother 92221111";
    public static final String VALID_MEDICAL_CONDITION_AMY = "nil";
    public static final String VALID_MEDICAL_CONDITION_BOB = "sprained ankle";
    public static final String VALID_TAG_INJURY = "injury";
    public static final String VALID_TAG_NEW = "new";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String RACE_DESC_AMY = " " + PREFIX_RACE + VALID_RACE_AMY;
    public static final String RACE_DESC_BOB = " " + PREFIX_RACE + VALID_RACE_BOB;
    public static final String RELIGION_DESC_AMY = " " + PREFIX_RELIGION + VALID_RELIGION_AMY;
    public static final String RELIGION_DESC_BOB = " " + PREFIX_RELIGION + VALID_RELIGION_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String DIETARY_PREFERENCE_DESC_AMY = " "
            + PREFIX_DIETARY_PREFERENCE + VALID_DIETARY_PREFERENCE_AMY;
    public static final String DIETARY_PREFERENCE_DESC_BOB = " "
            + PREFIX_DIETARY_PREFERENCE + VALID_DIETARY_PREFERENCE_BOB;
    public static final String EMERGENCY_CONTACT_DESC_AMY = " "
            + PREFIX_EMERGENCY_CONTACT + VALID_EMERGENCY_CONTACT_AMY;
    public static final String EMERGENCY_CONTACT_DESC_BOB = " "
            + PREFIX_EMERGENCY_CONTACT + VALID_EMERGENCY_CONTACT_BOB;
    public static final String MEDICAL_CONDITION_DESC_AMY = " "
            + PREFIX_MEDICAL_CONDITION + VALID_MEDICAL_CONDITION_AMY;
    public static final String MEDICAL_CONDITION_DESC_BOB = " "
            + PREFIX_MEDICAL_CONDITION + VALID_MEDICAL_CONDITION_BOB;

    public static final String TAG_DESC_INJURY = " " + PREFIX_TAG + VALID_TAG_INJURY;
    public static final String TAG_DESC_NEW = " " + PREFIX_TAG + VALID_TAG_NEW;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "22A"; // 'alphabets is not allowed in age
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "a";
    //alphabets, other than m/f is not allowed in gender

    public static final String INVALID_RACE_DESC = " " + PREFIX_RACE + "chinese&"; // '&' not allowed in race
    public static final String INVALID_RELIGION_DESC = " " + PREFIX_RELIGION + "buddhist&"; // '&' not allowed in race
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "9114a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for address
    public static final String INVALID_EMERGENCY_CONTACT_DESC = " " + PREFIX_EMERGENCY_CONTACT;
    // empty string not allowed for emergency contact
    public static final String INVALID_DIETARY_PREFERENCE_DESC = " " + PREFIX_DIETARY_PREFERENCE + "No beef 123";
    //Numbers are not allowed for dietary preference
    public static final String INVALID_MEDICAL_CONDITION_DESC = " " + PREFIX_MEDICAL_CONDITION + "G6PD@";
    //special characters are not allowed in medical Condition
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditVolunteerCommand.EditVolunteerDescriptor DESC_AMY;
    public static final EditVolunteerCommand.EditVolunteerDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditVolunteerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_NEW).build();
        DESC_BOB = new EditVolunteerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_INJURY).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered volunteer list and selected volunteer in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Volunteer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredVolunteerList());
        Volunteer expectedSelectedVolunteer = actualModel.getSelectedVolunteer();

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredVolunteerList());
            assertEquals(expectedSelectedVolunteer, actualModel.getSelectedVolunteer());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the volunteer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showVolunteerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredVolunteerList().size());

        Volunteer volunteer = model.getFilteredVolunteerList().get(targetIndex.getZeroBased());
        final String[] splitName = volunteer.getName().fullName.split("\\s+");
        model.updateFilteredVolunteerList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredVolunteerList().size());
    }

    /**
     * Deletes the first volunteer in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstVolunteer(Model model) {
        Volunteer firstVolunteer = model.getFilteredVolunteerList().get(0);
        model.deleteVolunteer(firstVolunteer);
        model.commitAddressBook();
    }

}
