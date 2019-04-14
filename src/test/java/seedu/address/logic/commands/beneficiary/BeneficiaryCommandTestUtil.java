//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.NameContainsKeywordsPredicate;
import seedu.address.testutil.beneficiary.EditBeneficiaryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class BeneficiaryCommandTestUtil {

    public static final String VALID_NAME_ANIMAL_SHELTER = "Animal Shelter";
    public static final String VALID_NAME_BABES = "Babes";
    public static final String VALID_PHONE_ANIMAL_SHELTER = "11111111";
    public static final String VALID_PHONE_BABES = "22222222";
    public static final String VALID_EMAIL_ANIMAL_SHELTER = "animal@example.com";
    public static final String VALID_EMAIL_BABES = "babes@example.com";
    public static final String VALID_ADDRESS_ANIMAL_SHELTER = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BABES = "Block 123, Bobby Street 3";

    public static final String NAME_DESC_ANIMAL_SHELTER = " " + PREFIX_NAME + VALID_NAME_ANIMAL_SHELTER;
    public static final String NAME_DESC_BABES = " " + PREFIX_NAME + VALID_NAME_BABES;
    public static final String PHONE_DESC_ANIMAL_SHELTER = " " + PREFIX_PHONE + VALID_PHONE_ANIMAL_SHELTER;
    public static final String PHONE_DESC_BABES = " " + PREFIX_PHONE + VALID_PHONE_BABES;
    public static final String EMAIL_DESC_ANIMAL_SHELTER = " " + PREFIX_EMAIL + VALID_EMAIL_ANIMAL_SHELTER;
    public static final String EMAIL_DESC_BABES = " " + PREFIX_EMAIL + VALID_EMAIL_BABES;
    public static final String ADDRESS_DESC_ANIMAL_SHELTER = " " + PREFIX_ADDRESS + VALID_ADDRESS_ANIMAL_SHELTER;
    public static final String ADDRESS_DESC_BABES = " " + PREFIX_ADDRESS + VALID_ADDRESS_BABES;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditBeneficiaryCommand.EditBeneficiaryDescriptor DESC_ANIMAL_SHELTER;
    public static final EditBeneficiaryCommand.EditBeneficiaryDescriptor DESC_BABES;

    static {
        DESC_ANIMAL_SHELTER = new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_ANIMAL_SHELTER)
                .withPhone(VALID_PHONE_ANIMAL_SHELTER).withEmail(VALID_EMAIL_ANIMAL_SHELTER)
                .withAddress(VALID_ADDRESS_ANIMAL_SHELTER).build();
        DESC_BABES = new EditBeneficiaryDescriptorBuilder().withName(VALID_NAME_BABES)
                .withPhone(VALID_PHONE_BABES).withEmail(VALID_EMAIL_BABES)
                .withAddress(VALID_ADDRESS_BABES).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered beneficiary list
     * and selected beneficiary in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Beneficiary> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBeneficiaryList());
        Beneficiary expectedSelectedBeneficiary = actualModel.getSelectedBeneficiary();

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredBeneficiaryList());
            assertEquals(expectedSelectedBeneficiary, actualModel.getSelectedBeneficiary());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the beneficiary at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showBeneficiaryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBeneficiaryList().size());

        Beneficiary beneficiary = model.getFilteredBeneficiaryList().get(targetIndex.getZeroBased());
        final String[] splitName = beneficiary.getName().fullName.split("\\s+");
        model.updateFilteredBeneficiaryList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBeneficiaryList().size());
    }

    /**
     * Deletes the first beneficiary in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstBeneficiary(Model model) {
        Beneficiary firstBeneficiary = model.getFilteredBeneficiaryList().get(0);
        model.deleteBeneficiary(firstBeneficiary);
        model.commitAddressBook();
    }

}
