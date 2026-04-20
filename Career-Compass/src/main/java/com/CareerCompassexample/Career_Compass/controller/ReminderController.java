package com.CareerCompassexample.Career_Compass.controller;

import com.CareerCompassexample.Career_Compass.entity.Reminder;
import com.CareerCompassexample.Career_Compass.entity.Student;
import com.CareerCompassexample.Career_Compass.service.ReminderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    /**
     * Add a reminder for the logged-in student
     */
    @PostMapping("/add/{eventId}")
    public ResponseEntity<?> addReminder(@PathVariable Long eventId, HttpSession session) {
        // Get logged-in student from session
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("❌ Please login first to set a reminder.");
        }

        try {
            Reminder reminder = reminderService.addReminder(student, eventId);
            return ResponseEntity.ok("✅ Reminder set for event ID: " + eventId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Something went wrong while setting the reminder.");
        }
    }

    /**
     * 🔹 Manual trigger: Send all reminder emails
     */
    @PostMapping("/send-emails")
    public ResponseEntity<String> sendReminderEmails() {
        reminderService.sendAllReminders();
        return ResponseEntity.ok("📧 Reminder emails sent successfully!");
    }
    @GetMapping("/ping")
    public String ping() {
        return "ReminderController is alive!";
    }

}
