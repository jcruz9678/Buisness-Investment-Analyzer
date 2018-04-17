package View;

import Entity.UserObject;
import XMLDomEngine.userStore;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public JFXTextField usernameLoginTextBox;
    @FXML
    public JFXPasswordField passwordLoginTextBox;
    private static List<UserObject> userList;
    private static UserObject user;
    @FXML
    public JFXTextField errorMsg;

    public void initialize(URL location, ResourceBundle resources){
        userStore store = new userStore();
        userList = store.getUsers();

    }

    //Login Page
    public void loginButtonAction(ActionEvent event) throws IOException {
        //Button was clicked, login if username/password is correct
        String username = usernameLoginTextBox.getText();
        String password = passwordLoginTextBox.getText();
        int counter = 0;

        //check if user exists
        for (UserObject n : userList) {
            if (username.equals(n.getUserName()) && password.equals(n.getPassword())) {
                user = n;
                //Go to Input page
                Parent root;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Input.fxml"));
                    InputController controller = new InputController();
                    controller.setUserId(n.getId());
                    fxmlLoader.setController(controller);
                    root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Input");
                    stage.setScene(new Scene(root, 1236, 769));
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    //Get the users
                    userStore store = new userStore();
                    userList = store.getUsers();
                    counter--;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else{
                counter++;
            }
        }
        if(counter == userList.size()){
            //Error
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
            ErrorController controller = new ErrorController();
            controller.setMessage("Invalid Login");
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Error");
            stage.setScene(new Scene(root, 270, 70));
            stage.show();
        }
    }

        public void cancelLoginButtonAction (ActionEvent event){
            //On button click, close application
            Platform.exit();
        }

        public void registerLoginButtonAction (ActionEvent event){
            //Button was clicked, open registration page
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Register.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Register");
                stage.setScene(new Scene(root, 562, 370));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
