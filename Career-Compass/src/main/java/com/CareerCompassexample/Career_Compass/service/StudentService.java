package com.CareerCompassexample.Career_Compass.service;

import com.CareerCompassexample.Career_Compass.dto.LoginRequest;
import com.CareerCompassexample.Career_Compass.entity.Student;
import com.CareerCompassexample.Career_Compass.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student register(Student student) {
        return studentRepository.save(student);
    }

    public boolean login(LoginRequest request) {
        return studentRepository.findByEmail(request.getEmail())
                .map(student -> student.getPassword().equals(request.getPassword()))
                .orElse(false);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email).orElse(null);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public boolean isProfileComplete(Student student){
        if(student == null) return false;

        boolean classFilled = student.getStudentClass() != null && !student.getStudentClass().isBlank();
        boolean subjectFilled = student.getFavoriteSubject() != null && !student.getFavoriteSubject().isBlank();
        boolean aimFilled = student.getAim() != null && !student.getAim().isBlank();
        boolean hobbiesFilled = student.getHobbies() != null && !student.getHobbies().isEmpty();

        return classFilled && subjectFilled && aimFilled && hobbiesFilled;
    }

}
