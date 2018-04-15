package Entity;

public class ReturnOnInvestment
{
    private Double monthlyCashFlow;
    private Double yearlyCashFlow;
    private Double capRate;
    private Double cashOnCashReturn;
    private Double returnOnInvestment;
    private Double totalReturn;

    public void setMonthlyCashFlow(Double monthlyCashFlow) {
        this.monthlyCashFlow = monthlyCashFlow;
    }

    public void setYearlyCashFlow(Double yearlyCashFlow) {
        this.yearlyCashFlow = yearlyCashFlow;
    }

    public void setCapRate(Double capRate) {
        this.capRate = capRate;
    }

    public void setCashOnCashReturn(Double cashOnCashReturn) {
        this.cashOnCashReturn = cashOnCashReturn;
    }

    public void setReturnOnInvestment(Double returnOnInvestment) {
        this.returnOnInvestment = returnOnInvestment;
    }

    public void setTotalReturn(Double totalReturn) {
        this.totalReturn = totalReturn;
    }

    public Double getMonthlyCashFlow() {
        return monthlyCashFlow;
    }

    public Double getYearlyCashFlow() {
        return yearlyCashFlow;
    }

    public Double getCapRate() {
        return capRate;
    }

    public Double getCashOnCashReturn() {
        return cashOnCashReturn;
    }

    public Double getReturnOnInvestment() {
        return returnOnInvestment;
    }

    public Double getTotalReturn() {
        return totalReturn;
    }
}
