package dev.Innocent.JPAAdvanceMapping.DAO;

import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
import dev.Innocent.JPAAdvanceMapping.Entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor thInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
}
