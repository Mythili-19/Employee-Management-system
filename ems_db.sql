-- Run this in MySQL Workbench or MySQL CLI before running the app

CREATE DATABASE IF NOT EXISTS ems_db;
USE ems_db;

CREATE TABLE IF NOT EXISTS employees (
    id                INT PRIMARY KEY AUTO_INCREMENT,
    name              VARCHAR(100) NOT NULL,
    department        VARCHAR(100) NOT NULL,
    designation       VARCHAR(100) NOT NULL,
    salary            DOUBLE       NOT NULL,
    performance_score INT          NOT NULL DEFAULT 0
);

-- Sample data (optional)
INSERT INTO employees (name, department, designation, salary, performance_score) VALUES
('Mythili',   'Engineering', 'Software Engineer', 75000, 92),
('Ravi Kumar', 'HR',          'HR Manager',        65000, 88),
('Priya S',   'Engineering', 'Tech Lead',          95000, 97),
('Arun M',    'Finance',     'Accountant',         60000, 75),
('Sneha R',   'Marketing',   'Marketing Head',     70000, 85);
