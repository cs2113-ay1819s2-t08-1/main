package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddBeneficiaryCommand;
import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.commands.AssignBeneficiaryCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.DeleteBeneficiaryCommand;
import seedu.address.logic.commands.DeleteProjectCommand;
import seedu.address.logic.commands.DeleteVolunteerCommand;
import seedu.address.logic.commands.EditBeneficiaryCommand;
import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindBeneficiaryCommand;
import seedu.address.logic.commands.FindVolunteerCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListBeneficiaryCommand;
import seedu.address.logic.commands.ListProjectCommand;
import seedu.address.logic.commands.ListVolunteerCommand;
import seedu.address.logic.commands.MapCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectVolunteerCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SummaryBeneficiaryCommand;
import seedu.address.logic.commands.UndoCommand;
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
            return new AddBeneficiaryCommandParser().parse(arguments);

        case AddVolunteerCommand.COMMAND_WORD:
            return new AddVolunteerCommandParser().parse(arguments);

        case SummaryBeneficiaryCommand.COMMAND_WORD:
            return new SummaryBeneficiaryCommand();

        case AssignBeneficiaryCommand.COMMAND_WORD:
            return new AssignBeneficiaryCommandParser().parse(arguments);

        case EditBeneficiaryCommand.COMMAND_WORD:
            return new EditBeneficiaryCommandParser().parse(arguments);

        case EditVolunteerCommand.COMMAND_WORD:
            return new EditVolunteerCommandParser().parse(arguments);

        case DeleteBeneficiaryCommand.COMMAND_WORD:
            return new DeleteBeneficiaryCommandParser().parse(arguments);

        case DeleteProjectCommand.COMMAND_WORD:
            return new DeleteProjectCommandParser().parse(arguments);

        case DeleteVolunteerCommand.COMMAND_WORD:
            return new DeleteVolunteerCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindBeneficiaryCommand.COMMAND_WORD:
            return new FindBeneficiaryCommandParser().parse(arguments);

        case FindVolunteerCommand.COMMAND_WORD:
            return new FindVolunteerCommandParser().parse(arguments);

        case ListProjectCommand.COMMAND_WORD:
            return new ListProjectCommand();

        case ListBeneficiaryCommand.COMMAND_WORD:
            return new ListBeneficiaryCommand();

        case ListVolunteerCommand.COMMAND_WORD:
            return new ListVolunteerCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case SelectVolunteerCommand.COMMAND_WORD:
            return new SelectVolunteerCommandParser().parse(arguments);

        case CompleteCommand.COMMAND_WORD:
            return new CompleteCommandParser().parse(arguments);

        case MapCommand.COMMAND_WORD:
            return new MapCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

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
