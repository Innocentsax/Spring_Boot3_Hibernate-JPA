package dev.Innocent.JPAAdvanceMapping;

import dev.Innocent.JPAAdvanceMapping.DAO.AppDAO;
import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
import dev.Innocent.JPAAdvanceMapping.Entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			deleteInstructorDetail(appDAO);
		};
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
