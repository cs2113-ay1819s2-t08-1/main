//@@author ndhuu
package seedu.address.model.beneficiary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_BABES;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.ANIMAL_SHELTER;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.BABES;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.beneficiary.BeneficiaryBuilder;

public class BeneficiaryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Beneficiary beneficiary = new BeneficiaryBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        beneficiary.getAttachedProjectLists().remove(0);
    }

    @Test
    public void isSameBeneficiary() {
        // same object -> returns true
        assertTrue(ANIMAL_SHELTER.isSameBeneficiary(ANIMAL_SHELTER));

        // null -> returns false
        assertFalse(ANIMAL_SHELTER.isSameBeneficiary(null));

        // same name, different attribute -> return true
        assertTrue(ANIMAL_SHELTER.isSameBeneficiary(
            new BeneficiaryBuilder(BABES).withName(VALID_NAME_ANIMAL_SHELTER).build()));

        // different name and different phone or email -> returns false
        Beneficiary differentPhone = new BeneficiaryBuilder(BABES).withPhone(VALID_PHONE_BABES)
            .withEmail(VALID_EMAIL_ANIMAL_SHELTER).build();
        assertFalse(ANIMAL_SHELTER.isSameBeneficiary(differentPhone));

        Beneficiary differentEmail = new BeneficiaryBuilder(BABES).withPhone(VALID_PHONE_ANIMAL_SHELTER)
            .withEmail(VALID_EMAIL_BABES).build();
        assertFalse(ANIMAL_SHELTER.isSameBeneficiary(differentEmail));

        //different name, (same phone and email) different attributes -> returns true
        Beneficiary editedAnimalShelter = new BeneficiaryBuilder(ANIMAL_SHELTER)
            .withName(VALID_NAME_BABES)
            .withAddress(VALID_ADDRESS_BABES).build();
        assertTrue(ANIMAL_SHELTER.isSameBeneficiary(editedAnimalShelter));

        // same name, same email, different attributes -> returns true
        editedAnimalShelter = new BeneficiaryBuilder(ANIMAL_SHELTER).withPhone(VALID_PHONE_BABES)
            .withAddress(VALID_ADDRESS_BABES).build();
        assertTrue(ANIMAL_SHELTER.isSameBeneficiary(editedAnimalShelter));

        // same name, same phone, same email, different attributes -> returns true
        editedAnimalShelter = new BeneficiaryBuilder(ANIMAL_SHELTER).withAddress(VALID_ADDRESS_BABES).build();
        assertTrue(ANIMAL_SHELTER.isSameBeneficiary(editedAnimalShelter));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Beneficiary animalShelterCopy = new BeneficiaryBuilder(ANIMAL_SHELTER).build();
        assertTrue(ANIMAL_SHELTER.equals(animalShelterCopy));

        // same object -> returns true
        assertTrue(ANIMAL_SHELTER.equals(ANIMAL_SHELTER));

        // null -> returns false
        assertFalse(ANIMAL_SHELTER.equals(null));

        // different type -> returns false
        assertFalse(ANIMAL_SHELTER.equals(5));

        // different beneficiary -> returns false
        assertFalse(ANIMAL_SHELTER.equals(BABES));

        // same name -> returns true
        Beneficiary editedAnimalShelter = new BeneficiaryBuilder(BABES).withName(VALID_NAME_ANIMAL_SHELTER).build();
        assertTrue(ANIMAL_SHELTER.equals(editedAnimalShelter));

        // same phone and email -> returns true
        editedAnimalShelter = new BeneficiaryBuilder(BABES)
            .withPhone(VALID_PHONE_ANIMAL_SHELTER).withEmail(VALID_EMAIL_ANIMAL_SHELTER).build();
        assertTrue(ANIMAL_SHELTER.equals(editedAnimalShelter));
    }
}
