//@@author quinnzzzzz
package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ProjectTitle {

    public static final String MESSAGE_PROJECT_TITLE_CONSTRAINTS =
        "Titles should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullTitle;

    /**
     * Constructs a {@code ProjectTitle}.
     *
     * @param projectTitle A valid name.
     */
    public ProjectTitle(String projectTitle) {
        requireNonNull(projectTitle);
        checkArgument(isValidName(projectTitle), MESSAGE_PROJECT_TITLE_CONSTRAINTS);
        this.fullTitle = projectTitle;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullTitle;
    }

    @Override
    public int hashCode() {
        return fullTitle.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ProjectTitle// instanceof handles nulls
            && fullTitle.equals(((ProjectTitle) other).fullTitle)); // state check
    }
}
