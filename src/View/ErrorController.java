package View;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {
    @FXML
    public JFXTextField errorMsg;
    private static String message;


    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        ErrorController.message = message;
    }

    public void initialize(URL location, ResourceBundle resources){
        errorMsg.setText(message);
    }

    //Error Control
    public void errorButtonAction(ActionEvent event) {
        //On button click, close error message
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
