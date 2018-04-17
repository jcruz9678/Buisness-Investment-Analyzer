package View;

import Entity.ProfileObject;
import XMLDomEngine.profileStore;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import sun.java2d.StateTrackable;
import sun.java2d.cmm.Profile;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileController implements Initializable
{
    private static Integer userId;
    private static List<ProfileObject> profileList;

    public  ProfileController(Integer userId)
    {
        this.userId = userId;
        profileList = new ArrayList<ProfileObject>();
    }
    @FXML
    public JFXTreeTableView table;
    public JFXTreeTableColumn<String, String> column;
    public void loadProfiles(ActionEvent event)
    {

    }
    public void cancelProfilesButtonAction(ActionEvent event)
    {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileStore store = new profileStore();
        profileList = store.getProfiles(userId);
        List<TreeItem<String>> list = new ArrayList<TreeItem<String>>();

        for(ProfileObject obj : profileList)
        {
            list.add(new TreeItem<>(obj.getInput().getPropertyAddress()));
        }
        TreeItem<String> root = new TreeItem<>("Addresses");
        root.setExpanded(true);
        for(int i = 0; i < list.size(); i++)
        {
            root.getChildren().add(list.get(i));
        }
        TreeTableColumn<String,String> column = new TreeTableColumn<>("Column");
        column.setPrefWidth(150);
        //Defining cell content
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue()));

        table.getColumns().add(column);
        table.setShowRoot(true);
    }



}
