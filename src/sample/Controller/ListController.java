package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.methods.SwitchScene;
import sample.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ListController {
    @FXML
    private JFXListView<Task> listtask;

    @FXML
    private JFXButton back_button_list;

    private ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();
        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(new AddItemController().getUserID());
        while (resultSet.next()) {
            Task task = new Task();
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            tasks.addAll(task);
        }

        System.out.println("User .... Id: " + new AddItemController().getUserID());

        listtask.setItems(tasks);
        listtask.setCellFactory(CellController -> new CellController());
        back_button_list.setOnAction(event -> {
            back_button_list.getScene().getWindow().hide();
            SwitchScene switchScene = new SwitchScene();
            switchScene.setDir("/sample/view/addItem.fxml");
            switchScene.change();
        });

    }
}
