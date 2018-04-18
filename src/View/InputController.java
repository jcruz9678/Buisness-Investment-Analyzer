package View;

import BusinessLogic.GenerateOutput;
import Entity.Input;
import Entity.Output;
import Entity.ProfileObject;
import XMLDomEngine.profileStore;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InputController implements Initializable {


    private static Input input;
    private static  Integer userId;

    public InputController(Input input)
    {
        this.input = input;
    }
    public InputController()
    {
        this.input = new Input();
        this.userId = null;
    }

    public static Input getInput() {
        return input;
    }

    public static void setInput(Input input) {
        InputController.input = input;
    }

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer userId) {
        InputController.userId = userId;
    }

    @FXML
    public JFXTextField propertyAddress;
    @FXML public JFXTextField propertyPrice;
    @FXML public JFXTextField downPayment;
    @FXML public JFXTextField anticipatedImprovements;
    @FXML public JFXTextField mortgageInterestRate;
    @FXML public JFXTextField mortgageLengthY;
    @FXML public JFXTextField incomingRent;
    @FXML public JFXTextField vacancy;
    @FXML public JFXTextField additionalFees;
    @FXML public JFXTextField appreciation;
    @FXML public JFXTextField additionalTaxes;
    @FXML public JFXTextField propertyTaxes;
    @FXML public JFXTextField insurance;
    @FXML public JFXTextField propertyManagement;
    @FXML public JFXTextField advertising;
    @FXML public JFXTextField utilities;
    @FXML public JFXTextField maintenance;
    public void initialize(URL location, ResourceBundle resources)
    {
        if(input.getPropertyAddress() != null)
        {
            propertyAddress.setText(input.getPropertyAddress());
            propertyPrice.setText(input.getTotalPurchasePrice().toString());
            downPayment.setText(input.getDownPayment().toString());
            anticipatedImprovements.setText(input.getAnticipatedImprovements().toString());
            mortgageInterestRate.setText(input.getMortgageInterestRate().toString());
            mortgageLengthY.setText(input.getMortgageLengthYears().toString());
            incomingRent.setText(input.getMonthlyIncomeRent().toString());
            vacancy.setText(input.getVacancyPercent().toString());
            additionalFees.setText(input.getAdditionalFees().toString());
            appreciation.setText(input.getAppreciationPerYear().toString());
            additionalTaxes.setText(input.getAdditionalTaxes().toString());
            insurance.setText(input.getInsuranceCost().toString());
            propertyManagement.setText(input.getManagementCost().toString());
            propertyTaxes.setText(input.getPropertyTaxes().toString());
            advertising.setText(input.getAdvertising().toString());
            utilities.setText(input.getUtilityCosts().toString());
            maintenance.setText(input.getMaintenanceCost().toString());

        }
    }


    public void calcInputButtonAction(ActionEvent event) {
        //On button click, perform calculations and go to Output page

        try {
            ArrayList<JFXTextField> textfields = new ArrayList<JFXTextField>();
            textfields.add(propertyAddress);
            textfields.add(propertyPrice);
            textfields.add(downPayment);
            textfields.add(anticipatedImprovements);
            textfields.add(mortgageInterestRate);
            textfields.add(mortgageLengthY);
            textfields.add(incomingRent);
            textfields.add(vacancy);
            textfields.add(additionalFees);
            textfields.add(appreciation);
            textfields.add(additionalTaxes);
            textfields.add(propertyTaxes);
            textfields.add(insurance);
            textfields.add(propertyManagement);
            textfields.add(advertising);
            textfields.add(utilities);
            textfields.add(maintenance);

            boolean emptyTextField = true;
            for(int i = 0; i < textfields.size(); i++)
            {
                if(textfields.get(i).getText() == null || textfields.get(i).getText().isEmpty())
                {
                    emptyTextField = false;
                }
            }

            if(emptyTextField)
            {
                input.setPropertyAddress(propertyAddress.getText());
                input.setTotalPurchasePrice(Double.parseDouble(propertyPrice.getText()));
                input.setDownPayment(Double.parseDouble(downPayment.getText()));
                input.setAnticipatedImprovements(Double.parseDouble(anticipatedImprovements.getText()));
                input.setMortgageInterestRate(Double.parseDouble(mortgageInterestRate.getText()));
                input.setMortgageLengthYears(Double.parseDouble(mortgageLengthY.getText()));
                input.setMonthlyIncomeRent(Double.parseDouble(incomingRent.getText()));
                input.setVacancyPercent(Double.parseDouble(vacancy.getText()));
                input.setAdditionalFees(Double.parseDouble(additionalFees.getText()));
                input.setAppreciationPerYear(Double.parseDouble(appreciation.getText()));
                input.setAdditionalTaxes(Double.parseDouble(additionalTaxes.getText()));
                input.setPropertyTaxes(Double.parseDouble(propertyTaxes.getText()));
                input.setInsuranceCost(Double.parseDouble(insurance.getText()));
                input.setManagementCost(Double.parseDouble(propertyManagement.getText()));
                input.setAdvertising(Double.parseDouble(advertising.getText()));
                input.setUtilityCosts(Double.parseDouble(utilities.getText()));
                input.setMaintenanceCost(Double.parseDouble(maintenance.getText()));

                GenerateOutput generator = new GenerateOutput();
                Output output = generator.executeAllCalculations(input);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Output.fxml"));

                OutputController controller = new OutputController(output, input);//fxmlLoader.<OutputController>getController();
                fxmlLoader.setController(controller);
                AnchorPane root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Output");
                stage.setScene(new Scene(root, 1236, 769));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else
            {
                //Error
                Parent root;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
                    ErrorController controller = new ErrorController();
                    controller.setMessage("Cannot have empty fields.");
                    fxmlLoader.setController(controller);
                    root = fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Error");
                    stage.setScene(new Scene(root, 270, 70));
                    stage.show();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Input Page
    public void loadProfileInputButtonAction(ActionEvent event) {
        //Button was clicked, go to profile page
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Profiles.fxml"));
            ProfileController controller = new ProfileController(userId);
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();
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
    public void saveprofileOutputButtonAction(ActionEvent event) {
        //On button click, Save the users results
        if(setInputObject())
        {
            profileStore store = new profileStore();
            ProfileObject profile = new ProfileObject();
            profile.setInput(input);
            profile.setUserId(userId);
            profile.setId(store.getMaxProfileId() + 1);


            String messageTitle = "Save Profile";
            String message = "Are you sure you want to save this profile?";


            Parent root;
            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Message.fxml"));
                MessageController controller = new MessageController(profile,message, messageTitle);
                fxmlLoader.setController(controller);
                root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Message");
                stage.setScene(new Scene(root, 578, 272));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            //Error
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
                ErrorController controller = new ErrorController();
                controller.setMessage("Cannot have empty fields.");
                fxmlLoader.setController(controller);
                root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Error");
                stage.setScene(new Scene(root, 270, 70));
                stage.show();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    private boolean setInputObject()
    {
        ArrayList<JFXTextField> textfields = new ArrayList<JFXTextField>();
        textfields.add(propertyAddress);
        textfields.add(propertyPrice);
        textfields.add(downPayment);
        textfields.add(anticipatedImprovements);
        textfields.add(mortgageInterestRate);
        textfields.add(mortgageLengthY);
        textfields.add(incomingRent);
        textfields.add(vacancy);
        textfields.add(additionalFees);
        textfields.add(appreciation);
        textfields.add(additionalTaxes);
        textfields.add(propertyTaxes);
        textfields.add(insurance);
        textfields.add(propertyManagement);
        textfields.add(advertising);
        textfields.add(utilities);
        textfields.add(maintenance);

        boolean emptyTextField = true;
        for(int i = 0; i < textfields.size(); i++)
        {
            if(textfields.get(i).getText() == null || textfields.get(i).getText().isEmpty())
            {
                emptyTextField = false;
            }
        }

        if(emptyTextField)
        {
            input.setPropertyAddress(propertyAddress.getText());
            input.setTotalPurchasePrice(Double.parseDouble(propertyPrice.getText()));
            input.setDownPayment(Double.parseDouble(downPayment.getText()));
            input.setAnticipatedImprovements(Double.parseDouble(anticipatedImprovements.getText()));
            input.setMortgageInterestRate(Double.parseDouble(mortgageInterestRate.getText()));
            input.setMortgageLengthYears(Double.parseDouble(mortgageLengthY.getText()));
            input.setMonthlyIncomeRent(Double.parseDouble(incomingRent.getText()));
            input.setVacancyPercent(Double.parseDouble(vacancy.getText()));
            input.setAdditionalFees(Double.parseDouble(additionalFees.getText()));
            input.setAppreciationPerYear(Double.parseDouble(appreciation.getText()));
            input.setAdditionalTaxes(Double.parseDouble(additionalTaxes.getText()));
            input.setPropertyTaxes(Double.parseDouble(propertyTaxes.getText()));
            input.setInsuranceCost(Double.parseDouble(insurance.getText()));
            input.setManagementCost(Double.parseDouble(propertyManagement.getText()));
            input.setAdvertising(Double.parseDouble(advertising.getText()));
            input.setUtilityCosts(Double.parseDouble(utilities.getText()));
            input.setMaintenanceCost(Double.parseDouble(maintenance.getText()));
        }

        return emptyTextField;
    }
}
