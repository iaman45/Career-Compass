package com.CareerCompassexample.Career_Compass.controller;

import com.CareerCompassexample.Career_Compass.entity.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollegePageController {

    @GetMapping("/colleges")
    public String getCollegesPage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        model.addAttribute("student", student);
        return "colleges"; // ✅ DO NOT add .html
    }

}
