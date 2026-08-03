import java.util.Scanner;

public class Main {

    static EmployeeDAO dao = new EmployeeDAO();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        dao.createTable();
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> dao.viewAllEmployees();
                case 3 -> searchById();
                case 4 -> searchByName();
                case 5 -> updateEmployee();
                case 6 -> deleteEmployee();
                case 7 -> dao.departmentReport();
                case 8 -> dao.top5ByPerformance();
                case 0 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    static void printMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("   Employee Management System (EMS)");
        System.out.println("=".repeat(40));
        System.out.println(" 1. Add Employee");
        System.out.println(" 2. View All Employees");
        System.out.println(" 3. Search by ID");
        System.out.println(" 4. Search by Name");
        System.out.println(" 5. Update Employee");
        System.out.println(" 6. Delete Employee");
        System.out.println(" 7. Department Report");
        System.out.println(" 8. Top 5 Employees by Performance");
        System.out.println(" 0. Exit");
        System.out.println("=".repeat(40));
    }

    static void addEmployee() {
        Employee emp = new Employee();
        System.out.print("Name: ");
        emp.setName(sc.nextLine());
        System.out.print("Department: ");
        emp.setDepartment(sc.nextLine());
        System.out.print("Designation: ");
        emp.setDesignation(sc.nextLine());
        System.out.print("Salary: ");
        emp.setSalary(sc.nextDouble());
        System.out.print("Performance Score (0-100): ");
        emp.setPerformanceScore(sc.nextInt());
        sc.nextLine();
        dao.addEmployee(emp);
    }

    static void searchById() {
        System.out.print("Enter Employee ID: ");
        dao.searchById(sc.nextInt());
        sc.nextLine();
    }

    static void searchByName() {
        System.out.print("Enter Employee Name: ");
        dao.searchByName(sc.nextLine());
    }

    static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee emp = new Employee();
        emp.setId(id);
        System.out.print("New Name: ");
        emp.setName(sc.nextLine());
        System.out.print("New Department: ");
        emp.setDepartment(sc.nextLine());
        System.out.print("New Designation: ");
        emp.setDesignation(sc.nextLine());
        System.out.print("New Salary: ");
        emp.setSalary(sc.nextDouble());
        System.out.print("New Performance Score (0-100): ");
        emp.setPerformanceScore(sc.nextInt());
        sc.nextLine();
        dao.updateEmployee(emp);
    }

    static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        dao.deleteEmployee(sc.nextInt());
        sc.nextLine();
    }
}
