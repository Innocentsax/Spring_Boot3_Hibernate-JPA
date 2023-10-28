package dev.Innocent.EmployeeCRUD.Repository;

import dev.Innocent.EmployeeCRUD.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
