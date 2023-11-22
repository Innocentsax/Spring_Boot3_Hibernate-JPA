package dev.Innocent.JPAAdvanceMapping;

import dev.Innocent.JPAAdvanceMapping.DAO.AppDAO;
import dev.Innocent.JPAAdvanceMapping.Entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaAdvanceMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAdvanceMappingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createCourseAndStudents(appDAO);

			//findCourseAndStudents(appDAO);

			//findStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting student id: " + theId);
		appDAO.deleteStudentById(theId);
		System.out.println("DONE!!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// Create more courses
		Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
		Course tempCourse2 = new Course("Atari 260 - Game Development");

		// Add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: " + tempStudent);
		System.out.println("Associate courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);
		System.out.println("DONE!!!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("DONE!!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// Create a course
		Course tempCourse = new Course("Pacman- How to Score One Million Point");

		// Create the students
		Student tempStudent1 = new Student("Innocent", "Charles", "innocentcharles@gmail.com");
		Student tempStudent2 = new Student("Udo", "Charles", "charles@zenz.com");

		// Add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//  Save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);
		System.out.println("DONE!!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting Course " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("DONE!!!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// Get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// Print the Course
		System.out.println(tempCourse);

		// Print the Reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("DONE!!!");

	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// Create a Course
		Course tempCourse = new Course("Pacman - How to score One Million point");

		//  add some reviews
		tempCourse.addReview(new Review("Great course!!... Love it"));
		tempCourse.addReview(new Review("Cool Course... Great Job"));
		tempCourse.addReview(new Review("What a dump course, you are an idiot"));

		// Save the course and leverage the cascade All
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);
		System.out.println("DONE!!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("DONE!!!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		// Find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// Update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);
		System.out.printf("DONE!!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		// Find the Instructor
		System.out.println("Finding instructor " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// Update the Instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");
		appDAO.update(tempInstructor);
		System.out.println("DONE!!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId =1;

		// Find the Instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		// Find Instructor
		System.out.println("Finding Instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		// Find courses for Instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// Associate the object
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("DONE!!!");
	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		// Create the Instructor
		Instructor tempInstructor = new Instructor("Innocent", "Charles", "innocentcharles@gmail.com");

		// Create the Instructor Details
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.youTube.com", "Playing Sax");

		// associate the Object
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//  Create some course
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 =new Course("The Pinball MasterClass");

		// Add course to Instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// NOTE: This will also save the courses because of CascadeType.PERSIST
		System.out.println("Saving Instructor: " + tempInstructor);
		System.out.println("The Courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("DONE!!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting Instructor detail id: " + theId);

		appDAO.deleteInstructorById(theId);
		System.out.println("DONE!!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// Get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// Print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// Print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("DONE!!!!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting Instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("DONE!!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("The associated instructorDetails only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// Create the Instructor
		Instructor tempInstructor = new Instructor("Innocent", "Charles", "innocentcharles@gmail.com");

		// Create the Instructor Details
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.youTube.com", "Playing Sax");

		// associate the Object
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Save the Instructor
		// NOTE: This will also save the details object because of Cascade.TYPE.ALL
		System.out.println("Saving Instructor" + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!!!");
	}

}
