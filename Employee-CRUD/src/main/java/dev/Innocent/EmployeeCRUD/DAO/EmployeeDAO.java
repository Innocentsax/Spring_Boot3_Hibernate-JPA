package dev.Innocent.EmployeeCRUD.DAO;

import dev.Innocent.EmployeeCRUD.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void deleteById(int theId);
}
