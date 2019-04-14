//@@author ndhuu
package seedu.address.logic.parser.beneficiary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.beneficiary.AddBeneficiaryCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtilBeneficiary;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddBeneficiaryCommandParser implements Parser<AddBeneficiaryCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBeneficiaryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddBeneficiaryCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtilBeneficiary.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtilBeneficiary.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtilBeneficiary.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtilBeneficiary.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Beneficiary beneficiary = new Beneficiary(name, phone, email, address);

        return new AddBeneficiaryCommand(beneficiary);
    }

}
