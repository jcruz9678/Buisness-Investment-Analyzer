package com.company;

import BusinessLogic.GenerateOutput;
import Entity.Input;
import Entity.Output;
import Entity.ProfileObject;
import Entity.UserObject;
import XMLDomEngine.profileStore;
import XMLDomEngine.userStore;

import java.util.List;

public class Main {

    public void main()
    {
        userStore store = new userStore();
        profileStore pStore = new profileStore();
       // UserObject user = new UserObject("HeYa", "Outkast", store.getMaxUserId() + 1);
        // store.insertUser(user);

        List<UserObject> userList = store.getUsers();

        for(int i = 0; i < userList.size(); i++)
        {
            System.out.println("Id = " + userList.get(i).getId());
            System.out.println("Username = " + userList.get(i).getUserName());
            System.out.println("Password = " + userList.get(i).getPassword());
        }

        Input input = new Input();
        //Set the inputs
        input.setPropertyAddress("7-9 Church St Foxboro Duplex");
        input.setTotalPurchasePrice(250000.00);
        input.setDownPayment(200000.00);
        input.setAnticipatedImprovements(0.0);
        input.setMortgageInterestRate(4.0);
        input.setMortgageLengthYears(30.0);

        input.setMonthlyIncomeRent(2000.00);
        input.setVacancyPercent(10.0);
        input.setAdditionalFees(0.00);

        input.setPropertyTaxes(4727.00);
        input.setInsuranceCost(1500.00);
        input.setManagementCost(0.00);
        input.setMaintenanceCost(3000.00);
        input.setAdvertising(300.00);
        input.setUtilityCosts(0.00);

        input.setAppreciationPerYear(2.00);
        input.setAdditionalTaxes(0.00);


        GenerateOutput generator = new GenerateOutput();

        Output output = generator.executeAllCalculations(input);


        System.out.println("Total costs: " + output.getTotalCosts());
        System.out.println("Cash Outlay: " + output.getCashOutlay());
        System.out.println("Monthly Mortgage: " + output.getTotalMonthlyMortgage());
        System.out.println("Total expenses: " + output.getTotalExpenses());
        System.out.println("Future Value: " + output.getFutureValue());
        System.out.println("Accrual: " + output.getEquityAccrual());
        System.out.println("Total Monthly Income: " + output.getMonthlyIncome());
        System.out.println("Total Yearly Income: " + output.getYearlyIncome());
        System.out.println("Net Operating Income" + output.getNetOperatingIncome());
        System.out.println("Monthly Cash Flow: " + output.getMonthlyCashFlow());
        System.out.println("Yearly Cash Flow: " + output.getYearlyCashFlow());
        System.out.println("Cap Rate: " + output.getCapRate());
        System.out.println("Cash On Cash Return: " + output.getCashOnCashReturn());
        System.out.println("Total Return: " + output.getTotalReturn());
        System.out.println("Return on Investment: " + output.getTotalROI());

        ProfileObject profile = new ProfileObject();
        profile.setUserId(1);
        profile.setId(pStore.getMaxProfileId() + 1);
        profile.setInput(input);

        pStore.insertProfile(profile);

        List<ProfileObject> profileList = pStore.getProfiles(1);
        for(int i = 0; i < profileList.size(); i++)
        {
            Input profileInput = profileList.get(i).getInput();
            System.out.println("Profile Load: ");
            System.out.println("Id: " + profileList.get(i).getId());
            System.out.println("User Id: " + profileList.get(i).getUserId());
            System.out.println("Property Address: " + profileInput.getPropertyAddress());
            System.out.println("Total Purchase Price: " + profileInput.getTotalPurchasePrice());
            System.out.println("Down Payment: " + profileInput.getDownPayment());
            System.out.println("Anticipated Improvements: " + profileInput.getAnticipatedImprovements());
            System.out.println("Mortgage Interest: " + profileInput.getMortgageInterestRate());
            System.out.println("Mortgage Length in Years: " + profileInput.getMortgageLengthYears());
            System.out.println("Monthly Rent: " + profileInput.getMonthlyIncomeRent());
            System.out.println("Vacancy Percent: " + profileInput.getVacancyPercent());
            System.out.println("Additional Fees: " + profileInput.getAdditionalFees());
            System.out.println("Property Taxes: " + profileInput.getPropertyTaxes());
            System.out.println("Insurance Cost: " + profileInput.getInsuranceCost());
            System.out.println("Management Cost: " + profileInput.getManagementCost());
            System.out.println("Maintenance Cost: " + profileInput.getMaintenanceCost());
            System.out.println("Advertising Cost: " + profileInput.getAdvertising());
            System.out.println("Utility Cost: " + profileInput.getUtilityCosts());
            System.out.println("Appreciation Per Year: " + profileInput.getAppreciationPerYear());
            System.out.println("Additional Taxes: " + profileInput.getAdditionalTaxes());
        }





    }
}
