package dev.Innocent.EmployeeCRUD.Controller;

import dev.Innocent.EmployeeCRUD.Entity.Employee;
import dev.Innocent.EmployeeCRUD.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    // Quick and dirty: inject employee Service (use constructor injection)
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Expose the endpoint "/employee" and return a list od employee
    @GetMapping("/list")
    public String listEmployee(Model theModel){
        // Get the employee from db
        List<Employee> theEmployees = employeeService.findAll();

        // Add the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // Create model Attribute to bind form Data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //Save the Employee
        employeeService.save(theEmployee);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/employee/list";
    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        // Get employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // Set employee in the model to prepopulate the form
        theModel.addAttribute("employee", theEmployee);

        // Send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        // Delete the employee
        employeeService.deleteById(theId);

        // Redirect to the /employee/list
        return "redirect:/employee/list";
    }

}
