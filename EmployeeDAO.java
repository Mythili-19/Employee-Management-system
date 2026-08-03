import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // ── CREATE TABLE ────────────────────────────────────────────────────────
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS employees (
                    id               INT PRIMARY KEY AUTO_INCREMENT,
                    name             VARCHAR(100) NOT NULL,
                    department       VARCHAR(100) NOT NULL,
                    designation      VARCHAR(100) NOT NULL,
                    salary           DOUBLE       NOT NULL,
                    performance_score INT         NOT NULL DEFAULT 0
                )
                """;
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            st.execute(sql);
            System.out.println("Table 'employees' ready.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // ── ADD ─────────────────────────────────────────────────────────────────
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, department, designation, salary, performance_score) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setString(3, emp.getDesignation());
            ps.setDouble(4, emp.getSalary());
            ps.setInt(5, emp.getPerformanceScore());
            ps.executeUpdate();
            System.out.println("✅ Employee added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // ── VIEW ALL ─────────────────────────────────────────────────────────────
    public void viewAllEmployees() {
        String sql = "SELECT * FROM employees ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            printHeader();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(mapRow(rs));
            }
            if (!found) System.out.println("No employees found.");
            printFooter();
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }
    }

    // ── SEARCH BY ID ─────────────────────────────────────────────────────────
    public void searchById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            printHeader();
            if (rs.next()) System.out.println(mapRow(rs));
            else System.out.println("No employee found with ID: " + id);
            printFooter();
        } catch (SQLException e) {
            System.out.println("Error searching by ID: " + e.getMessage());
        }
    }

    // ── SEARCH BY NAME ───────────────────────────────────────────────────────
    public void searchByName(String name) {
        String sql = "SELECT * FROM employees WHERE name LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            printHeader();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(mapRow(rs));
            }
            if (!found) System.out.println("No employee found with name: " + name);
            printFooter();
        } catch (SQLException e) {
            System.out.println("Error searching by name: " + e.getMessage());
        }
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name=?, department=?, designation=?, salary=?, performance_score=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setString(3, emp.getDesignation());
            ps.setDouble(4, emp.getSalary());
            ps.setInt(5, emp.getPerformanceScore());
            ps.setInt(6, emp.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Employee updated successfully.");
            else System.out.println("No employee found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    // ── DELETE ───────────────────────────────────────────────────────────────
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Employee deleted successfully.");
            else System.out.println("No employee found with ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    // ── DEPARTMENT REPORT ────────────────────────────────────────────────────
    public void departmentReport() {
        String sql = "SELECT department, COUNT(*) AS total, AVG(salary) AS avg_salary FROM employees GROUP BY department ORDER BY department";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n" + "=".repeat(55));
            System.out.printf("| %-20s | %-8s | %-15s |%n", "Department", "Total", "Avg Salary");
            System.out.println("=".repeat(55));
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("| %-20s | %-8d | %-15.2f |%n",
                        rs.getString("department"),
                        rs.getInt("total"),
                        rs.getDouble("avg_salary"));
            }
            if (!found) System.out.println("No data available.");
            System.out.println("=".repeat(55));
        } catch (SQLException e) {
            System.out.println("Error generating department report: " + e.getMessage());
        }
    }

    // ── TOP 5 BY PERFORMANCE ─────────────────────────────────────────────────
    public void top5ByPerformance() {
        String sql = "SELECT * FROM employees ORDER BY performance_score DESC LIMIT 5";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n🏆 Top 5 Employees by Performance:");
            printHeader();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(mapRow(rs));
            }
            if (!found) System.out.println("No employees found.");
            printFooter();
        } catch (SQLException e) {
            System.out.println("Error fetching top performers: " + e.getMessage());
        }
    }

    // ── HELPERS ──────────────────────────────────────────────────────────────
    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getString("designation"),
                rs.getDouble("salary"),
                rs.getInt("performance_score")
        );
    }

    private void printHeader() {
        System.out.println("\n" + "=".repeat(82));
        System.out.printf("| %-4s | %-20s | %-15s | %-15s | %-10s | %-5s |%n",
                "ID", "Name", "Department", "Designation", "Salary", "Score");
        System.out.println("=".repeat(82));
    }

    private void printFooter() {
        System.out.println("=".repeat(82));
    }
}
