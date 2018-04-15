package BusinessLogic;

import Entity.*;

public class Calculations {

    public Assumptions calculateAssumptions(Input input)
    {
        Assumptions result = new Assumptions();
        //Calculate the assumptions
        Double closingCosts = input.getTotalPurchasePrice() * 0.03;
        Double totalCosts = input.getTotalPurchasePrice() +
                input.getAnticipatedImprovements() +
                closingCosts;
        Double cashOutlay = input.getDownPayment() +
                input.getAnticipatedImprovements() +
                closingCosts;
        Double downPaymentPercent = input.getDownPayment() / input.getTotalPurchasePrice();
        Double financeAmount = input.getTotalPurchasePrice() - input.getDownPayment();
        //Calculate Mortgage
        Double mortgagePayment = calculateMortgage(financeAmount,
                input.getMortgageInterestRate()/100,
                input.getMortgageLengthYears());

        //Assign calculated values to the assumption object
        result.setClosingCosts(closingCosts);
        result.setCashOutlay(cashOutlay);
        result.setTotalCosts(totalCosts);
        result.setDownPaymentPercent(downPaymentPercent);
        result.setFinanceAmount(financeAmount);
        result.setMortgageMonthlyPayment(mortgagePayment);
        //return assumptions
        return result;
    }
    public Expenses calculateTotalExpenses(Input input)
    {
        Expenses result = new Expenses();
        Double total = input.getPropertyTaxes() +
                input.getInsuranceCost() +
                input.getManagementCost() +
                input.getMaintenanceCost() +
                input.getAdvertising() +
                input.getUtilityCosts();

        result.setTotalExpenses(total);

        return result;
    }
    public Income calculateIncome(Input input, Expenses expenses)
    {
        Income result = new Income();

        Double monthlyRent = input.getMonthlyIncomeRent();
        Double vacancy = input.getVacancyPercent()/100;
        Double fees = input.getAdditionalFees();

        Double monthlyIncome = (monthlyRent -(monthlyRent * (vacancy))) +
                fees;
        Double yearlyIncome = monthlyIncome * 12;
        Double netOperatingIncome = yearlyIncome - expenses.getTotalExpenses();

        result.setMonthlyIncome(monthlyIncome);
        result.setYearlyIncome(yearlyIncome);
        result.setNetOperatingIncome(netOperatingIncome);
        return result;
    }
    public EquityAccrual calculateEquityAccrual(Input input, Assumptions assumptions)
    {
        EquityAccrual result = new EquityAccrual();
        Double financeAmount = assumptions.getFinanceAmount();
        Double rate = (input.getMortgageInterestRate()/100);
        Double period = input.getMortgageLengthYears() * 12.0; //Hardcoded
        // (financeAmount * rate/12)/ Math.pow((1-(1+rate/12)),(-(period/12)*12));
                //financeAmount
                //* Math.pow((1 + (rate / 12)), (period * 2));
        Double PMT = PMT(rate/12, period, financeAmount);
        Double futureValue = futureValue(rate/12, 12.0, PMT, financeAmount);
        Double accrual = financeAmount - futureValue;

        result.setPMT(PMT);
        result.setFutureValue(futureValue);
        result.setAccrual(accrual);

        return result;
    }
    public ReturnOnInvestment calculateReturnOnInvestment(Input input, Income income, Assumptions assumptions, EquityAccrual accrual)
    {
        ReturnOnInvestment ROI = new ReturnOnInvestment();
        Double yearlyCashFlow = income.getNetOperatingIncome() -
                (assumptions.getMortgageMonthlyPayment() * 12);
        Double monthlyCashFlow = yearlyCashFlow / 12;
        Double capRate = (income.getNetOperatingIncome() / input.getTotalPurchasePrice());
        Double cashOnCashReturn = yearlyCashFlow / assumptions.getCashOutlay();
        Double totalReturn = yearlyCashFlow +
                accrual.getAccrual() +
                (input.getTotalPurchasePrice() * (input.getAppreciationPerYear()/100)) +
                input.getAdditionalTaxes();
        Double totalROI = (totalReturn - assumptions.getCashOutlay())/assumptions.getCashOutlay();
        //Set the fields
        ROI.setYearlyCashFlow(yearlyCashFlow);
        ROI.setMonthlyCashFlow(monthlyCashFlow);
        ROI.setCapRate(capRate);
        ROI.setCashOnCashReturn(cashOnCashReturn);
        ROI.setTotalReturn(totalReturn);
        ROI.setReturnOnInvestment(totalROI);

        return ROI;
    }
    private Double calculateMortgage(Double principal, Double rate, Double months)
    {
        Double monthlyInterestRate = rate / 12;
        Double interestCompound =  Math.pow((1 + rate), months);
        Double multiplier = (interestCompound * monthlyInterestRate)/(interestCompound - 1);

        Double result =  principal * multiplier;
        return  result;
    }
    private Double PMT(Double rate,Double nper, Double pv)
    {

        if (rate == 0)
            return -(pv)/nper;

        Double pvif = Math.pow(1 + rate, nper);
        Double pmt = rate / (pvif - 1) * -(pv * pvif);
        return pmt;
    }
    private Double futureValue(Double rate,Double nper, Double pmt,Double pv)
    {
        Double pow = Math.pow(1 + rate, nper);
        Double fv = 0.0;
        if (rate > 0) {
            fv = Math.abs((pmt * (1 + rate) * (1 - pow) / rate) - pv * pow);
        } else {
            fv = -1 * (pv + pmt * nper);
        }
        return fv;
    }
}