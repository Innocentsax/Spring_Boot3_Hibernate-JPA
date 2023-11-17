package dev.Innocent.EmployeeCRUD.Repository;

import dev.Innocent.EmployeeCRUD.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@RepositoryRestController(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findAllByOrderByLastNameAsc();
}
