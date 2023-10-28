package dev.Innocent.EmployeeCRUD.Controller;

import dev.Innocent.EmployeeCRUD.Entity.Employee;
import dev.Innocent.EmployeeCRUD.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    // Quick and dirty: inject employee Service (use constructor injection)
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Expose the endpoint "/employee" and return a list od employee
    @GetMapping("/employee")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    // Add mapping for POST / employees - add new employee
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // Also just in case they pass an id in JSON ... set id to 0, this is to force a save of new item ... instead of update.
        theEmployee.setId(0);
        Employee dbEmployee  = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employee - update existing employee
    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody  Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // Add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempemployee = employeeService.findById(employeeId);

        // throw exception if null
        if(tempemployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
