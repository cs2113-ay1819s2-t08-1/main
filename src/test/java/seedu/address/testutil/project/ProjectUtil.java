package seedu.address.testutil.project;

import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_PROJECT_TITLE;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_DATE;

import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.model.project.Project;

/**
 * A utility class for project.
 */
public class ProjectUtil {

    /**
     * Returns an add command string for adding the {@code project}.
     */
    public static String getAddCommand(Project project) {
        return AddProjectCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PROJECT_TITLE + project.getProjectTitle().fullTitle + " ");
        sb.append(PREFIX_DATE + project.getProjectDate().fullDate + " ");
        return sb.toString();
    }
}
