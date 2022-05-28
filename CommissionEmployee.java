public class CommissionEmployee extends Employee {

    private double rate;
    private double sales;

    public CommissionEmployee(int id, String firstName, String lastName, double rate, double sales) {
        super(id, firstName, lastName);
        this.rate = rate;
        this.sales = sales;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    @Override
    public double calculatePay() {
        return rate * sales / 100;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Commission Rate: " + rate + "\n  Sales: $" + sales;
    }
}
