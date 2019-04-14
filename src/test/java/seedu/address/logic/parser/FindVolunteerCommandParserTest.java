package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindVolunteerCommand;
import seedu.address.model.volunteer.NameContainsKeywordsPredicate;

public class FindVolunteerCommandParserTest {

    private FindVolunteerCommandParser parser = new FindVolunteerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, FindVolunteerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindVolunteerCommand() {
        // no leading and trailing whitespaces
        FindVolunteerCommand expectedFindVolunteerCommand =
            new FindVolunteerCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindVolunteerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindVolunteerCommand);
    }

}
