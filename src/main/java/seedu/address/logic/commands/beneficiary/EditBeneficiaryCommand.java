//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BENEFICIARIES;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.project.ProjectBuilder;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectTitle;

/**
 * Edits the details of an existing beneficiary in the address book.
 */
public class EditBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "editBeneficiary";
    public static final String COMMAND_WORD_ALIAS = "eb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the beneficiary identified "
        + "by the index number used in the displayed beneficiary list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_BENEFICIARY_SUCCESS = "Edited Beneficiary: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BENEFICIARY = "This beneficiary already exists in the address book.";

    private final Index index;
    private final EditBeneficiaryDescriptor editBeneficiaryDescriptor;

    /**
     * @param index                     of the beneficiary in the filtered beneficiary list to edit
     * @param editBeneficiaryDescriptor details to edit the beneficiary with
     */
    public EditBeneficiaryCommand(Index index, EditBeneficiaryDescriptor editBeneficiaryDescriptor) {
        requireNonNull(index);
        requireNonNull(editBeneficiaryDescriptor);

        this.index = index;
        this.editBeneficiaryDescriptor = new EditBeneficiaryDescriptor(editBeneficiaryDescriptor);
    }

    /**
     * Creates and returns a {@code Beneficiary} with the details of {@code beneficiaryToEdit}
     * edited with {@code editBeneficiaryDescriptor}.
     */
    private static Beneficiary createEditedBeneficiary(Beneficiary beneficiaryToEdit,
                                                       EditBeneficiaryDescriptor editBeneficiaryDescriptor) {
        assert beneficiaryToEdit != null;

        Name updatedName = editBeneficiaryDescriptor.getName().orElse(beneficiaryToEdit.getName());
        Phone updatedPhone = editBeneficiaryDescriptor.getPhone().orElse(beneficiaryToEdit.getPhone());
        Email updatedEmail = editBeneficiaryDescriptor.getEmail().orElse(beneficiaryToEdit.getEmail());
        Address updatedAddress = editBeneficiaryDescriptor.getAddress().orElse(beneficiaryToEdit.getAddress());

        return new Beneficiary(updatedName, updatedPhone, updatedEmail, updatedAddress);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Beneficiary> lastShownList = model.getFilteredBeneficiaryList();

        if (exceedBeneficiaryListSize(lastShownList)) {
            throw new CommandException(Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
        }

        //Set edited beneficiary
        Beneficiary beneficiaryToEdit = lastShownList.get(index.getZeroBased());
        Beneficiary editedBeneficiary = createEditedBeneficiary(beneficiaryToEdit, editBeneficiaryDescriptor);
        editedBeneficiary.setProjectLists(beneficiaryToEdit.getAttachedProjectLists());

        if (doesEditCauseDuplication(model, beneficiaryToEdit, editedBeneficiary)) {
            throw new CommandException(MESSAGE_DUPLICATE_BENEFICIARY);
        }

        updateAttachedProjects(model, beneficiaryToEdit, editedBeneficiary);
        updateTargetBeneficiary(model, beneficiaryToEdit, editedBeneficiary);
        saveChanges(model);
        return new CommandResult(String.format(MESSAGE_EDIT_BENEFICIARY_SUCCESS, editedBeneficiary));
    }

    private boolean exceedBeneficiaryListSize(List<Beneficiary> lastShownList) {
        return index.getZeroBased() >= lastShownList.size();
    }

    private void saveChanges(Model model) {
        model.commitAddressBook();
    }

    private void updateTargetBeneficiary(Model model, Beneficiary beneficiaryToEdit, Beneficiary editedBeneficiary) {
        model.setBeneficiary(beneficiaryToEdit, editedBeneficiary);
        model.updateFilteredBeneficiaryList(PREDICATE_SHOW_ALL_BENEFICIARIES);
    }

    /**
     * Check if the edit is valid (no repetition of beneficiaries)
     *
     * @param model
     * @param beneficiaryToEdit
     * @param editedBeneficiary
     * @return
     */
    private boolean doesEditCauseDuplication(
        Model model, Beneficiary beneficiaryToEdit, Beneficiary editedBeneficiary) {
        return beneficiaryToEdit.isSameBeneficiary(editedBeneficiary)
            && model.getFilteredBeneficiaryList().filtered(x -> x.isSameBeneficiary(editedBeneficiary)).size() != 1;
    }

    /**
     * Update the projects attached to the edited beneficiary
     *
     * @param model
     * @param beneficiaryToEdit
     * @param editedBeneficiary
     */
    private void updateAttachedProjects(Model model, Beneficiary beneficiaryToEdit, Beneficiary editedBeneficiary) {
        for (ProjectTitle attachedProject : beneficiaryToEdit.getAttachedProjectLists()) {
            Predicate<Project> equalProjectTitle = x -> x.getProjectTitle().equals(attachedProject);
            if (model.getFilteredProjectList().filtered(equalProjectTitle).size() != 0) {
                Project project = model.getFilteredProjectList().filtered(equalProjectTitle).get(0);
                Project newProject = new ProjectBuilder(project).withBeneficiary(editedBeneficiary.getName()).build();
                model.setProject(project, newProject);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBeneficiaryCommand)) {
            return false;
        }

        // state check
        EditBeneficiaryCommand e = (EditBeneficiaryCommand) other;
        return index.equals(e.index)
            && editBeneficiaryDescriptor.equals(e.editBeneficiaryDescriptor);
    }

    /**
     * Stores the details to edit the beneficiary with. Each non-empty field value will replace the
     * corresponding field value of the beneficiary.
     */
    public static class EditBeneficiaryDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<ProjectTitle> attachedProjectList = new HashSet<>();

        public EditBeneficiaryDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBeneficiaryDescriptor(EditBeneficiaryDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
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

        public void setAttachedProjectList(Set<ProjectTitle> attachedProjectList) {
            this.attachedProjectList = attachedProjectList;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBeneficiaryDescriptor)) {
                return false;
            }

            // state check
            EditBeneficiaryDescriptor e = (EditBeneficiaryDescriptor) other;

            return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress());
        }
    }
}
