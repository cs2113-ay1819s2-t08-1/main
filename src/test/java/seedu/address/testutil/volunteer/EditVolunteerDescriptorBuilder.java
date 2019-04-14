//package seedu.address.testutil.volunteer;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import seedu.address.logic.commands.volunteer.EditVolunteerCommand.EditVolunteerDescriptor;
//import seedu.address.model.tag.Tag;
//import seedu.address.model.volunteer.Address;
//import seedu.address.model.volunteer.Age;
//import seedu.address.model.volunteer.DietaryPreference;
//import seedu.address.model.volunteer.Email;
//import seedu.address.model.volunteer.EmergencyContact;
//import seedu.address.model.volunteer.Gender;
//import seedu.address.model.volunteer.MedicalCondition;
//import seedu.address.model.volunteer.Name;
//import seedu.address.model.volunteer.Phone;
//import seedu.address.model.volunteer.Race;
//import seedu.address.model.volunteer.Religion;
//import seedu.address.model.volunteer.Volunteer;
//
///**
// * A utility class to help with building EditVolunteerDescriptor objects.
// */
//public class EditVolunteerDescriptorBuilder {
//
//    private EditVolunteerDescriptor descriptor;
//
//    public EditVolunteerDescriptorBuilder() {
//        descriptor = new EditVolunteerDescriptor();
//    }
//
//    public EditVolunteerDescriptorBuilder(EditVolunteerDescriptor descriptor) {
//        this.descriptor = new EditVolunteerDescriptor(descriptor);
//    }
//
//    /**
//     * Returns an {@code EditVolunteerDescriptor} with fields containing {@code volunteer}'s details
//     */
//    public EditVolunteerDescriptorBuilder(Volunteer volunteer) {
//        descriptor = new EditVolunteerDescriptor();
//        descriptor.setName(volunteer.getName());
//        descriptor.setAge(volunteer.getAge());
//        descriptor.setGender(volunteer.getGender());
//        descriptor.setRace(volunteer.getRace());
//        descriptor.setReligion(volunteer.getReligion());
//        descriptor.setPhone(volunteer.getPhone());
//        descriptor.setEmail(volunteer.getEmail());
//        descriptor.setAddress(volunteer.getAddress());
//        descriptor.setEmergencyContact(volunteer.getEmergencyContact());
//        descriptor.setDietaryPreference(volunteer.getDietaryPreference());
//        descriptor.setMedicalCondition(volunteer.getMedicalCondition());
//        descriptor.setTags(volunteer.getTags());
//    }
//
//    /**
//     * Sets the {@code Name} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withName(String name) {
//        descriptor.setName(new Name(name));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Age} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withAge(String age) {
//        descriptor.setAge(new Age(age));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Gender} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withGender(String gender) {
//        descriptor.setGender(new Gender(gender));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Race} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withRace() {
//        return withRace();
//    }
//
//    /**
//     * Sets the {@code Race} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withRace(String race) {
//        descriptor.setRace(new Race(race));
//        return this;
//    }
//    /**
//     * Sets the {@code Religion} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withReligion(String religion) {
//        descriptor.setReligion(new Religion(religion));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withPhone(String phone) {
//        descriptor.setPhone(new Phone(phone));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withEmail(String email) {
//        descriptor.setEmail(new Email(email));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Address} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withAddress(String address) {
//        descriptor.setAddress(new Address(address));
//        return this;
//    }
//
//    /**
//     * Sets the {@code Medical Condition} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withMedicalCondition(String medicalCondition) {
//        descriptor.setMedicalCondition(new MedicalCondition(medicalCondition));
//        return this;
//    }
//    /**
//     * Sets the {@code Emergency Contact} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withEmergencyContact(String emergencyContact) {
//        descriptor.setEmergencyContact(new EmergencyContact(emergencyContact));
//        return this;
//    }
//    /**
//     * Sets the {@code Dietary Preference} of the {@code EditVolunteerDescriptor} that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withDietaryPreference(String dietaryPreference) {
//        descriptor.setDietaryPreference(new DietaryPreference(dietaryPreference));
//        return this;
//    }
//
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditVolunteerDescriptor}
//     * that we are building.
//     */
//    public EditVolunteerDescriptorBuilder withTags(String... tags) {
//        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
//        descriptor.setTags(tagSet);
//        return this;
//    }
//
//    public EditVolunteerDescriptor build() {
//        return descriptor;
//    }
//}
