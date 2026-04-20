package com.CareerCompassexample.Career_Compass.controller;

import com.CareerCompassexample.Career_Compass.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-email")
public class TestEmailController {

    private final EmailService emailService;

    public TestEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Send a test email manually
     * Example: POST /api/test-email/send?to=someone@gmail.com
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendTestEmail(@RequestParam String to) {
        try {
            emailService.sendReminderEmail(
                    to,
                    "📢 Test Email from Career Compass",
                    "Hello,\n\nThis is a test email from your Career Compass project.\n\n✅ If you received this, email setup works!"
            );
            return ResponseEntity.ok("✅ Test email sent successfully to " + to);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Failed to send email: " + e.getMessage());
        }
    }
}
