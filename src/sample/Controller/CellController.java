package sample.Controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private ImageView deleteButton;

    @FXML
    private ImageView extend_button;
    private ListController listController = new ListController();

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        DropShadow shadow = new DropShadow(10, Color.WHITE);
        extend_button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->
                extend_button.setEffect(shadow));
        extend_button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->
                extend_button.setEffect(null));
        deleteButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->
                deleteButton.setEffect(shadow));
        deleteButton.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->
                deleteButton.setEffect(null));
    }

    @Override
    public void updateItem(Task myTask, boolean empty) {
        databaseHandler = new DatabaseHandler(); //main change
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
            task_id.setText(Integer.toString(myTask.getTaskId()));
            date_id.setText(myTask.getDatecreated().toString());
            int taskId = myTask.getTaskId();
            extend_button.setOnMouseClicked(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/updateTaskForm.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                UpdateTaskController updateTaskController = loader.getController();
                updateTaskController.setTaskField(myTask.getTask());
                stage.show();
            });

            deleteButton.setOnMouseClicked(event -> {

                try {

                    databaseHandler.deleteTask(AddItemController.userID, taskId);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                getListView().getItems().remove(getItem());

            });
            setText(null);
            setGraphic(root_id);
        }

    }
}