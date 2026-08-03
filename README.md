# Employee Management System (EMS)

A console-based Employee Management System built with **Java, JDBC, and MySQL**.

## Features
- Add, View, Update, Delete employees (CRUD)
- Search by ID or Name
- Department-wise report (total employees + avg salary)
- Top 5 employees by performance score

## Tech Stack
- Java 17+
- JDBC (MySQL Connector/J)
- MySQL 8+

## Files
| File | Description |
|---|---|
| Main.java | Console menu and entry point |
| Employee.java | Employee model/entity |
| EmployeeDAO.java | All DB operations via JDBC |
| DBConnection.java | MySQL connection utility |
| ems_db.sql | Database and table setup script |

## Setup & Run

### 1. Setup Database
Run `ems_db.sql` in MySQL Workbench or CLI:
```
mysql -u root -p < ems_db.sql
```

### 2. Update DB Password
In `DBConnection.java`, set your MySQL password:
```java
private static final String PASSWORD = "your_mysql_password";
```

### 3. Add MySQL Connector JAR
Download `mysql-connector-j-8.x.x.jar` from:
https://dev.mysql.com/downloads/connector/j/

### 4. Compile
```bash
javac -cp .;mysql-connector-j-8.x.x.jar *.java        # Windows
javac -cp .:mysql-connector-j-8.x.x.jar *.java        # Mac/Linux
```

### 5. Run
```bash
java -cp .;mysql-connector-j-8.x.x.jar Main           # Windows
java -cp .:mysql-connector-j-8.x.x.jar Main           # Mac/Linux
```
