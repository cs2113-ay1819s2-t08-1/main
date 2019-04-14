//@@author ndhuu
package seedu.address.testutil.beneficiary;

import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_BABES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.beneficiary.Beneficiary;

/**
 * A utility class containing a list of {@code Beneficiary} objects to be used in tests.
 */
public class TypicalBeneficiaries {

    public static final Beneficiary ACTION_FOR_AIDS = new BeneficiaryBuilder()
        .withName("Action for AIDS")
        .withAddress("123, Jurong West Ave 6, #08-111")
        .withEmail("AforA@example.com")
        .withPhone("94351253").build();
    public static final Beneficiary BEYOND_SOCIAL_SERVICES = new BeneficiaryBuilder()
        .withName("Beyond Social Services")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("bys@example.com")
        .withPhone("98765432").build();
    public static final Beneficiary FOOD_FROM_HEART = new BeneficiaryBuilder()
        .withName("Food from the heart")
        .withPhone("95352563")
        .withEmail("foodHeart@example.com")
        .withAddress("wall street").build();
    public static final Beneficiary AWWA = new BeneficiaryBuilder()
        .withName("Asian Women Welfare Association")
        .withPhone("87652533")
        .withEmail("awwa@example.com")
        .withAddress("10th street").build();
    public static final Beneficiary MAKE_WISH = new BeneficiaryBuilder()
        .withName("Make A Wish Foundation")
        .withPhone("9482224")
        .withEmail("wish@example.com")
        .withAddress("michegan ave").build();

    // Manually added
    public static final Beneficiary AIDHA = new BeneficiaryBuilder()
        .withName("Aidha").withPhone("8482424")
        .withEmail("aidHa@example.com")
        .withAddress("little india").build();
    public static final Beneficiary MIGRANT_WORKERS = new BeneficiaryBuilder()
        .withName("Migrant Workers").withPhone("8482131")
        .withEmail("migrantWorkers@example.com")
        .withAddress("chicago ave").build();

    // Manually added - Beneficiary's details found in {@code BeneficiaryBeneficiaryCommandTestUtil}
    public static final Beneficiary ANIMAL_SHELTER = new BeneficiaryBuilder()
        .withName(VALID_NAME_ANIMAL_SHELTER).withPhone(VALID_PHONE_ANIMAL_SHELTER)
        .withEmail(VALID_EMAIL_ANIMAL_SHELTER).withAddress(VALID_ADDRESS_ANIMAL_SHELTER).build();
    public static final Beneficiary BABES = new BeneficiaryBuilder()
        .withName(VALID_NAME_BABES).withPhone(VALID_PHONE_BABES)
        .withEmail(VALID_EMAIL_BABES).withAddress(VALID_ADDRESS_BABES).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBeneficiaries() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical beneficiaries.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Beneficiary beneficiary : getTypicalBeneficiaries()) {
            ab.addBeneficiary(beneficiary);
        }
        return ab;
    }

    public static List<Beneficiary> getTypicalBeneficiaries() {
        return new ArrayList<>(Arrays.asList(ACTION_FOR_AIDS,
            BEYOND_SOCIAL_SERVICES, FOOD_FROM_HEART, AWWA, MAKE_WISH));
    }
}
