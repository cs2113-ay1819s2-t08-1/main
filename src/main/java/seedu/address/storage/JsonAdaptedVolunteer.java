package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.DietaryPreference;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.EmergencyContact;
import seedu.address.model.volunteer.Gender;
import seedu.address.model.volunteer.MedicalCondition;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Religion;
import seedu.address.model.volunteer.Volunteer;


/**
 * Jackson-friendly version of {@link Volunteer}.
 */
class JsonAdaptedVolunteer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Volunteer's %s field is missing!";

    private final String name;
    private final String age;
    private final String gender;
    private final String race;
    private final String religion;
    private final String phone;
    private final String address;
    private final String email;
    private final String emergencyContact;
    private final String dietaryPreference;
    private final String medicalCondition;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedVolunteer} with the given volunteer details.
     */
    @JsonCreator
    public JsonAdaptedVolunteer(@JsonProperty("name") String name, @JsonProperty("age") String age,
                                @JsonProperty("gender") String gender, @JsonProperty("race") String race,
                                @JsonProperty("religion") String religion, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("address") String address,
                                @JsonProperty("dietaryPreference") String dietaryPreference,
                                @JsonProperty("emergencyContact") String emergencyContact,
                                @JsonProperty("medicalCondition") String medicalCondition,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.race = race;
        this.religion = religion;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.emergencyContact = emergencyContact;
        this.dietaryPreference = dietaryPreference;
        this.medicalCondition = medicalCondition;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Volunteer} into this class for Jackson use.
     */
    public JsonAdaptedVolunteer(Volunteer source) {
        name = source.getName().fullName;
        age = source.getAge().ageOutput;
        gender = source.getGender().genderOutput;
        race = source.getRace().raceOutput;
        religion = source.getReligion().religionOutput;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        emergencyContact = source.getEmergencyContact().value;
        dietaryPreference = source.getDietaryPreference().restriction;
        medicalCondition = source.getMedicalCondition().status;
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted volunteer object into the model's {@code Volunteer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted volunteer.
     */
    public Volunteer toModelType() throws IllegalValueException {
        final List<Tag> volunteerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            volunteerTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Race.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (race == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Race.class.getSimpleName()));
        }
        if (!Race.isValidRace(race)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Race modelRace = new Race(race);

        if (religion == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Race.class.getSimpleName()));
        }
        if (!Religion.isValidReligion(religion)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Religion modelReligion = new Religion(religion);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);


        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (emergencyContact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                EmergencyContact.class.getSimpleName()));
        }
        if (!EmergencyContact.isValidEmergencyContact(emergencyContact)) {
            throw new IllegalValueException(EmergencyContact.MESSAGE_CONSTRAINTS);
        }
        final EmergencyContact modelEmergencyContact = new EmergencyContact(emergencyContact);

        if (dietaryPreference == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DietaryPreference.class.getSimpleName()));
        }
        if (!DietaryPreference.isValidDietaryPreference(dietaryPreference)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final DietaryPreference modelDietaryPreference = new DietaryPreference(dietaryPreference);

        if (medicalCondition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                MedicalCondition.class.getSimpleName()));
        }
        if (!MedicalCondition.isValidMedicalCondition(medicalCondition)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final MedicalCondition modelMedicalCondition = new MedicalCondition(medicalCondition);

        final Set<Tag> modelTags = new HashSet<>(volunteerTags);
        return new Volunteer(modelName, modelAge, modelGender, modelRace, modelReligion, modelPhone,
            modelAddress, modelEmail,
            modelEmergencyContact, modelDietaryPreference, modelMedicalCondition, modelTags);
    }

}
