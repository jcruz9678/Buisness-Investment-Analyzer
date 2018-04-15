package Entity;

public class Expenses
{
    private Double totalExpenses;

    public Expenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    public Expenses() {
        this.totalExpenses = null;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }
}
