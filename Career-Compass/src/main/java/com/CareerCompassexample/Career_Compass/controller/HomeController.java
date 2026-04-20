package com.CareerCompassexample.Career_Compass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redirects root URL to your static dashboard.html
        return "redirect:/dashboard.html";
    }
}
