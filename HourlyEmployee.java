public class HourlyEmployee extends Employee {
    private double numHours;
    private double hourlyRate;

    public HourlyEmployee(int id, String firstName, String lastName, double numHours, double hourlyRate) {
        super(id, firstName, lastName);
        this.numHours = numHours;
        this.hourlyRate = hourlyRate;
    }

    public double getNumHours() {
        return numHours;
    }

    public void setNumHours(double numHours) {
        this.numHours = numHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePay() {
        return numHours * hourlyRate;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Hourly Rate: $" + hourlyRate + "\n  Hours: " + numHours;
    }
}
