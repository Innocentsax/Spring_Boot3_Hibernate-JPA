package dev.Innocent.CrudApp.Services;

import dev.Innocent.CrudApp.Entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String theLastName);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
