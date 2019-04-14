//@@author quinnzzzzz
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.ASSIGNED_PROJECT_TITLE;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.project.AssignBeneficiaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.ProjectTitle;

/**
 * Parses input arguments and creates a new AssignBeneficiaryCommand object
 */
public class AssignBeneficiaryCommandParser implements Parser<AssignBeneficiaryCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AssignBeneficiaryCommand
     * and returns an AssignBeneficiaryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignBeneficiaryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ASSIGNED_PROJECT_TITLE, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, ASSIGNED_PROJECT_TITLE, PREFIX_INDEX)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignBeneficiaryCommand.MESSAGE_USAGE));
        }

        ProjectTitle projectTitle = ParserUtilProject.parseProjectTitle(argMultimap
            .getValue(ASSIGNED_PROJECT_TITLE).get());
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new AssignBeneficiaryCommand(projectTitle, index);
    }
}

