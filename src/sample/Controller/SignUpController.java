package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Database.DatabaseHandler;
import sample.methods.SwitchScene;
import sample.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private AnchorPane sign_up_anchor_main;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField signup_lastname;

    @FXML
    private JFXButton signup_button;

    @FXML
    private JFXTextField signup_firstname;

    @FXML
    private JFXTextField signup_username;

    @FXML
    private JFXPasswordField signup_password;

    @FXML
    private JFXButton signup_back_button;

    @FXML
    void initialize() {
        signup_button.setOnAction(event -> createUser());
        signup_back_button.setOnAction(event -> {

            sign_up_anchor_main.getScene().getWindow().hide();
            SwitchScene switchScene = new SwitchScene();
            switchScene.setDir("/sample/view/login.fxml");
            switchScene.change();
        });
    }

    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String name = signup_firstname.getText();
        String lastname = signup_lastname.getText();
        String username = signup_username.getText();
        String password = signup_password.getText();

        //clear text
        signup_firstname.setText("");
        signup_lastname.setText("");
        signup_username.setText("");
        signup_password.setText("");
        User user = new User(name, lastname, username, password);

        databaseHandler.signUpUser(user);
    }
}
