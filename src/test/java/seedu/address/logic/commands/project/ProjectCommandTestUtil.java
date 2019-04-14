package seedu.address.logic.commands.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.NameContainsKeywordsPredicate;
import seedu.address.model.project.Project;

/**
 * Contains helper methods for testing commands.
 */
public class ProjectCommandTestUtil {

    /**
     * Updates {@code model}'s filtered list to show only the project at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());
        Project targetProject = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = targetProject.getProjectTitle().fullTitle.split("\\s+");
        model.updateFilteredProjectList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
    }

    /**
     * Updates {@code model}'s filtered list to show only the beneficiary at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showBeneficiaryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBeneficiaryList().size());

        Beneficiary beneficiary = model.getFilteredBeneficiaryList().get(targetIndex.getZeroBased());
        final String[] splitName = beneficiary.getName().fullName.split("\\s+");
        model.updateFilteredBeneficiaryList(new seedu.address.model.beneficiary.NameContainsKeywordsPredicate(Arrays
            .asList(splitName[0])));

        assertEquals(1, model.getFilteredBeneficiaryList().size());
    }
}

