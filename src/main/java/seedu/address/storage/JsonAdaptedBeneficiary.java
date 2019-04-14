//@@author ndhuu
package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;
import seedu.address.model.project.ProjectTitle;

/**
 * Jackson-friendly version of {@link Beneficiary}.
 */
class JsonAdaptedBeneficiary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Beneficiary's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedProjectTitle> attachedProjects = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedBeneficiary} with the given beneficiary details.
     */
    @JsonCreator
    public JsonAdaptedBeneficiary(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                  @JsonProperty("email") String email, @JsonProperty("address") String address,
                                  @JsonProperty("attachedProjects") List<JsonAdaptedProjectTitle> attachedProjects) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (attachedProjects != null) {
            this.attachedProjects.addAll(attachedProjects);
        }
    }

    /**
     * Converts a given {@code Beneficiary} into this class for Jackson use.
     */
    public JsonAdaptedBeneficiary(Beneficiary source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        attachedProjects.addAll(source.getAttachedProjectLists().stream()
            .map(JsonAdaptedProjectTitle::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted beneficiary object into the model's {@code Beneficiary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted beneficiary.
     */
    public Beneficiary toModelType() throws IllegalValueException {
        final List<ProjectTitle> projectTitleList = new ArrayList<>();
        for (JsonAdaptedProjectTitle projectTitle : attachedProjects) {
            projectTitleList.add(projectTitle.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        Beneficiary beneficiary = new Beneficiary(modelName, modelPhone, modelEmail, modelAddress);
        final Set<ProjectTitle> modelProjectList = new HashSet<>(projectTitleList);
        beneficiary.setProjectLists(modelProjectList);

        return beneficiary;
    }

}
