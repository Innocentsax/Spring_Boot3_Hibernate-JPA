package dev.Innocent.JPAAdvanceMapping;

import dev.Innocent.JPAAdvanceMapping.DAO.AppDAO;
import dev.Innocent.JPAAdvanceMapping.Entity.Course;
import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
import dev.Innocent.JPAAdvanceMapping.Entity.InstructorDetail;
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
			//createInstructor(appDAO);

			//findInstructor(appDAO);

			//deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);

			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourse(appDAO);

			//findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);

			//updateInstructor(appDAO);

			//updateCourse(appDAO);

			deleteCourse(appDAO);

		};
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
