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
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.PHONE_DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.PHONE_DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_BABES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand.EditBeneficiaryDescriptor;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;
import seedu.address.testutil.beneficiary.EditBeneficiaryDescriptorBuilder;

public class EditBeneficiaryCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBeneficiaryCommand.MESSAGE_USAGE);

    private EditBeneficiaryCommandParser parser = new EditBeneficiaryCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ANIMAL_SHELTER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditBeneficiaryCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ANIMAL_SHELTER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ANIMAL_SHELTER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser,
            "1" + INVALID_PHONE_DESC + EMAIL_DESC_ANIMAL_SHELTER, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser,
            "1" + PHONE_DESC_BABES + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
            "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_ADDRESS_ANIMAL_SHELTER + VALID_PHONE_ANIMAL_SHELTER,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BABES
            + EMAIL_DESC_ANIMAL_SHELTER + ADDRESS_DESC_ANIMAL_SHELTER + NAME_DESC_ANIMAL_SHELTER;

        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder()
            .withName(VALID_NAME_ANIMAL_SHELTER)
            .withPhone(VALID_PHONE_BABES)
            .withEmail(VALID_EMAIL_ANIMAL_SHELTER)
            .withAddress(VALID_ADDRESS_ANIMAL_SHELTER).build();
        EditBeneficiaryCommand expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BABES + EMAIL_DESC_ANIMAL_SHELTER;

        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder().withPhone(VALID_PHONE_BABES)
            .withEmail(VALID_EMAIL_ANIMAL_SHELTER).build();
        EditBeneficiaryCommand expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ANIMAL_SHELTER;
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder()
            .withName(VALID_NAME_ANIMAL_SHELTER).build();
        EditBeneficiaryCommand expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_ANIMAL_SHELTER;
        descriptor = new EditBeneficiaryDescriptorBuilder().withPhone(VALID_PHONE_ANIMAL_SHELTER).build();
        expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_ANIMAL_SHELTER;
        descriptor = new EditBeneficiaryDescriptorBuilder().withEmail(VALID_EMAIL_ANIMAL_SHELTER).build();
        expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_ANIMAL_SHELTER;
        descriptor = new EditBeneficiaryDescriptorBuilder().withAddress(VALID_ADDRESS_ANIMAL_SHELTER).build();
        expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_ANIMAL_SHELTER + ADDRESS_DESC_ANIMAL_SHELTER
            + EMAIL_DESC_ANIMAL_SHELTER + PHONE_DESC_ANIMAL_SHELTER + ADDRESS_DESC_ANIMAL_SHELTER
            + EMAIL_DESC_ANIMAL_SHELTER + PHONE_DESC_BABES + ADDRESS_DESC_BABES + EMAIL_DESC_BABES;

        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder().withPhone(VALID_PHONE_BABES)
            .withEmail(VALID_EMAIL_BABES).withAddress(VALID_ADDRESS_BABES).build();
        EditBeneficiaryCommand expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BABES;
        EditBeneficiaryDescriptor descriptor = new EditBeneficiaryDescriptorBuilder()
            .withPhone(VALID_PHONE_BABES).build();
        EditBeneficiaryCommand expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BABES + INVALID_PHONE_DESC + ADDRESS_DESC_BABES
            + PHONE_DESC_BABES;
        descriptor = new EditBeneficiaryDescriptorBuilder().withPhone(VALID_PHONE_BABES).withEmail(VALID_EMAIL_BABES)
            .withAddress(VALID_ADDRESS_BABES).build();
        expectedCommand = new EditBeneficiaryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
