package seedu.address.logic.parser.beneficiary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.HARD_DELETE_MODE;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.beneficiary.DeleteBeneficiaryCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtilBeneficiary;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBeneficiaryCommand object
 */
public class DeleteBeneficiaryCommandParser implements Parser<DeleteBeneficiaryCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBeneficiaryCommand
     * and returns an DeleteBeneficiaryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBeneficiaryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_INDEX, HARD_DELETE_MODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteBeneficiaryCommand.MESSAGE_USAGE));
        }
        boolean isHardDeleteMode = arePrefixesPresent(argMultimap, HARD_DELETE_MODE);
        Index index = ParserUtilBeneficiary.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new DeleteBeneficiaryCommand(index, isHardDeleteMode);

    }
}
