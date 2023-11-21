package dev.Innocent.JPAAdvanceMapping.DAO;

import dev.Innocent.JPAAdvanceMapping.Entity.Course;
import dev.Innocent.JPAAdvanceMapping.Entity.Instructor;
import dev.Innocent.JPAAdvanceMapping.Entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor thInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    void save(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);
}
