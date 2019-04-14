package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.project.Complete;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDate;
import seedu.address.model.project.ProjectTitle;
import seedu.address.model.volunteer.Volunteer;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectTitle;
    private final String projectDate;
    private final String complete;
    private final String beneficiaryAssigned;
    private final List<JsonAdaptedVolunteer> attachedVolunteers = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("project title") String projectTitle,
                              @JsonProperty("project date") String projectDate,
                              @JsonProperty("complete") String complete,
                              @JsonProperty("attached beneficiary") String beneficiaryAssigned,
                              @JsonProperty("attached volunteers") List<JsonAdaptedVolunteer> attachedVolunteers) {
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        this.complete = complete;
        this.beneficiaryAssigned = beneficiaryAssigned;
        if (attachedVolunteers != null) {
            this.attachedVolunteers.addAll(attachedVolunteers);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectTitle = source.getProjectTitle().fullTitle;
        projectDate = source.getProjectDate().fullDate;
        complete = source.getComplete().toString();
        beneficiaryAssigned = source.getBeneficiaryAssigned().toString();
        attachedVolunteers.addAll(source.getVolunteerList().stream()
            .map(JsonAdaptedVolunteer::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Volunteer> volunteerList = new ArrayList<>();
        for (JsonAdaptedVolunteer volunteer : attachedVolunteers) {
            volunteerList.add(volunteer.toModelType());
        }

        if (projectTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ProjectTitle.class.getSimpleName()));
        }
        if (!ProjectTitle.isValidName(projectTitle)) {
            throw new IllegalValueException(ProjectTitle.MESSAGE_PROJECT_TITLE_CONSTRAINTS);
        }
        final ProjectTitle modelProjectTitle = new ProjectTitle(projectTitle);

        if (projectDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ProjectDate.class.getSimpleName()));
        }
        if (!ProjectDate.isValidDate(projectDate)) {
            throw new IllegalValueException(ProjectDate.MESSAGE_CONSTRAINTS);
        }
        final ProjectDate modelProjectDate = new ProjectDate(projectDate);

        if (complete == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Complete.class.getSimpleName()));
        }
        if (!Complete.isValidBoolean(complete)) {
            throw new IllegalValueException(Complete.MESSAGE_CONSTRAINTS);
        }
        final Complete modelComplete = new Complete(complete);

        if (beneficiaryAssigned == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        final Name modelBeneficiaryAssigned = new Name(beneficiaryAssigned);
        Project project = new Project(modelProjectTitle, modelProjectDate, modelComplete, modelBeneficiaryAssigned);
        final List<Volunteer> modelVolunteerList = new ArrayList<>(volunteerList);
        project.setVolunteerList(modelVolunteerList);

        return project;
    }

}
