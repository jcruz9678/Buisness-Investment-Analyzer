package BusinessLogic;

import Entity.*;

public class GenerateOutput
{
    public Output executeAllCalculations(Input input)
    {
        //Calculate the output
        Calculations calc = new Calculations();

        Assumptions assumptions = calc.calculateAssumptions(input);
        Expenses expenses = calc.calculateTotalExpenses(input);
        Income income = calc.calculateIncome(input, expenses);
        EquityAccrual accrual = calc.calculateEquityAccrual(input, assumptions);
        ReturnOnInvestment ROI = calc.calculateReturnOnInvestment(input, income,assumptions,accrual);

        Output output = new Output();

        //Set the output values
        output.setTotalCosts(assumptions.getTotalCosts());
        output.setCashOutlay(assumptions.getCashOutlay());
        output.setTotalMonthlyMortgage(assumptions.getMortgageMonthlyPayment());
        output.setTotalExpenses(expenses.getTotalExpenses());
        output.setFutureValue(accrual.getFutureValue());
        output.setEquityAccrual(accrual.getAccrual());
        output.setMonthlyIncome(income.getMonthlyIncome());
        output.setYearlyIncome(income.getYearlyIncome());
        output.setMonthlyCashFlow(ROI.getMonthlyCashFlow());
        output.setYearlyCashFlow(ROI.getYearlyCashFlow());
        output.setCapRate(ROI.getCapRate());
        output.setCashOnCashReturn(ROI.getCashOnCashReturn());
        output.setTotalReturn(ROI.getTotalReturn());
        output.setTotalROI(ROI.getReturnOnInvestment());
        output.setNetOperatingIncome(income.getNetOperatingIncome());



        return output;
    }
}
