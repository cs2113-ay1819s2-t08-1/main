//@@author ndhuu
package seedu.address.logic.parser.beneficiary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.ADDRESS_DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.ADDRESS_DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.EMAIL_DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.EMAIL_DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.NAME_DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.NAME_DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.PHONE_DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.PHONE_DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_BABES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.ANIMAL_SHELTER;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.BABES;

import org.junit.Test;

import seedu.address.logic.commands.beneficiary.AddBeneficiaryCommand;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;
import seedu.address.testutil.beneficiary.BeneficiaryBuilder;

public class AddBeneficiaryCommandParserTest {
    private AddBeneficiaryCommandParser parser = new AddBeneficiaryCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Beneficiary expectedBeneficiary = new BeneficiaryBuilder(BABES).build();

        // whitespace only preamble
        assertParseSuccess(parser,
            PREAMBLE_WHITESPACE + NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES
                + ADDRESS_DESC_BABES, new AddBeneficiaryCommand(expectedBeneficiary));

        // multiple names - last name accepted
        assertParseSuccess(parser,
            NAME_DESC_ANIMAL_SHELTER + NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES
                + ADDRESS_DESC_BABES, new AddBeneficiaryCommand(expectedBeneficiary));

        // multiple phones - last phone accepted
        assertParseSuccess(parser,
            NAME_DESC_BABES + PHONE_DESC_ANIMAL_SHELTER + PHONE_DESC_BABES + EMAIL_DESC_BABES
                + ADDRESS_DESC_BABES, new AddBeneficiaryCommand(expectedBeneficiary));

        // multiple emails - last email accepted
        assertParseSuccess(parser,
            NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_ANIMAL_SHELTER + EMAIL_DESC_BABES
                + ADDRESS_DESC_BABES, new AddBeneficiaryCommand(expectedBeneficiary));

        // multiple addresses - last address accepted
        assertParseSuccess(parser,
            NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES + ADDRESS_DESC_ANIMAL_SHELTER
                + ADDRESS_DESC_BABES, new AddBeneficiaryCommand(expectedBeneficiary));

        // multiple tags - all accepted
        Beneficiary expectedBeneficiaryMultipleTags = new BeneficiaryBuilder(BABES)
            .build();
        assertParseSuccess(parser,
            NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES + ADDRESS_DESC_BABES,
            new AddBeneficiaryCommand(expectedBeneficiaryMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Beneficiary expectedBeneficiary = new BeneficiaryBuilder(ANIMAL_SHELTER).build();
        assertParseSuccess(parser,
            NAME_DESC_ANIMAL_SHELTER + PHONE_DESC_ANIMAL_SHELTER
                + EMAIL_DESC_ANIMAL_SHELTER + ADDRESS_DESC_ANIMAL_SHELTER,
            new AddBeneficiaryCommand(expectedBeneficiary));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBeneficiaryCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES + ADDRESS_DESC_BABES,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BABES + VALID_PHONE_BABES + EMAIL_DESC_BABES + ADDRESS_DESC_BABES,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BABES + PHONE_DESC_BABES + VALID_EMAIL_BABES + ADDRESS_DESC_BABES,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES + VALID_ADDRESS_BABES,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_NAME_BABES + VALID_PHONE_BABES + VALID_EMAIL_BABES + VALID_ADDRESS_BABES,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
            INVALID_NAME_DESC + PHONE_DESC_BABES + EMAIL_DESC_BABES + ADDRESS_DESC_BABES,
            Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
            NAME_DESC_BABES + INVALID_PHONE_DESC + EMAIL_DESC_BABES + ADDRESS_DESC_BABES,
            Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
            NAME_DESC_BABES + PHONE_DESC_BABES + INVALID_EMAIL_DESC + ADDRESS_DESC_BABES,
            Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser,
            NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES + INVALID_ADDRESS_DESC,
            Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
            INVALID_NAME_DESC + PHONE_DESC_BABES + EMAIL_DESC_BABES + INVALID_ADDRESS_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            PREAMBLE_NON_EMPTY + NAME_DESC_BABES + PHONE_DESC_BABES + EMAIL_DESC_BABES + ADDRESS_DESC_BABES,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBeneficiaryCommand.MESSAGE_USAGE));
    }
}
