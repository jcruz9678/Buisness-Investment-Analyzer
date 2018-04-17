package View;

import Entity.UserObject;
import XMLDomEngine.userStore;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class RegistrationController {
    //global variable for user
    private Integer userId;
    @FXML
    public JFXTextField usernameRegisterTextBox;
    @FXML
    public JFXPasswordField passwordRegisterTextBox;
    @FXML
    public JFXPasswordField confirmRegisterTextBox;
    @FXML
    public JFXTextField errorMsg;

    //Register Page
    public void cancelRegisterButtonAction(ActionEvent event) {
        //Button was clicked, cancel registration
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 595, 422));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerAction(ActionEvent event) throws IOException {
        //Button was clicked, Register new user
        //Get the users
        userStore store = new userStore();
        List<UserObject> userList = store.getUsers();

        boolean userExists = true;
        String username = usernameRegisterTextBox.getText();
        String password = passwordRegisterTextBox.getText();
        String passwordConfirm = confirmRegisterTextBox.getText();

        boolean empty = false;
        if(username.isEmpty()|| password.isEmpty() || passwordConfirm.isEmpty())
        {
            empty = true;
            //Error
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
            ErrorController controller = new ErrorController();
            controller.setMessage("Fields cannot be blank");
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Error");
            stage.setScene(new Scene(root, 270, 70));
            stage.show();
        }
        //validate that user does not exist by comparing username
       for(UserObject n : userList){
           if(n.getUserName().equals(username)){
               userExists = true;

               //Error
               Parent root;
               FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
               ErrorController controller = new ErrorController();
               controller.setMessage("User already exists");
               fxmlLoader.setController(controller);
               root = fxmlLoader.load();

               Stage stage = new Stage();
               stage.setTitle("Error");
               stage.setScene(new Scene(root, 270, 70));
               stage.show();


           }
           else{
               userExists = false;
           }
       }

        //Create the user object
        if(password.equals(passwordConfirm) && userExists == false && !empty) {
            UserObject user = new UserObject();
            //Assign inputs like user name and password and id to this object
            user.setId(store.getMaxUserId() + 1);
            user.setUserName(username);
            user.setPassword(password);

            store.insertUser(user);

            //Change scene back to Login
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setScene(new Scene(root, 595, 422));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(userExists == false && !empty){
            //Error
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
            ErrorController controller = new ErrorController();
            controller.setMessage("Passwords must match");
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Error");
            stage.setScene(new Scene(root, 270, 70));
            stage.show();
        }
    }


    //Profiles Page
    public void loadProfilesButtonAction(ActionEvent event) {
        //On button click, load the selected profile and populate with their data
        //TODO: implement this function
    }

    public void cancelProfilesButtonAction(ActionEvent event) {
        //On button click, return to input page
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Input.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Input");
            stage.setScene(new Scene(root, 1236, 769));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

