package View;

import Entity.UserObject;
import XMLDomEngine.userStore;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Controller {
    //global variable for user
    private Integer userId;

    //Login Page
    public void loginButtonAction(ActionEvent event) {
        //Button was clicked, login if username/password is correct
        //TODO: Check database for existing user
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Input.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Input");
            stage.setScene(new Scene(root, 1236, 769));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
            //Get the users
            userStore store = new userStore();
            List<UserObject> userList = store.getUsers();
            //Validate before scene change
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cancelLoginButtonAction(ActionEvent event) {
        //On button click, close application
        Platform.exit();
    }

    public void registerLoginButtonAction(ActionEvent event) {
        //Button was clicked, open registration page
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 562, 370));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    public void registerAction(ActionEvent event) {
        //Button was clicked, Register new user
        //TODO addd new user to database/check if user already exists
        //Get the users
        userStore store = new userStore();
        List<UserObject> userList = store.getUsers();
        //validate that user does not exist by comparing username

        //Create the user object
        UserObject user = new UserObject();
        //Assign inputs like user name and password and id to this object
        user.setId(store.getMaxUserId() + 1);
        user.setUserName();
        user.setPassword();

        store.insertUser(user);
        //Change scene back to Login
    }


    //Input Page
    public void loadProfileInputButtonAction(ActionEvent event) {
        //Button was clicked, go to profile page
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Profiles.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Profiles");
            stage.setScene(new Scene(root, 567, 635));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutInputButtonAction(ActionEvent event) {
        //On button click, log user out and return to login page
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

    public void calcInputButtonAction(ActionEvent event) {
        //On button click, perform calculations and go to Output page

        //TODO: Perform calculations

        //go to Output page
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Output.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Output");
            stage.setScene(new Scene(root, 1236, 769));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Output Page
    public void backOutputButtonAction(ActionEvent event) {
        //On button click, return to Input page
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

    public void saveprofileOutputButtonAction(ActionEvent event) {
        //On button click, Save the users results
        //TODO: Implement this function
    }

    public void logoutOutputButtonAction(ActionEvent event) {
        //On button click, log user out and return to login page
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

