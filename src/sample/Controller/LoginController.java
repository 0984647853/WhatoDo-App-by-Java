package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.methods.SwitchScene;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    private static int userID;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane login_anchor_main;

    @FXML
    private JFXTextField Login_username;

    @FXML
    private JFXPasswordField Login_password;

    @FXML
    private JFXButton Login_Button;

    @FXML
    private JFXButton Login_sign_up_button;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        Login_Button.setOnAction(event -> {

            String loginText = Login_username.getText().trim();
            String loginPwd = Login_password.getText().trim();
            if (!loginPwd.equals("") || !loginText.equals("")) {

                User user = new User();
                user.setUsername(loginText);
                user.setPassword(loginPwd);

                ResultSet userRow = databaseHandler.getUser(user);
                int counter = 0;
                try {
                    if (userRow.next()) {
                        counter++;
                        String name = userRow.getString("firstname");

                        userID = userRow.getInt("userid");

                        System.out.println("Welcome " + name);
                    } else {
                        Shaker shaker = new Shaker(Login_username, 10);
                        shaker.shake();
                        Shaker shaker1 = new Shaker(Login_password, 10);
                        shaker1.shake();
                        Shaker shaker2 = new Shaker(Login_Button, 10);
                        shaker2.shake();
                        System.out.println("ur username or password is incorrect");
                    }
                    if (counter == 1) {
                        showAddItem();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                Shaker shaker = new Shaker(Login_Button, 4);
                shaker.shake();
                System.out.println("Please provide your username and password");
            }
        });
        Login_sign_up_button.setOnAction(event -> {
            // Take users to signup screen
            //way 1:
//            try {
//                AnchorPane loader = FXMLLoader.load(getClass().getResource("/sample/view/signup.fxml"));
//                login_anchor_main.getChildren().setAll(loader);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //way 2:
            Login_sign_up_button.getScene().getWindow().hide();
            SwitchScene switchScene = new SwitchScene();
            switchScene.setDir("/sample/view/signup.fxml");
            switchScene.change();
        });
    }
    private void showAddItem() {
        // Take users to signup screen
        Login_Button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = new AddItemController();
        addItemController.setUserID(userID);

        stage.showAndWait();
    }
}

