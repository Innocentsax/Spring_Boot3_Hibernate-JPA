package dev.Innocent.JPAAdvanceMapping.DAO;

import dev.Innocent.JPAAdvanceMapping.Entity.Course;
import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
import dev.Innocent.JPAAdvanceMapping.Entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // Define field for entity manager
    private EntityManager entityManager;

    // Inject entity Manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor thInstructor) {
        entityManager.persist(thInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // Retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // Get the courses
        List<Course> courses = tempInstructor.getCourses();

        // Break association of all courses for the instructor
        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        // Delete the Instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // Retrieve the Instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // Remove the associated object reference and break Bi-Directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        // Delete the Instructor Detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // Create Query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // Execute Query
        List<Course> courses = query.getResultList();
        return courses;
    }
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // Create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail " + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        // Execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // Retrieve the courses
        Course tempCourse = entityManager.find(Course.class, theId);

        //  Delete the Course
        entityManager.remove(tempCourse);
    }
}
