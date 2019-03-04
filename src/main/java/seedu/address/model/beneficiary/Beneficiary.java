package seedu.address.model.beneficiary;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Beneficiary in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Beneficiary {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public Beneficiary(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Name getName() {
        return name;
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

    /**
     * Returns true if both Beneficiarys of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Beneficiarys.
     */
    public boolean isSameBeneficiary(Beneficiary otherBeneficiary) {
        if (otherBeneficiary == this) {
            return true;
        }

        return otherBeneficiary != null
                && otherBeneficiary.getName().equals(getName())
                && (otherBeneficiary.getPhone().equals(getPhone()) || otherBeneficiary.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both Beneficiarys have the same identity and data fields.
     * This defines a stronger notion of equality between two Beneficiarys.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Beneficiary)) {
            return false;
        }

        Beneficiary otherBeneficiary = (Beneficiary) other;
        return otherBeneficiary.getName().equals(getName())
                && otherBeneficiary.getPhone().equals(getPhone())
                && otherBeneficiary.getEmail().equals(getEmail())
                && otherBeneficiary.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }

}
