package View;

import Entity.Input;
import Entity.Output;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ResourceBundle;

public class OutputController implements Initializable {

    private static Output output;
    private static Input input;

    public static Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    @FXML public JFXTextField cashFlowM;
    @FXML public JFXTextField cashFlowY;
    @FXML public JFXTextField cashOnCashReturn;
    @FXML public JFXTextField netOperatingIncome;
    @FXML public JFXTextField capRate;
    @FXML public JFXTextField totalROI;
    @FXML public JFXTextField totalCosts;
    @FXML public JFXTextField cashOutlay;
    @FXML public JFXTextField accrual;
    @FXML public JFXTextField monthlyIncome;
    @FXML public JFXTextField yearlyIncome;
    @FXML public JFXTextField totalReturn;
    @FXML public JFXTextField monthlyMortgage;
    @FXML public JFXTextField futureValue;

    public OutputController(Output output, Input input)
    {
        this.output = output;
        this.input = input;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cashFlowM.setText(output.getMonthlyCashFlow().toString());
        cashFlowY.setText(output.getYearlyCashFlow().toString());
        cashOnCashReturn.setText(output.getCashOnCashReturn().toString());
        netOperatingIncome.setText(output.getNetOperatingIncome().toString());
        capRate.setText(output.getCapRate().toString());
        totalROI.setText(output.getTotalROI().toString());
        totalCosts.setText(output.getTotalCosts().toString());
        cashOutlay.setText(output.getCashOutlay().toString());
        accrual.setText(output.getEquityAccrual().toString());
        monthlyIncome.setText(output.getMonthlyIncome().toString());
        yearlyIncome.setText(output.getYearlyIncome().toString());
        totalReturn.setText(output.getTotalReturn().toString());
        monthlyMortgage.setText(output.getTotalMonthlyMortgage().toString());
        futureValue.setText(output.getFutureValue().toString());

        cashFlowM.setEditable(false);
        cashFlowY.setEditable(false);
        cashOnCashReturn.setEditable(false);
        netOperatingIncome.setEditable(false);
        capRate.setEditable(false);
        totalROI.setEditable(false);
        totalCosts.setEditable(false);
        cashOutlay.setEditable(false);
        accrual.setEditable(false);
        monthlyIncome.setEditable(false);
        yearlyIncome.setEditable(false);
        totalReturn.setEditable(false);
        monthlyMortgage.setEditable(false);
        futureValue.setEditable(false);


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

    //Output Page
    public void backOutputButtonAction(ActionEvent event) {
        //On button click, return to Input page
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Input.fxml"));
            InputController controller = new InputController(input);
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();
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
