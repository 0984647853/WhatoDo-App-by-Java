package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.methods.SwitchScene;

import java.io.IOException;
import java.sql.SQLException;

public class AddItemController {

    public static int userID;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLable;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXButton watchtask;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        TaskAnnounce();
        DropShadow shadow = new DropShadow(10, Color.WHITE);
        watchtask.setOnAction(event -> {
            watchtask.getScene().getWindow().hide();
            SwitchScene switchScene = new SwitchScene();
            switchScene.setDir("/sample/view/list.fxml");
            switchScene.change();
        });
        addButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->
                addButton.setEffect(shadow));
        addButton.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->
                addButton.setEffect(null));
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

            try {
                AnchorPane formPane =
                        FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                AddItemFormController addItemFormController = new AddItemFormController();
                addItemFormController.setUserId(getUserID());

                FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(1000), rootAnchorPane);
                fadeTransition2.setFromValue(0f);
                fadeTransition2.setToValue(1f);
                fadeTransition2.setCycleCount(1);
                fadeTransition2.setAutoReverse(false);
                fadeTransition2.play();
                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public int getUserID() {
        return userID;
    }

    void setUserID(int userID) {
        AddItemController.userID = userID;
        System.out.println("AddItemController: " + AddItemController.userID);
    }

    private void TaskAnnounce() throws SQLException, ClassNotFoundException {
        DatabaseHandler isTask = new DatabaseHandler();
        int taskcount = isTask.getAllTasks(getUserID());
        if (taskcount == 0) {
            notTaskLable.setText("There is no task");
        } else {
            notTaskLable.setText("You have " + taskcount + " tasks");
        }
    }
}
