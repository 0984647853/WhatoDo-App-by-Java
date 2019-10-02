package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UpdateTaskController {

    @FXML
    private Label lable_text;

    @FXML
    void initialize() {


    }

    public void setTaskField(String task) {
        this.lable_text.setText(task);
    }

    public String getTask() {
        return this.lable_text.getText().trim();
    }


}
