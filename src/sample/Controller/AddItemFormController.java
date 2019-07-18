package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {
    private static int userId;
    private DatabaseHandler databaseHandler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private JFXTextField task_field_id;

    @FXML
    private JFXTextArea description_id;

    @FXML
    private JFXButton saveTaskbutton;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        saveTaskbutton.setOnAction(event -> {

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = task_field_id.getText().trim();
            String taskDescription = description_id.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")) {
                Task task =
                        new Task(timestamp, taskText, taskDescription, getUserId());
                databaseHandler.insertTask(task);

                System.out.println("Task added successfully");
                System.out.println("Task user ID: " + task.getUserId());
            } else {
                System.out.println("Nothing added!");
            }

        });
    }

    private int getUserId() {
        return userId;
    }

    void setUserId(int userId) {
        AddItemFormController.userId = userId;
        System.out.println("AddItemFormController: " + AddItemFormController.userId);
    }
}
