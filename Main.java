import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String BG_BLUE = "\u001B[44m";
    public static ArrayList<Employee> employees = new ArrayList<Employee>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        printHeading("Employee Management System");

        employees.add(new SalaryEmployee(1, "John", "Doe", 1000));
        employees.add(new HourlyEmployee(2, "Jane", "Doe", 40, 10));
        employees.add(new CommissionEmployee(3, "John", "Smith", 0.1, 1000));

        while (true) {
            System.out.println("\n");
            System.out.println(TEXT_YELLOW + "1. Add new employees" + ANSI_RESET);
            System.out.println(TEXT_RED + "2. Delete employee" + ANSI_RESET);
            System.out.println(TEXT_PURPLE + "3. Employee Listing" + ANSI_RESET);
            System.out.println(TEXT_GREEN + "4. Payroll Listing" + ANSI_RESET);
            System.out.println(TEXT_CYAN + "5. Exit" + ANSI_RESET);
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                clearScreen();
                while (true) {
                    System.out.println("\nEmployee data for employee " + (employees.size() + 1));
                    System.out.println(TEXT_CYAN + "1. Salary Employee" + ANSI_RESET);
                    System.out.println(TEXT_YELLOW + "2. Hourly Employee" + ANSI_RESET);
                    System.out.println(TEXT_PURPLE + "3. Commission Employee : " + ANSI_RESET);
                    System.out.print("Enter your choice: ");
                    int empChoice = sc.nextInt();
                    employees.add(createEmployee(sc, empChoice));

                    System.out.print(TEXT_YELLOW + "Do you want to add another employee? (y/n) : " + ANSI_RESET);
                    String answer = sc.next();
                    if (answer.equals("n"))
                        break;
                }
            } else if (choice == 2) {
                clearScreen();
                System.out.print(TEXT_RED + "Enter employee ID to delete: " + ANSI_RESET);
                int id = sc.nextInt();
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).getId() == id) {
                        employees.remove(i);
                    }
                }
            } else if (choice == 3) {
                printHeading("Employee Listing");
                employeeListing();
            } else if (choice == 4) {
                printHeading("Payroll Listing");
                employeePayroll();
            } else {
                break;
            }
        }

        sc.close();
    }

    public static void employeeListing() {
        for (Employee employee : employees) {
            System.out.println(TEXT_PURPLE + employee.toString() + ANSI_RESET);
        }
    }

    public static void employeePayroll() {
        for (Employee employee : employees) {
            System.out.println(TEXT_GREEN + "\nWeekly pay for " +
                    employee.getLastName() + " " + employee.getFirstName() +
                    " is $" + String.format("%.2f", employee.calculatePay()) + ANSI_RESET);
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
                            System.out.println(TEXT_RED + "Employee id already exists." + ANSI_RESET);
                            break;
                        } else {
                            doesExist = false;
                        }
                    }
                }
            } else {
                System.out.println(TEXT_RED + "Employee id must be greater than 0." + ANSI_RESET);
            }
        }
        return id;
    }

    public static void printHeading(String text) {
        clearScreen();
        System.out.println();
        System.out.print(TEXT_BLACK + BG_BLUE);
        System.out.println("==========================================" + ANSI_RESET);
        System.out.print(TEXT_BLACK + BG_BLUE);
        System.out.println("|                                        |" + ANSI_RESET);
        System.out.print(TEXT_BLACK + BG_BLUE);
        System.out.printf("|" + center(text, 40, ' ') + "|\n" + ANSI_RESET);
        System.out.print(TEXT_BLACK + BG_BLUE);
        System.out.println("|                                        |" + ANSI_RESET);
        System.out.print(TEXT_BLACK + BG_BLUE);
        System.out.println("==========================================" + ANSI_RESET);
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
