package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_TAG;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand.EditVolunteerDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditVolunteerCommand object
 */
public class EditVolunteerCommandParser implements Parser<EditVolunteerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditVolunteerCommand
     * and returns an EditVolunteerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditVolunteerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtilVolunteer.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditVolunteerCommand.MESSAGE_USAGE), pe);
        }

        EditVolunteerDescriptor editVolunteerDescriptor = new EditVolunteerDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editVolunteerDescriptor.setName(ParserUtilVolunteer.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editVolunteerDescriptor.setPhone(ParserUtilVolunteer.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editVolunteerDescriptor.setEmail(ParserUtilVolunteer.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editVolunteerDescriptor.setAddress(ParserUtilVolunteer.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        return new EditVolunteerCommand(index, editVolunteerDescriptor);
    }
}