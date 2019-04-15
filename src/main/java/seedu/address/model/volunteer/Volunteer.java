package seedu.address.model.volunteer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.project.ProjectTitle;
import seedu.address.model.tag.Tag;

/**
 * Represents a Volunteer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Volunteer {
    // Identity fields
    private final Name name;
    private final Age age;
    private final Race race;
    private final Gender gender;
    private final Religion religion;
    private final Phone phone;
    private final Email email;
    private final EmergencyContact emergencyContact;
    private final Address address;
    private final DietaryPreference dietaryPreference;
    private final MedicalCondition medicalCondition;
    private final Set<Tag> tags = new HashSet<>();
    // Data fields
    private int points;
    private HashSet<ProjectTitle> attachedProjectList = new HashSet<ProjectTitle>();

    /**
     * Every Field must be present except DietaryPreference,
     * MedicalCondition and Religion which is set to nil by default
     */
    public Volunteer(Name name, Age age, Gender gender, Race race, Religion religion, Phone phone, Address address,
                     Email email, EmergencyContact emergencyContact, DietaryPreference dietaryPreference,
                     MedicalCondition medicalCondition, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
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
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
        this.points = 0;
    }

    public Name getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Race getRace() {
        return race;
    }

    public Religion getReligion() {
        return religion;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public DietaryPreference getDietaryPreference() {
        return dietaryPreference;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both volunteers of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two volunteers.
     */
    public boolean isSameVolunteer(Volunteer otherVolunteer) { //change later
        if (otherVolunteer == this) {
            return true;
        }

        return otherVolunteer != null
            && otherVolunteer.getName().equals(getName())
            && (otherVolunteer.getPhone().equals(getPhone()) || otherVolunteer.getEmail().equals(getEmail()));
    }

    /**
     * Adds points to (@code points) during (@code MapCommand)
     */
    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

    /**
     * Returns (@code points)
     */
    public int getPoints() {
        return points;
    }

    /**
     * Resets (@code points)
     */
    public void resetPoints() {
        points = 0;
    }

    /**
     * Returns true if both volunteers have the same identity and data fields.
     * This defines a stronger notion of equality between two volunteers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Volunteer)) {
            return false;
        }

        Volunteer otherVolunteer = (Volunteer) other;
        return otherVolunteer.getName().equals(getName())
            && otherVolunteer.getAge().equals(getAge())
            && otherVolunteer.getRace().equals(getRace())
            && otherVolunteer.getPhone().equals(getPhone())
            && otherVolunteer.getAddress().equals(getAddress())
            && otherVolunteer.getEmail().equals(getEmail())
            && otherVolunteer.getDietaryPreference().equals(getDietaryPreference())
            && otherVolunteer.getMedicalCondition().equals(getMedicalCondition())
            && otherVolunteer.getEmergencyContact().equals(getEmergencyContact())
            && otherVolunteer.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, race, age, phone, address, email,
            emergencyContact, dietaryPreference, medicalCondition, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Age: ")
            .append(getAge())
            .append(" Gender: ")
            .append(getGender())
            .append(" Race: ")
            .append(getRace())
            .append(" Religion: ")
            .append(getReligion())
            .append(" Phone: ")
            .append(getPhone())
            .append(" Email: ")
            .append(getEmail())
            .append(" Address: ")
            .append(getAddress())
            .append("Medical Condition: ")
            .append(getMedicalCondition())
            .append("Emergency Contact: ")
            .append(getEmergencyContact())
            .append("Dietary Preference: ")
            .append(getDietaryPreference())
            .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }


}
