//@@author ndhuu
package seedu.address.logic.commands.beneficiary;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.ProjectTitle;

/**
 * Edits the details of an existing beneficiary in the address book.
 */
public class SummaryBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "summariseBeneficiary";
    public static final String COMMAND_WORD_ALIAS = "sb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Summary statistics details of beneficiary list "
        + "based on projects attached to them";

    public static final String MESSAGE_SUMMARY_BENEFICIARY_SUCCESS = "Beneficiaries are summarised, and shown on "
        + "pop up table";

    /**
     * Summarise beneficiary list
     *
     * @param logic
     * @return
     */
    public static ObservableList<SummarisedBeneficiary> getSummarisedBeneficiaries(Logic logic) {
        List<Beneficiary> beneficiaryList = logic.getFilteredBeneficiaryList();
        List<SummarisedBeneficiary> data0 = new ArrayList<>();
        for (Beneficiary beneficiary : beneficiaryList) {
            data0.add(new SummarisedBeneficiary(beneficiary));
        }
        return FXCollections.observableArrayList(data0);
    }

    /**
     * Make the scene for beneficiary summary table
     *
     * @param stage
     * @param table
     * @param data
     * @return
     */
    public static Scene getScene(Stage stage, TableView<SummarisedBeneficiary> table,
                                 ObservableList<SummarisedBeneficiary> data) {
        Scene scene = new Scene(new Group());
        stage.setTitle("VolunCheer");
        stage.setWidth(1200);
        stage.setHeight(700);

        final Label label = new Label("Beneficiary Summary Table");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Beneficiary Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
            new PropertyValueFactory<SummarisedBeneficiary, String>("name"));

        TableColumn numProjectCol = new TableColumn("No. Projects");
        numProjectCol.setMinWidth(100);
        numProjectCol.setCellValueFactory(
            new PropertyValueFactory<SummarisedBeneficiary, String>("numberOfProjects"));

        TableColumn projListCol = new TableColumn("List of attached projects");
        projListCol.setMinWidth(800);
        projListCol.setCellValueFactory(
            new PropertyValueFactory<SummarisedBeneficiary, List<String>>("projectList"));

        table.setItems(data);
        table.getColumns().addAll(nameCol, numProjectCol, projListCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        Group g = (Group) scene.getRoot();
        g.getChildren().addAll(vbox);
        return scene;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUMMARY_BENEFICIARY_SUCCESS), true);
    }

    /**
     * Class to do summary of beneficiary
     */
    public static class SummarisedBeneficiary {

        private final String name;
        private final String numberOfProjects;
        private final List<String> projectList = new ArrayList<>();

        public SummarisedBeneficiary(Beneficiary beneficiary) {
            this.name = beneficiary.getName().fullName;
            for (ProjectTitle project : beneficiary.getAttachedProjectLists()) {
                this.projectList.add(project.fullTitle);
            }
            this.numberOfProjects = Integer.toString(this.projectList.size());
        }

        public String getName() {
            return name;
        }

        public String getNumberOfProjects() {
            return numberOfProjects;
        }

        public List<String> getProjectList() {
            return projectList;
        }
    }
}
