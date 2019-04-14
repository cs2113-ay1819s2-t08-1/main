package seedu.address.testutil.project;

import static seedu.address.testutil.volunteer.TypicalVolunteers.getTypicalVolunteers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.project.ProjectBuilder;
import seedu.address.model.AddressBook;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.beneficiary.BeneficiaryBuilder;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {
    public static final Project PROJECT1 = new ProjectBuilder().withProjectTitle("Sunshine")
        .withProjectDate("16/01/2020").withComplete(false).build();
    public static final Project REPEATED_PROJECT1 = new ProjectBuilder().withProjectTitle("Sunshine")
        .withProjectDate("16/01/2020").withComplete(false).build();
    public static final Project EDITED_PROJECT1 = new ProjectBuilder().withProjectTitle("OldFolks")
        .withProjectDate("16/01/2020").withComplete(false).build();
    public static final Project PROJECT2 = new ProjectBuilder().withProjectTitle("iReject")
        .withProjectDate("12/08/2019").withComplete(false).build();
    public static final Beneficiary ACTION_FOR_AIDS = new BeneficiaryBuilder()
        .withName("Action for AIDS")
        .withAddress("123, Jurong West Ave 6, #08-111")
        .withEmail("AforA@example.com")
        .withPhone("94351253").build();
    public static final Beneficiary BEYOND_SOCIAL_SERVICES = new BeneficiaryBuilder()
        .withName("Beyond Social Services")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("bys@example.com")
        .withPhone("98765432").build();

    //manually added
    public static final Project PROJECT3 = new ProjectBuilder().withProjectTitle("Save the Earth")
        .withProjectDate("02/04/2020").withComplete(false).build();

    private TypicalProjects() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        for (Beneficiary beneficiary : getTypicalBeneficiaries()) {
            ab.addBeneficiary(beneficiary);
        }
        for (Volunteer volunteer : getTypicalVolunteers()) {
            ab.addVolunteer(volunteer);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(PROJECT1, PROJECT2));
    }

    public static List<Beneficiary> getTypicalBeneficiaries() {
        return new ArrayList<>(Arrays.asList(ACTION_FOR_AIDS,
            BEYOND_SOCIAL_SERVICES));
    }
}
