//@@author ndhuu
package seedu.address.testutil.beneficiary;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;
import seedu.address.model.project.ProjectTitle;

/**
 * A utility class to help with building Beneficiary objects.
 */
public class BeneficiaryBuilder {

    public static final String DEFAULT_NAME = "Orange Home";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "orangeHomeEmail@orangehome.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<ProjectTitle> attachedProjectList;

    public BeneficiaryBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        attachedProjectList = new HashSet<>();
    }

    /**
     * Initializes the BeneficiaryBuilder with the data of {@code beneficiaryToCopy}.
     */
    public BeneficiaryBuilder(Beneficiary beneficiaryToCopy) {
        name = beneficiaryToCopy.getName();
        phone = beneficiaryToCopy.getPhone();
        email = beneficiaryToCopy.getEmail();
        address = beneficiaryToCopy.getAddress();
        attachedProjectList = new HashSet<>(beneficiaryToCopy.getAttachedProjectLists());
    }

    /**
     * Sets the {@code Name} of the {@code Beneficiary} that we are building.
     */
    public BeneficiaryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Beneficiary} that we are building.
     */
    public BeneficiaryBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Beneficiary} that we are building.
     */
    public BeneficiaryBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Beneficiary} that we are building.
     */
    public BeneficiaryBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Build method to build new beneficiary
     *
     * @return a beneficiary object
     */
    public Beneficiary build() {
        Beneficiary b = new Beneficiary(name, phone, email, address);
        b.setProjectLists(this.attachedProjectList);
        return b;
    }

    /**
     * build method to build new beneficiary with an attached project list
     *
     * @param attachedProjectList
     * @return a beneficiary
     */
    public Beneficiary buildWithProjectList(Set<ProjectTitle> attachedProjectList) {
        Beneficiary beneficiary = build();
        beneficiary.setProjectLists(attachedProjectList);
        return beneficiary;
    }

}
