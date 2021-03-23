package org.hua.dit.oopii_21950_219113.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service //Same as @Component
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){

        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        if( studentRepository.findStudentByEmail(student.getEmail()).isPresent() ){
            System.out.println("Email taken");
        }
        studentRepository.save(student);
//        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        if ( !studentRepository.existsById(id)){
            throw new IllegalStateException("Student with id " + id + "Does not exists");
        }

        studentRepository.deleteById(id);
    }

    @Transactional //TODO: SEE HOW IT WORKS
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow( () -> new IllegalStateException(
                "Student with id " + studentId + "does not exists"
        ));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && name.length() > 0 && !Objects.equals(student.getEmail(), email)){
            if ( studentRepository.findStudentByEmail(email).isPresent()) {
                throw new IllegalStateException("email taken");

            }
            student.setEmail(email);
        }


    }
}
