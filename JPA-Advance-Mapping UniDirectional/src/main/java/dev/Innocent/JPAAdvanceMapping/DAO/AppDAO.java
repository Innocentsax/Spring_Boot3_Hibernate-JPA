package dev.Innocent.JPAAdvanceMapping.DAO;

import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;

public interface AppDAO {

    void save(Instructor thInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
