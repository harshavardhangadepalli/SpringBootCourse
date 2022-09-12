package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student>  getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent())
            throw new IllegalStateException("email taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists)
                throw new IllegalStateException("Student with id "+ studentId+" does not exist");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent())
            throw new IllegalStateException("Student with id does not exist");
        optionalStudent = studentRepository.findStudentByEmail(email);
        if(optionalStudent.isPresent())
            throw new IllegalStateException("Email with id already exists");
        Student student = studentRepository.getReferenceById(studentId);
        if(name != null && name.length() > 0 && !student.getName().equals(name))
            student.setName(name);
        if(email != null && email.length() > 0 && !student.getEmail().equals(email))
            student.setEmail(email);
    }
}
