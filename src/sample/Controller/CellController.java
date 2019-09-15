package sample.Controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CellController extends JFXListCell<Task> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane root_id;

    @FXML
    private URL location;

    @FXML
    private Label task_id;

    @FXML
    private Label date_id;

    @FXML
    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {
    }

    @Override
    public void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);
        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            task_id.setText(myTask.getTask());
            date_id.setText(myTask.getDatecreated().toString());
            setText(null);
            setGraphic(root_id);
        }
    }
}