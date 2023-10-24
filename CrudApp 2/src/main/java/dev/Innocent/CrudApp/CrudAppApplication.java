package dev.Innocent.CrudApp;

import dev.Innocent.CrudApp.Entity.Student;
import dev.Innocent.CrudApp.Services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService){
		return runner -> {
			//createStudent(studentService);

			createMultipleStudent(studentService);

			//readStudent(studentService);

			// queryForStudents(studentService);

			// queryForStudentsByLastName(studentService);

			// updateStudent(studentService);
			
			// deleteStudent(studentService);

			// deleteAllStudent(studentService);
		};
	}

	private void deleteAllStudent(StudentService studentService) {
		System.out.println("Deleting all student");
		int numOfRowsDeleted = studentService.deleteAll();
		System.out.println("Deleted row count" + numOfRowsDeleted);
	}

	private void deleteStudent(StudentService studentService) {
		int studentId = 3;
		System.out.println("Deleting Student ID: " + studentId);
		studentService.delete(studentId);
	}

	private void updateStudent(StudentService studentService) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentService.findById(studentId);

		// Change first name to "Udo"
		System.out.println("Updating student");
		myStudent.setFirstName("Udo");

		// Update the student
		studentService.update(myStudent);

		// Display the updated student
		System.out.println("Updated student: " +myStudent);
	}

	private void queryForStudentsByLastName(StudentService studentService) {
		// get a list of student
		List<Student> theStudents = studentService.findByLastName("Charles");
		// display list of student
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentService studentService) {
		// get a list of student
		List<Student> theStudent = studentService.findAll();

		// display list of student
		for(Student allStudent : theStudent){
			System.out.println(allStudent);
		}
	}

	private void readStudent(StudentService studentService) {
		// Create a student object
		System.out.println("Creating new student object");
		Student preStudent = new Student("Daniel", "var", "Dan@gmail.com");

		// save the student
		System.out.println("Saving the student");
		studentService.save(preStudent);

		// display id of the saved student
		int theId = preStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student base on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentService.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudent(StudentService studentService) {
		// Create multiple Student
		System.out.println("Multiple object");
		Student bestStudent = new Student("Jubril", "Burkur", "Jubril@gmail.com");
		Student bestStudent1 = new Student("John", "Paul",  "johnpaul@gmail.com");
		Student bestStudent2 = new Student("Innocent", "Charles", "Udo@gmail.com");

		// Save the student object
		System.out.println("Saving the object to database");
		studentService.save(bestStudent);
		studentService.save(bestStudent1);
		studentService.save(bestStudent2);

	}

	private void createStudent(StudentService studentService) {
		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Innocent", "Udo", "innocentCharles");

		// save the student object
		System.out.println("Saving the student");
		studentService.save(tempStudent);

		// Display id of the saved student
		System.out.println("Save student. Generate id: " + tempStudent.getId());
	}

}
