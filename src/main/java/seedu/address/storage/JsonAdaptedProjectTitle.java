package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.ProjectTitle;

/**
 * Jackson-friendly version of {@link ProjectTitle}.
 */
class JsonAdaptedProjectTitle {

    private final String projectTitle;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code projectTitle}.
     */
    @JsonCreator
    public JsonAdaptedProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedProjectTitle(ProjectTitle source) {
        projectTitle = source.fullTitle;
    }

    @JsonValue
    public String getProjectTitle() {
        return projectTitle;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ProjectTitle toModelType() throws IllegalValueException {
        if (!ProjectTitle.isValidName(projectTitle)) {
            throw new IllegalValueException(ProjectTitle.MESSAGE_PROJECT_TITLE_CONSTRAINTS);
        }
        return new ProjectTitle(projectTitle);
    }

}
