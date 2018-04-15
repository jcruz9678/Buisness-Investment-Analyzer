package Entity;

public class Assumptions
{
    private Double closingCosts;
    private Double totalCosts;
    private Double cashOutlay;
    private Double downPaymentPercent;
    private Double financeAmount;
    private Double mortgageMonthlyPayment;

    public void setClosingCosts(Double closingCosts) {
        this.closingCosts = closingCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public void setCashOutlay(Double cashOutlay) {
        this.cashOutlay = cashOutlay;
    }

    public void setDownPaymentPercent(Double downPaymentPercent) {
        this.downPaymentPercent = downPaymentPercent;
    }

    public void setFinanceAmount(Double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public void setMortgageMonthlyPayment(Double mortgageMonthlyPayment) {
        this.mortgageMonthlyPayment = mortgageMonthlyPayment;
    }

    public Double getClosingCosts() {
        return closingCosts;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public Double getCashOutlay() {
        return cashOutlay;
    }

    public Double getDownPaymentPercent() {
        return downPaymentPercent;
    }

    public Double getFinanceAmount() {
        return financeAmount;
    }

    public Double getMortgageMonthlyPayment() {
        return mortgageMonthlyPayment;
    }


}
