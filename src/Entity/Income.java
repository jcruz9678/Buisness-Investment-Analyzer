package Entity;

public class Income
{
    private Double monthlyIncome;
    private Double yearlyIncome;
    private Double netOperatingIncome;
    public Income()
    {
        this.monthlyIncome = null;
        this.yearlyIncome = null;
    }

    public Income(Double monthlyIncome, Double yearlyIncome) {
        this.monthlyIncome = monthlyIncome;
        this.yearlyIncome = yearlyIncome;
    }

    public Income(Double monthlyIncome, Double yearlyIncome, Double netOperatingIncome) {
        this.monthlyIncome = monthlyIncome;
        this.yearlyIncome = yearlyIncome;
        this.netOperatingIncome = netOperatingIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setYearlyIncome(Double yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public void setNetOperatingIncome(Double netOperatingIncome) {
        this.netOperatingIncome = netOperatingIncome;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public Double getYearlyIncome() {
        return yearlyIncome;
    }

    public Double getNetOperatingIncome() {
        return netOperatingIncome;
    }
}
