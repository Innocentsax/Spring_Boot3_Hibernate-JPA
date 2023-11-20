package dev.Innocent.JPAAdvanceMapping.DAO;

import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
import dev.Innocent.JPAAdvanceMapping.Entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
