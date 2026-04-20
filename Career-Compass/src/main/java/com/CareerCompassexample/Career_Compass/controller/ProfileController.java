package com.CareerCompassexample.Career_Compass.controller; // ✅ Final project package

import com.CareerCompassexample.Career_Compass.entity.Student;
import com.CareerCompassexample.Career_Compass.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private StudentRepository studentRepository;

    // Show profile form
    @GetMapping("/profile")
    public String showProfileForm(Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/login.html"; // Redirect to login page if not logged in
        }

        // Get student or create new instance
        Student student = studentRepository.findById(studentId).orElse(new Student());
        model.addAttribute("student", student);
        return "profile"; // profile.html in /templates if using Thymeleaf
    }

    // Update profile and go to instruction page
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Student studentForm, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/login.html";
        }

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            student.setStudentClass(studentForm.getStudentClass());
            student.setFavoriteSubject(studentForm.getFavoriteSubject());
            student.setAim(studentForm.getAim());
            student.setAge(studentForm.getAge());
            student.setGender(studentForm.getGender());

            // Convert hobbies input into list
            List<String> hobbiesList = new ArrayList<>();
            if (studentForm.getHobbies() != null && !studentForm.getHobbies().isEmpty()) {
                String hobbiesStr = studentForm.getHobbies().get(0);
                if (hobbiesStr != null && !hobbiesStr.isBlank()) {
                    for (String h : hobbiesStr.split(",")) {
                        hobbiesList.add(h.trim());
                    }
                }
            }
            student.setHobbies(hobbiesList);

            studentRepository.save(student);
        }

        return "redirect:/instruction.html";
    }
}
