package com.example.studentapi.service;

import com.example.studentapi.exception.StudentNotFoundException;
import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Long id, Student student) {

        Student existingStudent = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());

        return repository.save(existingStudent);
    }

    public void deleteStudent(Long id) {

        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student not found with id: " + id);
        }

        repository.deleteById(id);
    }
}