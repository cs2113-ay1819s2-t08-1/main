//@@author articstranger
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.MapCommand;

public class MapCommandParserTest {

    private MapCommandParser parser = new MapCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MapCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " ", expectedMessage);

    }

}
