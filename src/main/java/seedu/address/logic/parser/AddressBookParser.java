package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportVolunteerCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.MapCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;

import seedu.address.logic.commands.beneficiary.AddBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.DeleteBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.FindBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.ListBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.SummaryBeneficiaryCommand;
import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.logic.commands.project.AssignBeneficiaryCommand;
import seedu.address.logic.commands.project.AssignVolunteerCommand;
import seedu.address.logic.commands.project.CompleteCommand;
import seedu.address.logic.commands.project.DeleteProjectCommand;
import seedu.address.logic.commands.project.ListProjectCommand;
import seedu.address.logic.commands.volunteer.AddVolunteerCommand;
import seedu.address.logic.commands.volunteer.DeleteVolunteerCommand;
import seedu.address.logic.commands.volunteer.EditVolunteerCommand;
import seedu.address.logic.commands.volunteer.FindVolunteerCommand;
import seedu.address.logic.commands.volunteer.ListVolunteerCommand;
import seedu.address.logic.parser.beneficiary.AddBeneficiaryCommandParser;
import seedu.address.logic.parser.beneficiary.DeleteBeneficiaryCommandParser;
import seedu.address.logic.parser.beneficiary.EditBeneficiaryCommandParser;
import seedu.address.logic.parser.beneficiary.FindBeneficiaryCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddProjectCommand.COMMAND_WORD:
            return new AddProjectCommandParser().parse(arguments);

        case AddBeneficiaryCommand.COMMAND_WORD:
        case AddBeneficiaryCommand.COMMAND_WORD_ALIAS:
            return new AddBeneficiaryCommandParser().parse(arguments);

        case AddVolunteerCommand.COMMAND_WORD:
        case AddVolunteerCommand.COMMAND_ALIAS:
            return new AddVolunteerCommandParser().parse(arguments);

        case SummaryBeneficiaryCommand.COMMAND_WORD:
        case SummaryBeneficiaryCommand.COMMAND_WORD_ALIAS:
            return new SummaryBeneficiaryCommand();

        case AssignBeneficiaryCommand.COMMAND_WORD:
            return new AssignBeneficiaryCommandParser().parse(arguments);

        case AssignVolunteerCommand.COMMAND_WORD:
            return new AssignVolunteerCommandParser().parse(arguments);

        case EditBeneficiaryCommand.COMMAND_WORD:
        case EditBeneficiaryCommand.COMMAND_WORD_ALIAS:
            return new EditBeneficiaryCommandParser().parse(arguments);

        case EditVolunteerCommand.COMMAND_WORD:
        case EditVolunteerCommand.COMMAND_ALIAS:
            return new EditVolunteerCommandParser().parse(arguments);

        case DeleteBeneficiaryCommand.COMMAND_WORD:
        case DeleteBeneficiaryCommand.COMMAND_WORD_ALIAS:
            return new DeleteBeneficiaryCommandParser().parse(arguments);

        case DeleteProjectCommand.COMMAND_WORD:
            return new DeleteProjectCommandParser().parse(arguments);

        case DeleteVolunteerCommand.COMMAND_WORD:
        case DeleteVolunteerCommand.COMMAND_ALIAS:
            return new DeleteVolunteerCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindBeneficiaryCommand.COMMAND_WORD:
        case FindBeneficiaryCommand.COMMAND_WORD_ALIAS:
            return new FindBeneficiaryCommandParser().parse(arguments);

        case FindVolunteerCommand.COMMAND_WORD:
        case FindVolunteerCommand.COMMAND_ALIAS:
            return new FindVolunteerCommandParser().parse(arguments);

        case ListProjectCommand.COMMAND_WORD:
            return new ListProjectCommand();

        case ListBeneficiaryCommand.COMMAND_WORD:
        case ListBeneficiaryCommand.COMMAND_WORD_ALIAS:
            return new ListBeneficiaryCommand();

        case ListVolunteerCommand.COMMAND_WORD:
        case ListVolunteerCommand.COMMAND_ALIAS:
            return new ListVolunteerCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case CompleteCommand.COMMAND_WORD:
            return new CompleteCommandParser().parse(arguments);

        case MapCommand.COMMAND_WORD:
            return new MapCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        case ExportVolunteerCommand.COMMAND_WORD:
            return new ExportVolunteerCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
