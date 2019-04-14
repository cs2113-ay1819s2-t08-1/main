//@@author ndhuu
package seedu.address.model.beneficiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.VALID_ADDRESS_BABES;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.ANIMAL_SHELTER;
import static seedu.address.testutil.beneficiary.TypicalBeneficiaries.BABES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.beneficiary.exceptions.BeneficiaryNotFoundException;
import seedu.address.model.beneficiary.exceptions.DuplicateBeneficiaryException;
import seedu.address.testutil.beneficiary.BeneficiaryBuilder;

public class UniqueBeneficiaryListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueBeneficiaryList uniqueBeneficiaryList = new UniqueBeneficiaryList();

    @Test
    public void contains_nullBeneficiary_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.contains(null);
    }

    @Test
    public void contains_beneficiaryNotInList_returnsFalse() {
        assertFalse(uniqueBeneficiaryList.contains(ANIMAL_SHELTER));
    }

    @Test
    public void contains_beneficiaryInList_returnsTrue() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        assertTrue(uniqueBeneficiaryList.contains(ANIMAL_SHELTER));
    }

    @Test
    public void contains_beneficiaryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        Beneficiary editedAnimalShelter = new BeneficiaryBuilder(ANIMAL_SHELTER)
            .withAddress(VALID_ADDRESS_BABES).build();
        assertTrue(uniqueBeneficiaryList.contains(editedAnimalShelter));
    }

    @Test
    public void add_nullBeneficiary_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.add(null);
    }

    @Test
    public void add_duplicateBeneficiary_throwsDuplicateBeneficiaryException() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        thrown.expect(DuplicateBeneficiaryException.class);
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
    }

    @Test
    public void setBeneficiary_nullTargetBeneficiary_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.setBeneficiary(null, ANIMAL_SHELTER);
    }

    @Test
    public void setBeneficiary_nullEditedBeneficiary_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.setBeneficiary(ANIMAL_SHELTER, null);
    }

    @Test
    public void setBeneficiary_targetBeneficiaryNotInList_throwsBeneficiaryNotFoundException() {
        thrown.expect(BeneficiaryNotFoundException.class);
        uniqueBeneficiaryList.setBeneficiary(ANIMAL_SHELTER, ANIMAL_SHELTER);
    }

    @Test
    public void setBeneficiary_editedBeneficiaryIsSameBeneficiary_success() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        uniqueBeneficiaryList.setBeneficiary(ANIMAL_SHELTER, ANIMAL_SHELTER);
        UniqueBeneficiaryList expectedUniqueBeneficiaryList = new UniqueBeneficiaryList();
        expectedUniqueBeneficiaryList.add(ANIMAL_SHELTER);
        assertEquals(expectedUniqueBeneficiaryList, uniqueBeneficiaryList);
    }

    @Test
    public void setBeneficiary_editedBeneficiaryHasSameIdentity_success() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        Beneficiary editedAnimalShelter = new BeneficiaryBuilder(ANIMAL_SHELTER).withAddress(VALID_ADDRESS_BABES)
            .build();
        uniqueBeneficiaryList.setBeneficiary(ANIMAL_SHELTER, editedAnimalShelter);
        UniqueBeneficiaryList expectedUniqueBeneficiaryList = new UniqueBeneficiaryList();
        expectedUniqueBeneficiaryList.add(editedAnimalShelter);
        assertEquals(expectedUniqueBeneficiaryList, uniqueBeneficiaryList);
    }

    @Test
    public void setBeneficiary_editedBeneficiaryHasDifferentIdentity_success() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        uniqueBeneficiaryList.setBeneficiary(ANIMAL_SHELTER, BABES);
        UniqueBeneficiaryList expectedUniqueBeneficiaryList = new UniqueBeneficiaryList();
        expectedUniqueBeneficiaryList.add(BABES);
        assertEquals(expectedUniqueBeneficiaryList, uniqueBeneficiaryList);
    }

    @Test
    public void setBeneficiary_editedBeneficiaryHasNonUniqueIdentity_throwsDuplicateBeneficiaryException() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        uniqueBeneficiaryList.add(BABES);
        thrown.expect(DuplicateBeneficiaryException.class);
        uniqueBeneficiaryList.setBeneficiary(ANIMAL_SHELTER, BABES);
    }

    @Test
    public void remove_nullBeneficiary_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.remove(null);
    }

    @Test
    public void remove_beneficiaryDoesNotExist_throwsBeneficiaryNotFoundException() {
        thrown.expect(BeneficiaryNotFoundException.class);
        uniqueBeneficiaryList.remove(ANIMAL_SHELTER);
    }

    @Test
    public void remove_existingBeneficiary_removesBeneficiary() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        uniqueBeneficiaryList.remove(ANIMAL_SHELTER);
        UniqueBeneficiaryList expectedUniqueBeneficiaryList = new UniqueBeneficiaryList();
        assertEquals(expectedUniqueBeneficiaryList, uniqueBeneficiaryList);
    }

    @Test
    public void setBeneficiaries_nullUniqueBeneficiaryList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.setBeneficiaries((UniqueBeneficiaryList) null);
    }

    @Test
    public void setBeneficiaries_uniqueBeneficiaryList_replacesOwnListWithProvidedUniqueBeneficiaryList() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        UniqueBeneficiaryList expectedUniqueBeneficiaryList = new UniqueBeneficiaryList();
        expectedUniqueBeneficiaryList.add(BABES);
        uniqueBeneficiaryList.setBeneficiaries(expectedUniqueBeneficiaryList);
        assertEquals(expectedUniqueBeneficiaryList, uniqueBeneficiaryList);
    }

    @Test
    public void setBeneficiaries_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueBeneficiaryList.setBeneficiaries((List<Beneficiary>) null);
    }

    @Test
    public void setBeneficiaries_list_replacesOwnListWithProvidedList() {
        uniqueBeneficiaryList.add(ANIMAL_SHELTER);
        List<Beneficiary> beneficiaryList = Collections.singletonList(BABES);
        uniqueBeneficiaryList.setBeneficiaries(beneficiaryList);
        UniqueBeneficiaryList expectedUniqueBeneficiaryList = new UniqueBeneficiaryList();
        expectedUniqueBeneficiaryList.add(BABES);
        assertEquals(expectedUniqueBeneficiaryList, uniqueBeneficiaryList);
    }

    @Test
    public void setBeneficiaries_listWithDuplicateBeneficiaries_throwsDuplicateBeneficiaryException() {
        List<Beneficiary> listWithDuplicateBeneficiaries = Arrays.asList(ANIMAL_SHELTER, ANIMAL_SHELTER);
        thrown.expect(DuplicateBeneficiaryException.class);
        uniqueBeneficiaryList.setBeneficiaries(listWithDuplicateBeneficiaries);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueBeneficiaryList.asUnmodifiableObservableList().remove(0);
    }
}
