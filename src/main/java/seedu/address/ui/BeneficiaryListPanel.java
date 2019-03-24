package seedu.address.ui;

import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.beneficiary.Beneficiary;

/**
 * Panel containing the list of beneficiaries.
 */
public class BeneficiaryListPanel extends UiPart<Region> {
    private static final String FXML = "BeneficiaryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BeneficiaryListPanel.class);

    @FXML
    private ListView<Beneficiary> beneficiaryListView;

    @FXML
    private Label panelName;

    public BeneficiaryListPanel(ObservableList<Beneficiary> beneficiaryList, ObservableValue<Beneficiary> selectedBeneficiary,
                                Consumer<Beneficiary> onSelectedBeneficiaryChange) {
        super(FXML);
        panelName.setFont(Font.font("Cambria", 32));
        panelName.setTextFill(Color.web("#ADFF2F")) ;
        panelName.setText("Beneficiaries Dummy");
        beneficiaryListView.setItems(beneficiaryList);
        beneficiaryListView.setCellFactory(listView -> new BeneficiaryListViewCell());
        beneficiaryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in beneficiary list panel changed to : '" + newValue + "'");
            onSelectedBeneficiaryChange.accept(newValue);
        });
        selectedBeneficiary.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected beneficiary changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected beneficiary,
            // otherwise we would have an infinite loop.
            if (Objects.equals(beneficiaryListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                beneficiaryListView.getSelectionModel().clearSelection();
            } else {
                int index = beneficiaryListView.getItems().indexOf(newValue);
                beneficiaryListView.scrollTo(index);
                beneficiaryListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Beneficiary} using a {@code BeneficiaryCard}.
     */
    class BeneficiaryListViewCell extends ListCell<Beneficiary> {
        @Override
        protected void updateItem(Beneficiary beneficiary, boolean empty) {
            super.updateItem(beneficiary, empty);

            if (empty || beneficiary == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BeneficiaryCard(beneficiary, getIndex() + 1).getRoot());
            }
        }
    }

}