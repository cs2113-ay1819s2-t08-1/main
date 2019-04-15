package seedu.address.logic.commands.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_ADDRESS;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_AGE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_DIETARY_PREFERENCE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_EMAIL;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_GENDER;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_NAME;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_PHONE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_RACE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_RELIGION;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.volunteer.Volunteer;


/**
 * Adds a volunteer to the address book.
 */

public class AddVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "addVolunteer";
    public static final String COMMAND_ALIAS = "av";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_AGE + "AGE "
        + PREFIX_GENDER + "GENDER "
        + PREFIX_RACE + "RACE "
        + "[" + PREFIX_RELIGION + "RELIGION] "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_ADDRESS + "ADDRESS "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_EMERGENCY_CONTACT + "EMERGENCY CONTACT] "
        + "[" + PREFIX_DIETARY_PREFERENCE + "DIETARY PREFERENCE] "
        + "[" + PREFIX_MEDICAL_CONDITION + "MEDICAL CONDITION] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_AGE + "22 "
        + PREFIX_GENDER + "M "
        + PREFIX_RACE + "French "
        + PREFIX_RELIGION + "Christian "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + PREFIX_EMAIL + "johnd@example.com "
        + PREFIX_EMERGENCY_CONTACT + "Sally Mother 91234567 "
        + PREFIX_DIETARY_PREFERENCE + "nil "
        + PREFIX_MEDICAL_CONDITION + "nil "
        + PREFIX_TAG + "newVolunteer";

    public static final String MESSAGE_SUCCESS = "New volunteer added: %1$s";
    public static final String MESSAGE_DUPLICATE_VOLUNTEER = "This volunteer already exists in the address book";

    private final Volunteer toAdd;

    public AddVolunteerCommand(Volunteer volunteer) {
        requireNonNull(volunteer);
        toAdd = volunteer;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasVolunteer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_VOLUNTEER);
        }

        model.addVolunteer(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddVolunteerCommand // instanceof handles nulls
            && toAdd.equals(((AddVolunteerCommand) other).toAdd));
    }
}

