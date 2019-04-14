////@@swalahlah
//package seedu.address.logic.commands.volunteer;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_EMAIL_BOB;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_PHONE_BOB;
//import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_TAG_NEW;
//
//import org.junit.Test;
//
//import seedu.address.logic.commands.volunteer.EditVolunteerCommand.EditVolunteerDescriptor;
//import seedu.address.testutil.volunteer.EditVolunteerDescriptorBuilder;
//
//public class EditVolunteerDescriptorTest {
//
//    @Test
//    public void equals() {
//        // same values -> returns true
//        EditVolunteerDescriptor descriptorWithSameValues = new EditVolunteerDescriptor(DESC_AMY);
//        assertTrue(DESC_AMY.equals(descriptorWithSameValues));
//
//        // same object -> returns true
//        assertTrue(DESC_AMY.equals(DESC_AMY));
//
//        // null -> returns false
//        assertFalse(DESC_AMY.equals(null));
//
//        // different types -> returns false
//        assertFalse(DESC_AMY.equals(5));
//
//        // different values -> returns false
//        assertFalse(DESC_AMY.equals(DESC_BOB));
//
//        // different name -> returns false
//        EditVolunteerDescriptor editedAmy =
//                new EditVolunteerDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
//        assertFalse(DESC_AMY.equals(editedAmy));
//
//        // different phone -> returns false
//        editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
//        assertFalse(DESC_AMY.equals(editedAmy));
//
//        // different email -> returns false
//        editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
//        assertFalse(DESC_AMY.equals(editedAmy));
//
//        // different address -> returns false
//        editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
//        assertFalse(DESC_AMY.equals(editedAmy));
//
//        // different tags -> returns false
//        editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_NEW).build();
//        assertFalse(DESC_AMY.equals(editedAmy));
//    }
//}
