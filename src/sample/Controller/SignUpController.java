package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Database.DatabaseHandler;
import sample.methods.BackMethod;
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
            //way 1:
//            try {
//                //sign_up_anchor_main.getScene().getWindow().hide();
//                AnchorPane loader = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
//                sign_up_anchor_main.getChildren().setAll(loader);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            //way 2:

//            signup_back_button.getScene().getWindow().hide();
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource(
//                    "/sample/view/login.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
            //way 3 ( Back Method):
            BackMethod backMethod = new BackMethod();
            backMethod.setDir("\"/sample/view/login.fxml\"");
            backMethod.back();
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
