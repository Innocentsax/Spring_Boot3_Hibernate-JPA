package dev.Innocent.EmployeeCRUD.Services;

import dev.Innocent.EmployeeCRUD.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void deleteById(int theId);
}
