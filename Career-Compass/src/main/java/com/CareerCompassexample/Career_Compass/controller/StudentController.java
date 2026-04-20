package com.CareerCompassexample.Career_Compass.controller;

import com.CareerCompassexample.Career_Compass.dto.LoginRequest;
import com.CareerCompassexample.Career_Compass.dto.ProfileUpdateRequest;
import com.CareerCompassexample.Career_Compass.entity.Student;
import com.CareerCompassexample.Career_Compass.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request, HttpSession session) {
        boolean success = studentService.login(request);
        Map<String, Object> res = new HashMap<>();

        if (!success) {
            res.put("success", false);
            res.put("message", "Invalid email or password!");
            return ResponseEntity.badRequest().body(res);
        }

        Student student = studentService.findByEmail(request.getEmail());
        session.setAttribute("student", student); // store full Student object

        res.put("success", true);
        res.put("studentId", student.getId());
        res.put("redirectTo", "dashboard"); // Front-end will use this to redirect
        return ResponseEntity.ok(res);
    }

    // ✅ Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.register(student);
            return ResponseEntity.ok(savedStudent); // Return saved student as JSON
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateRequest req) {

        // explicitly say: Optional<ResponseEntity<?>>
        return studentService.findById(req.getId())
                .<ResponseEntity<?>>map(existing -> {    // 👈 Note <ResponseEntity<?>>
                    existing.setStudentClass(req.getStudentClass());
                    existing.setFavoriteSubject(req.getFavoriteSubject());
                    existing.setAim(req.getAim());
                    existing.setAge(req.getAge());
                    existing.setGender(req.getGender());
                    existing.setHobbies(req.getHobbies());

                    Student saved = studentService.save(existing);
                    return ResponseEntity.ok(saved);      // ResponseEntity<Student>
                })
                .orElseGet(() -> ResponseEntity.badRequest()
                        .body("Student not found"));       // ResponseEntity<String>
    }

}
