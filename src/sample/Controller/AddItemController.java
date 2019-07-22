package sample.Controller;

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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddItemController {

    private static int userID;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLable;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        TaskAnnounce();
        DropShadow shadow = new DropShadow(10, Color.WHITE);
        addButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->
                addButton.setEffect(shadow));
        addButton.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->
                addButton.setEffect(null));
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

//            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000),addButton);
//            FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(1000), notTaskLable);
//
//            System.out.println("Add button clicked");
//            addButton.relocate(333, 80);
//            notTaskLable.relocate(268,60);
//
//            addButton.setOpacity(0);
//            notTaskLable.setOpacity(0);
//
//            fadeTransition.setFromValue(1f);
//            fadeTransition.setToValue(0f);
//            fadeTransition.setCycleCount(1);
//            fadeTransition.setAutoReverse(false);
//            fadeTransition.play();
//
//            fadeTransition1.setFromValue(1f);
//            fadeTransition1.setToValue(0f);
//            fadeTransition1.setCycleCount(1);
//            fadeTransition1.setAutoReverse(false);
//            fadeTransition1.play();

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

    private int getUserID() {
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
