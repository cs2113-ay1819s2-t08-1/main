package seedu.address.logic.parser.volunteer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.AGE_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.DIETARY_PREFERENCE_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.DIETARY_PREFERENCE_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.EMERGENCY_CONTACT_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.EMERGENCY_CONTACT_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_DIETARY_PREFERENCE_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_EMERGENCY_CONTACT_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_MEDICAL_CONDITION_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_RACE_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_RELIGION_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.MEDICAL_CONDITION_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.MEDICAL_CONDITION_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.RACE_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.RACE_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.RELIGION_DESC_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.RELIGION_DESC_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.TAG_DESC_INJURY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.TAG_DESC_NEW;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_TAG_INJURY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_TAG_NEW;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.volunteer.TypicalVolunteers.AMY;
import static seedu.address.testutil.volunteer.TypicalVolunteers.BOB;

import org.junit.Test;

import seedu.address.logic.commands.volunteer.AddVolunteerCommand;
import seedu.address.logic.parser.AddVolunteerCommandParser;
import seedu.address.model.tag.Tag;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.DietaryPreference;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.EmergencyContact;
import seedu.address.model.volunteer.Gender;
import seedu.address.model.volunteer.MedicalCondition;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Religion;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.volunteer.VolunteerBuilder;

public class AddVolunteerCommandParserTest {
    private AddVolunteerCommandParser parser = new AddVolunteerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Volunteer expectedVolunteer = new VolunteerBuilder(BOB).withTags(VALID_TAG_INJURY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple Age - last age accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_AMY + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple Gender - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_AMY + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple Race - last race accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_AMY
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple Age - last religion accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_AMY + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple emergency contacts - last emergency contact accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_AMY + EMERGENCY_CONTACT_DESC_BOB
                + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple dietary preferences - last dietary preference accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_AMY
                + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));
        // multiple medical condition - last medical condition accepted
        assertParseSuccess(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB
                + RACE_DESC_BOB + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB
                + MEDICAL_CONDITION_DESC_AMY
                + MEDICAL_CONDITION_DESC_BOB + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteer));

        // multiple tags - all accepted
        Volunteer expectedVolunteerMultipleTags = new VolunteerBuilder(BOB).withTags(VALID_TAG_INJURY, VALID_TAG_NEW)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, new AddVolunteerCommand(expectedVolunteerMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Volunteer expectedVolunteer = new VolunteerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddVolunteerCommand(expectedVolunteer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVolunteerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB
                + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Name.MESSAGE_CONSTRAINTS);

        // invalid age
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_AGE_DESC + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB
                + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Age.MESSAGE_CONSTRAINTS);
        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + INVALID_GENDER_DESC + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB
                + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Gender.MESSAGE_CONSTRAINTS);
        // invalid race
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + INVALID_RACE_DESC
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB
                + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Race.MESSAGE_CONSTRAINTS);
        // invalid religion
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + INVALID_RELIGION_DESC + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Religion.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Phone.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Address.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, Email.MESSAGE_CONSTRAINTS);

        // invalid emergency contact
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_EMERGENCY_CONTACT_DESC + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, EmergencyContact.MESSAGE_CONSTRAINTS);

        // invalid dietary preference
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + INVALID_DIETARY_PREFERENCE_DESC + MEDICAL_CONDITION_DESC_BOB
                + TAG_DESC_NEW + TAG_DESC_INJURY, DietaryPreference.MESSAGE_CONSTRAINTS);



        // invalid medical condition
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + INVALID_MEDICAL_CONDITION_DESC
                + TAG_DESC_NEW + TAG_DESC_INJURY, MedicalCondition.MESSAGE_CONSTRAINTS);

        // invalid medical condition
        assertParseFailure(parser, NAME_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + INVALID_TAG_DESC + TAG_DESC_INJURY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_AGE_DESC + GENDER_DESC_BOB + RACE_DESC_BOB
                + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                + INVALID_TAG_DESC + TAG_DESC_INJURY, Name.MESSAGE_CONSTRAINTS);
        // non-empty preamble

        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + AGE_DESC_BOB + RACE_DESC_BOB
                        + RELIGION_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                        + EMERGENCY_CONTACT_DESC_BOB + DIETARY_PREFERENCE_DESC_BOB + MEDICAL_CONDITION_DESC_BOB
                        + TAG_DESC_NEW + TAG_DESC_INJURY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVolunteerCommand.MESSAGE_USAGE));
    }
}
