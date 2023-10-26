package dev.Innocent.CRUDREST.Controller;

import dev.Innocent.CRUDREST.Entity.Student;
import dev.Innocent.CRUDREST.Exception.StudentErrorResponse;
import dev.Innocent.CRUDREST.Exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    // define @PostConstruct to load the student data ... only once
    private List<Student> theStudent;

    @PostConstruct
    public void loadData(){
        theStudent = new ArrayList<>();
        theStudent.add(new Student("Innocent", "Charles"));
        theStudent.add(new Student("Udo", "Ben"));
        theStudent.add(new Student("RadicalX", "Ma G"));
    }

    @GetMapping("/students")
    public List<Student> getStudent(){
        return theStudent;
    }

    // Define endpoint or "/students/{studentID}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // Just index into the list... keep it simple for now
        //  Check the StudentId again list size
        if((studentId >= theStudent.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudent.get(studentId);
    }

}
