package Entity;

public class EquityAccrual
{
    private Double PMT;
    private Double futureValue;
    private Double Accrual;

    public void setPMT(Double PMT) {
        this.PMT = PMT;
    }

    public void setFutureValue(Double futureValue) {
        this.futureValue = futureValue;
    }

    public void setAccrual(Double accrual) {
        Accrual = accrual;
    }

    public Double getPMT() {
        return PMT;
    }

    public Double getFutureValue() {
        return futureValue;
    }

    public Double getAccrual() {
        return Accrual;
    }
}
