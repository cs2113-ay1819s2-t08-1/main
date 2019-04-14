//package seedu.address.testutil.volunteer;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import seedu.address.model.tag.Tag;
//import seedu.address.model.util.VolunteerSampleDataUtil;
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
// * A utility class to help with building Volunteer objects.
// */
//public class VolunteerBuilder {
//
//    public static final String DEFAULT_NAME = "Alice Pauline";
//    public static final String DEFAULT_AGE = "19";
//    public static final String DEFAULT_GENDER = "F";
//    public static final String DEFAULT_RACE = "American";
//
//    public static final String DEFAULT_RELIGION = "Christian";
//    public static final String DEFAULT_PHONE = "85355255";
//    public static final String DEFAULT_EMAIL = "alice@gmail.com";
//    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
//    public static final String DEFAULT_EMERGENCY_CONTACT = "Mary 98125555";
//    public static final String DEFAULT_DIETARY_PREFERENCE = "Alice Pauline";
//    public static final String DEFAULT_MEDICAL_CONDITION = "NIL";
//
//    private Name name;
//    private Age age;
//    private Gender gender;
//    private Race race;
//    private Religion religion;
//    private Phone phone;
//    private Address address;
//    private Email email;
//    private EmergencyContact emergencyContact;
//    private DietaryPreference dietaryPreference;
//    private MedicalCondition medicalCondition;
//    private Set<Tag> tags;
//
//    public VolunteerBuilder() {
//        name = new Name(DEFAULT_NAME);
//        age = new Age(DEFAULT_AGE);
//        gender = new Gender(DEFAULT_GENDER);
//        race = new Race(DEFAULT_RACE);
//        religion = new Religion(DEFAULT_RELIGION);
//        phone = new Phone(DEFAULT_PHONE);
//        address = new Address(DEFAULT_ADDRESS);
//        email = new Email(DEFAULT_EMAIL);
//        emergencyContact = new EmergencyContact(DEFAULT_EMERGENCY_CONTACT);
//        dietaryPreference = new DietaryPreference(DEFAULT_DIETARY_PREFERENCE);
//        medicalCondition = new MedicalCondition(DEFAULT_MEDICAL_CONDITION);
//        tags = new HashSet<>();
//    }
//
//    /**
//     * Initializes the VolunteerBuilder with the data of {@code volunteerToCopy}.
//     */
//    public VolunteerBuilder(Volunteer volunteerToCopy) {
//        name = volunteerToCopy.getName();
//        age = volunteerToCopy.getAge();
//        gender = volunteerToCopy.getGender();
//        race = volunteerToCopy.getRace();
//        religion = volunteerToCopy.getReligion();
//        phone = volunteerToCopy.getPhone();
//        email = volunteerToCopy.getEmail();
//        address = volunteerToCopy.getAddress();
//        emergencyContact = volunteerToCopy.getEmergencyContact();
//        dietaryPreference = volunteerToCopy.getDietaryPreference();
//        medicalCondition = volunteerToCopy.getMedicalCondition();
//        tags = new HashSet<>(volunteerToCopy.getTags());
//    }
//
//    /**
//     * Sets the {@code Name} of the {@code Volunteer} that we are building.
//     */
//    public VolunteerBuilder withName(String name) {
//        this.name = new Name(name);
//        return this;
//    }
//
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Volunteer} that we are building.
//     */
//    public VolunteerBuilder withTags(String... tags) {
//        this.tags = VolunteerSampleDataUtil.getTagSet(tags);
//        return this;
//    }
//
//    /**
//     * build with age.
//     *
//     * @param age
//     * @return
//     */
//    public VolunteerBuilder withAge(String age) {
//        this.age = new Age(age);
//        return this;
//    }
//
//    /**
//     * guild with gender.
//     *
//     * @param gender
//     * @return
//     */
//    public VolunteerBuilder withGender(String gender) {
//        this.gender = new Gender(gender);
//        return this;
//    }
//
//    /**
//     * build with race.
//     *
//     * @param race
//     * @return
//     */
//    public VolunteerBuilder withRace(String race) {
//        this.race = new Race(race);
//        return this;
//    }
//
//    /**
//     * build with religion
//     *
//     * @param religion
//     * @return
//     */
//    public VolunteerBuilder withReligion(String religion) {
//        this.religion = new Religion(religion);
//        return this;
//    }
//
//
//    /**
//     * Sets the {@code Address} of the {@code Volunteer} that we are building.
//     */
//    public VolunteerBuilder withAddress(String address) {
//        this.address = new Address(address);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code Volunteer} that we are building.
//     */
//    public VolunteerBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code Volunteer} that we are building.
//     */
//    public VolunteerBuilder withEmail(String email) {
//        this.email = new Email(email);
//        return this;
//    }
//
//    /**
//     * build.
//     * @param medicalCondition
//     * @return
//     */
//    public VolunteerBuilder withMedicalCondition(String medicalCondition) {
//        this.medicalCondition = new MedicalCondition(medicalCondition);
//        return this;
//    }
//
//    /**
//     * build.
//     * @param dietaryPreference
//     * @return
//     */
//    public VolunteerBuilder withDietaryPreference(String dietaryPreference) {
//        this.dietaryPreference = new DietaryPreference(dietaryPreference);
//        return this;
//    }
//
//    /**
//     * build.
//     * @param emergencyContact
//     * @return
//     */
//    public VolunteerBuilder withEmergencyContact(String emergencyContact) {
//        this.emergencyContact = new EmergencyContact(emergencyContact);
//        return this;
//    }
//
//
//    /**
//     * build
//     *
//     * @return
//     */
//    public Volunteer build() {
//        return new Volunteer(name, age, gender, race, religion, phone, address, email,
//            emergencyContact, dietaryPreference, medicalCondition, tags);
//    }
//
//}
