public class Employee {
    private int id;
    private String name;
    private String department;
    private String designation;
    private double salary;
    private int performanceScore;

    public Employee() {}

    public Employee(int id, String name, String department, String designation, double salary, int performanceScore) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.performanceScore = performanceScore;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public int getPerformanceScore() { return performanceScore; }
    public void setPerformanceScore(int performanceScore) { this.performanceScore = performanceScore; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-15s | %-15s | %-10.2f | %-5d |",
                id, name, department, designation, salary, performanceScore);
    }
}
