//package seedu.address.model.volunteer;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_TAG_NEW;
//import static seedu.address.testutil.volunteer.TypicalVolunteers.ALICE;
//import static seedu.address.testutil.volunteer.TypicalVolunteers.BOB;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import seedu.address.model.volunteer.exceptions.DuplicateVolunteerException;
//import seedu.address.model.volunteer.exceptions.VolunteerNotFoundException;
//import seedu.address.testutil.volunteer.VolunteerBuilder;
//
//public class UniqueVolunteerListTest {
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    private final UniqueVolunteerList uniqueVolunteerList = new UniqueVolunteerList();
//
//    @Test
//    public void contains_nullVolunteer_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.contains(null);
//    }
//
//    @Test
//    public void contains_volunteerNotInList_returnsFalse() {
//        assertFalse(uniqueVolunteerList.contains(ALICE));
//    }
//
//    @Test
//    public void contains_volunteerInList_returnsTrue() {
//        uniqueVolunteerList.add(ALICE);
//        assertTrue(uniqueVolunteerList.contains(ALICE));
//    }
//
//    @Test
//    public void contains_volunteerWithSameIdentityFieldsInList_returnsTrue() {
//        uniqueVolunteerList.add(ALICE);
//        Volunteer editedAlice = new VolunteerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_NEW)
//            .build();
//        assertTrue(uniqueVolunteerList.contains(editedAlice));
//    }
//
//    @Test
//    public void add_nullVolunteer_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.add(null);
//    }
//
//    @Test
//    public void add_duplicateVolunteer_throwsDuplicateVolunteerException() {
//        uniqueVolunteerList.add(ALICE);
//        thrown.expect(DuplicateVolunteerException.class);
//        uniqueVolunteerList.add(ALICE);
//    }
//
//    @Test
//    public void setVolunteer_nullTargetVolunteer_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.setVolunteer(null, ALICE);
//    }
//
//    @Test
//    public void setVolunteer_nullEditedVolunteer_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.setVolunteer(ALICE, null);
//    }
//
//    @Test
//    public void setVolunteer_targetVolunteerNotInList_throwsVolunteerNotFoundException() {
//        thrown.expect(VolunteerNotFoundException.class);
//        uniqueVolunteerList.setVolunteer(ALICE, ALICE);
//    }
//
//    @Test
//    public void setVolunteer_editedVolunteerIsSameVolunteer_success() {
//        uniqueVolunteerList.add(ALICE);
//        uniqueVolunteerList.setVolunteer(ALICE, ALICE);
//        UniqueVolunteerList expectedUniqueVolunteerList = new UniqueVolunteerList();
//        expectedUniqueVolunteerList.add(ALICE);
//        assertEquals(expectedUniqueVolunteerList, uniqueVolunteerList);
//    }
//
//    @Test
//    public void setVolunteer_editedVolunteerHasSameIdentity_success() {
//        uniqueVolunteerList.add(ALICE);
//        Volunteer editedAlice = new VolunteerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_NEW)
//            .build();
//        uniqueVolunteerList.setVolunteer(ALICE, editedAlice);
//        UniqueVolunteerList expectedUniqueVolunteerList = new UniqueVolunteerList();
//        expectedUniqueVolunteerList.add(editedAlice);
//        assertEquals(expectedUniqueVolunteerList, uniqueVolunteerList);
//    }
//
//    @Test
//    public void setVolunteer_editedVolunteerHasDifferentIdentity_success() {
//        uniqueVolunteerList.add(ALICE);
//        uniqueVolunteerList.setVolunteer(ALICE, BOB);
//        UniqueVolunteerList expectedUniqueVolunteerList = new UniqueVolunteerList();
//        expectedUniqueVolunteerList.add(BOB);
//        assertEquals(expectedUniqueVolunteerList, uniqueVolunteerList);
//    }
//
//    @Test
//    public void setVolunteer_editedVolunteerHasNonUniqueIdentity_throwsDuplicateVolunteerException() {
//        uniqueVolunteerList.add(ALICE);
//        uniqueVolunteerList.add(BOB);
//        thrown.expect(DuplicateVolunteerException.class);
//        uniqueVolunteerList.setVolunteer(ALICE, BOB);
//    }
//
//    @Test
//    public void remove_nullVolunteer_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.remove(null);
//    }
//
//    @Test
//    public void remove_volunteerDoesNotExist_throwsVolunteerNotFoundException() {
//        thrown.expect(VolunteerNotFoundException.class);
//        uniqueVolunteerList.remove(ALICE);
//    }
//
//    @Test
//    public void remove_existingVolunteer_removesVolunteer() {
//        uniqueVolunteerList.add(ALICE);
//        uniqueVolunteerList.remove(ALICE);
//        UniqueVolunteerList expectedUniqueVolunteerList = new UniqueVolunteerList();
//        assertEquals(expectedUniqueVolunteerList, uniqueVolunteerList);
//    }
//
//    @Test
//    public void setVolunteers_nullUniqueVolunteerList_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.setVolunteers((UniqueVolunteerList) null);
//    }
//
//    @Test
//    public void setVolunteers_uniqueVolunteerList_replacesOwnListWithProvidedUniqueVolunteerList() {
//        uniqueVolunteerList.add(ALICE);
//        UniqueVolunteerList expectedUniqueVolunteerList = new UniqueVolunteerList();
//        expectedUniqueVolunteerList.add(BOB);
//        uniqueVolunteerList.setVolunteers(expectedUniqueVolunteerList);
//        assertEquals(expectedUniqueVolunteerList, uniqueVolunteerList);
//    }
//
//    @Test
//    public void setVolunteers_nullList_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        uniqueVolunteerList.setVolunteers((List<Volunteer>) null);
//    }
//
//    @Test
//    public void setVolunteers_list_replacesOwnListWithProvidedList() {
//        uniqueVolunteerList.add(ALICE);
//        List<Volunteer> volunteerList = Collections.singletonList(BOB);
//        uniqueVolunteerList.setVolunteers(volunteerList);
//        UniqueVolunteerList expectedUniqueVolunteerList = new UniqueVolunteerList();
//        expectedUniqueVolunteerList.add(BOB);
//        assertEquals(expectedUniqueVolunteerList, uniqueVolunteerList);
//    }
//
//    @Test
//    public void setVolunteers_listWithDuplicateVolunteers_throwsDuplicateVolunteerException() {
//        List<Volunteer> listWithDuplicateVolunteers = Arrays.asList(ALICE, ALICE);
//        thrown.expect(DuplicateVolunteerException.class);
//        uniqueVolunteerList.setVolunteers(listWithDuplicateVolunteers);
//    }
//
//    @Test
//    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
//        thrown.expect(UnsupportedOperationException.class);
//        uniqueVolunteerList.asUnmodifiableObservableList().remove(0);
//    }
//}
