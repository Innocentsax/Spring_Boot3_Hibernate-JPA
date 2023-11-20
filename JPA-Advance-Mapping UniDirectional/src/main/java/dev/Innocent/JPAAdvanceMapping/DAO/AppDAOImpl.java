package dev.Innocent.JPAAdvanceMapping.DAO;

import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
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
}
