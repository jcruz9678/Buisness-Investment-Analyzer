package View;

import Entity.Input;
import Entity.ProfileObject;
import XMLDomEngine.profileStore;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {

    private ProfileObject profile;
    private String message;
    private String title;

    public MessageController(ProfileObject profile, String message, String title)
    {
        this.profile = profile;
        this.message = message;
        this.title = title;
    }

    @FXML
    public JFXTextField messageText;
    public Label messageTitle;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        messageText.setText(message);
        messageTitle.setLabelFor(messageText);
        messageTitle.setText(title);
        messageText.setEditable(false);

    }
    public void clickedYes(ActionEvent event)
    {
        profileStore store = new profileStore();
        store.insertProfile(profile);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    public void clickedNo(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
