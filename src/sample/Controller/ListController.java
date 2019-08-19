package sample.Controller;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ListController {

    ObservableList<String> listview = FXCollections.observableArrayList(
            "john",
            "Kendy",
            "Toan",
            "Oh yeah",
            "Baby",
            "lets go"
    );
    @FXML
    private JFXListView<String> listtask;

    @FXML
    void initialize() {
        listtask.setItems(listview);
    }

    static class JFXCell extends JFXListCell<String> {
        HBox hBox = new HBox();
        Label taskName = new Label();
        Pane pane = new Pane();

        public JFXCell() {
            super();
            hBox.getChildren().addAll();
        }
    }
}
