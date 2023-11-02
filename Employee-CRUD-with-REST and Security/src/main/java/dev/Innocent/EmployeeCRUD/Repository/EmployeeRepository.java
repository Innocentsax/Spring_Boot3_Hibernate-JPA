package dev.Innocent.EmployeeCRUD.Repository;

import dev.Innocent.EmployeeCRUD.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestController(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
