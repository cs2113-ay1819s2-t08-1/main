//@@author ndhuu
package seedu.address.testutil.beneficiary;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.beneficiary.AddBeneficiaryCommand;
import seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand.EditBeneficiaryDescriptor;
import seedu.address.model.beneficiary.Beneficiary;

/**
 * A utility class for beneficiary.
 */
public class BeneficiaryUtil {

    /**
     * Returns an add command string for adding the {@code beneficiary}.
     */
    public static String getAddCommand(Beneficiary beneficiary) {
        return AddBeneficiaryCommand.COMMAND_WORD + " " + getbeneficiaryDetails(beneficiary);
    }

    /**
     * Returns the part of command string for the given {@code beneficiary}'s details.
     */
    public static String getbeneficiaryDetails(Beneficiary beneficiary) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + beneficiary.getName().fullName + " ");
        sb.append(PREFIX_PHONE + beneficiary.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + beneficiary.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + beneficiary.getAddress().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditbeneficiaryDescriptor}'s details.
     */
    public static String getEditbeneficiaryDescriptorDetails(EditBeneficiaryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        return sb.toString();
    }
}
