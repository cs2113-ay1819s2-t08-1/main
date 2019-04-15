package seedu.address.logic.commands.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_ADDRESS;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_AGE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_DIETARY_PREFERENCE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_EMAIL;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_GENDER;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_NAME;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_PHONE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_RACE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_RELIGION;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VOLUNTEERS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
 * Edits the details of an existing volunteer in the address book.
 */
public class EditVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "editVolunteer";
    public static final String COMMAND_ALIAS = "ev";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the volunteer identified "
        + "by the index number used in the displayed volunteer list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_AGE + "AGE] "
        + "[" + PREFIX_GENDER + "AGE] "
        + "[" + PREFIX_RACE + "RACE] "
        + "[" + PREFIX_RELIGION + "AGE] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_EMERGENCY_CONTACT + "NAME, RELATIONSHIP, PHONE] "
        + "[" + PREFIX_DIETARY_PREFERENCE + "PREFERENCE] "
        + "[" + PREFIX_MEDICAL_CONDITION + "STATUS] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_VOLUNTEER_SUCCESS = "Edited Volunteer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_VOLUNTEER = "This volunteer already exists in the address book.";

    private final Index index;
    private final EditVolunteerDescriptor editVolunteerDescriptor;

    /**
     * @param index                   of the volunteer in the filtered volunteer list to edit
     * @param editVolunteerDescriptor details to edit the volunteer with
     */
    public EditVolunteerCommand(Index index, EditVolunteerDescriptor editVolunteerDescriptor) {
        requireNonNull(index);
        requireNonNull(editVolunteerDescriptor);

        this.index = index;
        this.editVolunteerDescriptor = new EditVolunteerDescriptor(editVolunteerDescriptor);
    }

    /**
     * Creates and returns a {@code Volunteer} with the details of {@code volunteerToEdit}
     * edited with {@code editVolunteerDescriptor}.
     */
    private static Volunteer createEditedVolunteer(Volunteer volunteerToEdit,
                                                   EditVolunteerDescriptor editVolunteerDescriptor) {
        assert volunteerToEdit != null;

        Name updatedName = editVolunteerDescriptor.getName().orElse(volunteerToEdit.getName());
        Age updatedAge = editVolunteerDescriptor.getAge().orElse(volunteerToEdit.getAge());
        Gender updatedGender = editVolunteerDescriptor.getGender().orElse(volunteerToEdit.getGender());
        Race updatedRace = editVolunteerDescriptor.getRace().orElse(volunteerToEdit.getRace());
        Religion updatedReligion = editVolunteerDescriptor.getReligion().orElse(volunteerToEdit.getReligion());
        Phone updatedPhone = editVolunteerDescriptor.getPhone().orElse(volunteerToEdit.getPhone());
        Email updatedEmail = editVolunteerDescriptor.getEmail().orElse(volunteerToEdit.getEmail());
        Address updatedAddress = editVolunteerDescriptor.getAddress().orElse(volunteerToEdit.getAddress());
        EmergencyContact updatedEmergencyContact =
            editVolunteerDescriptor.getEmergencyContact().orElse(volunteerToEdit.getEmergencyContact());
        DietaryPreference updatedDietaryPreference =
            editVolunteerDescriptor.getDietaryPreference().orElse(volunteerToEdit.getDietaryPreference());
        MedicalCondition updatedMedicalCondition =
            editVolunteerDescriptor.getMedicalCondition().orElse(volunteerToEdit.getMedicalCondition());
        Set<Tag> updatedTags = editVolunteerDescriptor.getTags().orElse(volunteerToEdit.getTags());

        return new Volunteer(updatedName, updatedAge, updatedGender, updatedRace, updatedReligion, updatedPhone,
            updatedAddress, updatedEmail, updatedEmergencyContact, updatedDietaryPreference,
            updatedMedicalCondition, updatedTags);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Volunteer> lastShownList = model.getFilteredVolunteerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
        }

        Volunteer volunteerToEdit = lastShownList.get(index.getZeroBased());
        Volunteer editedVolunteer = createEditedVolunteer(volunteerToEdit, editVolunteerDescriptor);

        if (!volunteerToEdit.isSameVolunteer(editedVolunteer) && model.hasVolunteer(editedVolunteer)) {
            throw new CommandException(MESSAGE_DUPLICATE_VOLUNTEER);
        }

        model.setVolunteer(volunteerToEdit, editedVolunteer);
        model.updateFilteredVolunteerList(PREDICATE_SHOW_ALL_VOLUNTEERS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_VOLUNTEER_SUCCESS, editedVolunteer));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVolunteerCommand)) {
            return false;
        }

        // state check
        EditVolunteerCommand e = (EditVolunteerCommand) other;
        return index.equals(e.index)
            && editVolunteerDescriptor.equals(e.editVolunteerDescriptor);
    }

    /**
     * Stores the details to edit the volunteer with. Each non-empty field value will replace the
     * corresponding field value of the volunteer.
     */
    public static class EditVolunteerDescriptor {
        private Name name;
        private Age age;
        private Gender gender;
        private Race race;
        private Religion religion;
        private Phone phone;
        private Address address;
        private Email email;
        private EmergencyContact emergencycontact;
        private DietaryPreference dietarypreference;
        private MedicalCondition medicalcondition;
        private Set<Tag> tags;

        public EditVolunteerDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVolunteerDescriptor(EditVolunteerDescriptor toCopy) {
            setName(toCopy.name);
            setAge(toCopy.age);
            setGender(toCopy.gender);
            setRace(toCopy.race);
            setReligion(toCopy.religion);
            setPhone(toCopy.phone);
            setAddress(toCopy.address);
            setEmail(toCopy.email);
            setEmergencyContact(toCopy.emergencycontact);
            setDietaryPreference(toCopy.dietarypreference);
            setMedicalCondition(toCopy.medicalcondition);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Race> getRace() {
            return Optional.ofNullable(race);
        }

        public void setRace(Race race) {
            this.race = race;
        }

        public Optional<Religion> getReligion() {
            return Optional.ofNullable(religion);
        }

        public void setReligion(Religion religion) {
            this.religion = religion;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<EmergencyContact> getEmergencyContact() {
            return Optional.ofNullable(emergencycontact);
        }

        public void setEmergencyContact(EmergencyContact emergencycontact) {
            this.emergencycontact = emergencycontact;
        }

        public Optional<DietaryPreference> getDietaryPreference() {
            return Optional.ofNullable(dietarypreference);
        }

        public void setDietaryPreference(DietaryPreference dietarypreference) {
            this.dietarypreference = dietarypreference;
        }

        public Optional<MedicalCondition> getMedicalCondition() {
            return Optional.ofNullable(medicalcondition);
        }

        public void setMedicalCondition(MedicalCondition medicalcondition) {
            this.medicalcondition = medicalcondition;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditVolunteerDescriptor)) {
                return false;
            }

            // state check
            EditVolunteerDescriptor e = (EditVolunteerDescriptor) other;

            return getName().equals(e.getName())
                && getAge().equals(e.getAge())
                && getGender().equals(e.getGender())
                && getRace().equals(e.getRace())
                && getReligion().equals(e.getReligion())
                && getPhone().equals(e.getPhone())
                && getAddress().equals(e.getAddress())
                && getEmail().equals(e.getEmail())
                && getEmergencyContact().equals(e.getEmergencyContact())
                && getDietaryPreference().equals(e.getDietaryPreference())
                && getMedicalCondition().equals(e.getMedicalCondition())
                && getTags().equals(e.getTags());
        }
    }
}
