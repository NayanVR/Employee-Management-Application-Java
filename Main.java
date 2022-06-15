import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static ArrayList<Employee> employees = new ArrayList<Employee>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        int numEmployees = sc.nextInt();

        for (int i = 0; i < numEmployees; i++) {
            System.out.println("\nEmployee data for employee " + (i + 1) + " of " + numEmployees);
            System.out.println("1. Salary Employee");
            System.out.println("2. Hourly Employee");
            System.out.print("3. Commission Employee : ");
            int choice = sc.nextInt();
            if(choice > 3 || choice < 1) {
                i--;
                continue;
            }
            employees.add(createEmployee(sc, choice));
        }

        while (true) {
            System.out.println("\n\n\n");
            System.out.println("1. Employee Listing");
            System.out.println("2. Payroll Listing");
            System.out.print("3. Exit : ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\n\n");
                System.out.println("Employee Listing");
                System.out.println("================");
                employeeListing();
            } else if (choice == 2) {
                System.out.println("\n\n");
                System.out.println("Employee Payroll");
                System.out.println("================");
                employeePayroll();
            } else {
                break;
            }
        }

        sc.close();
    }

    public static void employeeListing() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public static void employeePayroll() {
        for (Employee employee : employees) {
            System.out.println("Weekly pay for " +
                    employee.getLastName() + ", " + employee.getFirstName() +
                    " employee id " + employee.getId() +
                    " is $" + String.format("%.2f", employee.calculatePay()));
        }
    }

    public static SalaryEmployee createSalaryEmployee(Scanner sc) {
        int id = inputId(sc);
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();
        return new SalaryEmployee(id, firstName, lastName, salary);
    }

    public static HourlyEmployee createHourlyEmployee(Scanner sc) {
        int id = inputId(sc);
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        System.out.print("Hours Worked: ");
        double numHours = sc.nextDouble();
        System.out.print("Hourly Rate: ");
        double hourlyRate = sc.nextDouble();
        return new HourlyEmployee(id, firstName, lastName, numHours, hourlyRate);
    }

    public static CommissionEmployee createCommissionEmployee(Scanner sc) {
        int id = inputId(sc);
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        System.out.print("Commission Rate: ");
        double rate = sc.nextDouble();
        System.out.print("Sales: ");
        double sales = sc.nextDouble();
        return new CommissionEmployee(id, firstName, lastName, rate, sales);
    }

    public static Employee createEmployee(Scanner sc, int choice) {
        switch (choice) {
            case 1:
                return createSalaryEmployee(sc);
            case 2:
                return createHourlyEmployee(sc);
            case 3:
                return createCommissionEmployee(sc);
            default:
                return null;
        }
    }

    public static int inputId(Scanner sc) {
        int id = 0;
        boolean doesExist = true;
        while (doesExist) {
            System.out.print("\nEnter employee id: ");
            id = sc.nextInt();
            if (id > 0) {
                if (employees.size() == 0) {
                    doesExist = false;
                } else {
                    for (Employee employee : employees) {
                        if (employee.getId() == id) {
                            System.out.println("Employee id already exists.");
                        } else {
                            doesExist = false;
                        }
                    }
                }
            } else {
                System.out.println("Employee id must be greater than 0.");
            }
        }
        return id;
    }
}
