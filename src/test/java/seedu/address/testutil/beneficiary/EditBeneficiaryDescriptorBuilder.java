//@@author ndhuu
package seedu.address.testutil.beneficiary;

import java.util.Set;

import seedu.address.logic.commands.beneficiary.EditBeneficiaryCommand.EditBeneficiaryDescriptor;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;
import seedu.address.model.project.ProjectTitle;

/**
 * A utility class to help with building EditBeneficiaryDescriptor objects.
 */
public class EditBeneficiaryDescriptorBuilder {

    private EditBeneficiaryDescriptor descriptor;

    public EditBeneficiaryDescriptorBuilder() {
        descriptor = new EditBeneficiaryDescriptor();
    }

    public EditBeneficiaryDescriptorBuilder(EditBeneficiaryDescriptor descriptor) {
        this.descriptor = new EditBeneficiaryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBeneficiaryDescriptor} with fields containing {@code Beneficiary}'s details
     */
    public EditBeneficiaryDescriptorBuilder(Beneficiary beneficiary) {
        descriptor = new EditBeneficiaryDescriptor();
        descriptor.setName(beneficiary.getName());
        descriptor.setPhone(beneficiary.getPhone());
        descriptor.setEmail(beneficiary.getEmail());
        descriptor.setAddress(beneficiary.getAddress());
        descriptor.setAttachedProjectList(beneficiary.getAttachedProjectLists());
    }

    /**
     * Sets the {@code Name} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code attachedProjectList} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withProjectList(Set<ProjectTitle> projectList) {
        descriptor.setAttachedProjectList(projectList);
        return this;
    }

    public EditBeneficiaryDescriptor build() {
        return descriptor;
    }
}
