//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.DESC_ANIMAL_SHELTER;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.DESC_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_EMAIL_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_NAME_BABES;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_PHONE_BABES;
import static seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand.EditBeneficiaryDescriptor;

import org.junit.Test;

import seedu.address.testutil.beneficiary.EditBeneficiaryDescriptorBuilder;

public class EditBeneficiaryDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditBeneficiaryDescriptor descriptorWithSameValues = new EditBeneficiaryDescriptor(DESC_ANIMAL_SHELTER);
        assertTrue(DESC_ANIMAL_SHELTER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ANIMAL_SHELTER.equals(DESC_ANIMAL_SHELTER));

        // null -> returns false
        assertFalse(DESC_ANIMAL_SHELTER.equals(null));

        // different types -> returns false
        assertFalse(DESC_ANIMAL_SHELTER.equals(5));

        // different values -> returns false
        assertFalse(DESC_ANIMAL_SHELTER.equals(DESC_BABES));

        // different name -> returns false
        EditBeneficiaryDescriptor editedAnimalShelter =
            new EditBeneficiaryDescriptorBuilder(DESC_ANIMAL_SHELTER).withName(VALID_NAME_BABES).build();
        assertFalse(DESC_ANIMAL_SHELTER.equals(editedAnimalShelter));

        // different phone -> returns false
        editedAnimalShelter = new EditBeneficiaryDescriptorBuilder(DESC_ANIMAL_SHELTER)
            .withPhone(VALID_PHONE_BABES).build();
        assertFalse(DESC_ANIMAL_SHELTER.equals(editedAnimalShelter));

        // different email -> returns false
        editedAnimalShelter = new EditBeneficiaryDescriptorBuilder(DESC_ANIMAL_SHELTER)
            .withEmail(VALID_EMAIL_BABES).build();
        assertFalse(DESC_ANIMAL_SHELTER.equals(editedAnimalShelter));

        // different address -> returns false
        editedAnimalShelter = new EditBeneficiaryDescriptorBuilder(DESC_ANIMAL_SHELTER)
            .withAddress(VALID_ADDRESS_BABES).build();
        assertFalse(DESC_ANIMAL_SHELTER.equals(editedAnimalShelter));
    }
}
