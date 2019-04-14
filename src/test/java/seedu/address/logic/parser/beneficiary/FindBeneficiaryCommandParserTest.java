//@@author ndhuu
package seedu.address.logic.parser.beneficiary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.beneficiary.FindBeneficiaryCommand;
import seedu.address.model.beneficiary.NameContainsKeywordsPredicate;

public class FindBeneficiaryCommandParserTest {

    private FindBeneficiaryCommandParser parser = new FindBeneficiaryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBeneficiaryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindBeneficiaryCommand() {
        // no leading and trailing whitespaces
        FindBeneficiaryCommand expectedFindBeneficiaryCommand =
            new FindBeneficiaryCommand(new NameContainsKeywordsPredicate(Arrays.asList("Animal", "Babes")));
        assertParseSuccess(parser, "Animal Babes", expectedFindBeneficiaryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Animal \n \t Babes \t", expectedFindBeneficiaryCommand);
    }

}
