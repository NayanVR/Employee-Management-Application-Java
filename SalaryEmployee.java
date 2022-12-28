public class SalaryEmployee extends Employee {

    private double salary;

    public SalaryEmployee(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay() {
        return salary / 52;
    }

    @Override
    public String toString() {
        return super.toString() + " | Salary: $" + salary;
    }
}
