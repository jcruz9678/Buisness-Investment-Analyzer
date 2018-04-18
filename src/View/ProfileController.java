package View;

import Entity.Input;
import Entity.ProfileObject;
import XMLDomEngine.profileStore;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import sun.java2d.StateTrackable;
import sun.java2d.cmm.Profile;

import javafx.event.ActionEvent;

import java.io.IOException;
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
    public JFXTreeTableView profilesTable;
    public void loadProfiles(ActionEvent event)
    {
        Input loadInput = new Input();
        ObservableList<TreeItem<InputObject>> list = FXCollections.observableArrayList();
        list = profilesTable.getSelectionModel().getSelectedItems();
        String selectedId = list.get(0).getValue().getId();
        for(ProfileObject obj : profileList)
        {
            if(obj.getId() == Integer.parseInt(selectedId))
            {
                loadInput = obj.getInput();
            }
        }
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Input.fxml"));
            InputController controller = new InputController(loadInput);
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Input");
            stage.setScene(new Scene(root, 1236, 769));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
    public void cancelProfilesButtonAction(ActionEvent event)
    {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Input.fxml"));
            InputController controller = new InputController();
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Input");
            stage.setScene(new Scene(root, 1236, 769));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileStore store = new profileStore();
        profileList = store.getProfiles(userId);

        JFXTreeTableColumn<InputObject, String> streetAddressCol = new JFXTreeTableColumn<>("Street Address");
        //TreeTableColumn<String,String> streetAddressCol = new TreeTableColumn<>("Column");
        streetAddressCol.setPrefWidth(150);
        //Defining cell content
        streetAddressCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<InputObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<InputObject, String> param) {
                return param.getValue().getValue().streetAddressProperty();
            }
        });
        JFXTreeTableColumn<InputObject, String> idCol = new JFXTreeTableColumn<>("ID");
        idCol.setPrefWidth(150);
        //Defining cell content
        idCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<InputObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<InputObject, String> param) {
                return param.getValue().getValue().idProperty();
            }
        });

        ObservableList<InputObject> inputs = FXCollections.observableArrayList();
        for(ProfileObject obj : profileList)
        {
            inputs.add(new InputObject(obj.getId().toString(), obj.getInput().getPropertyAddress()));
        }

        final TreeItem<InputObject> root = new RecursiveTreeItem<InputObject>(inputs, RecursiveTreeObject::getChildren);
        profilesTable.getColumns().setAll(idCol, streetAddressCol);
        profilesTable.setRoot(root);
        profilesTable.setShowRoot(false);

    }
}
class InputObject extends RecursiveTreeObject<InputObject> {
    private StringProperty streetAddress;
    private StringProperty id;
    public InputObject(String id, String streetAddress)
    {
        this.id = new SimpleStringProperty(id);
        this.streetAddress = new SimpleStringProperty(streetAddress);
    }

    public String getStreetAddress() {
        return streetAddress.get();
    }

    public StringProperty streetAddressProperty() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress.set(streetAddress);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
