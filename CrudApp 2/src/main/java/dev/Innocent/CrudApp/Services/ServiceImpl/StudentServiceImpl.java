package dev.Innocent.CrudApp.Services.ServiceImpl;

import dev.Innocent.CrudApp.Entity.Student;
import dev.Innocent.CrudApp.Services.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentServiceImpl implements StudentService {

    private EntityManager entityManager;

    @Autowired
    public StudentServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName", Student.class);
        // Return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // Create query
        TypedQuery<Student> theQueryLast = entityManager.createQuery("FROM Student WHERE lastName=: theData", Student.class);
        //Set query parameter
        theQueryLast.setParameter("theData", theLastName);
        // return query result
        return theQueryLast.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve student
        Student theStudent = entityManager.find(Student.class, id);

        // Delete student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numOfRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numOfRowsDeleted;
    }
}
