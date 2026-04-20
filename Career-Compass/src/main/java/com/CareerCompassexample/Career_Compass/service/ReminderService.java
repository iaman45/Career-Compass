package com.CareerCompassexample.Career_Compass.service;

import com.CareerCompassexample.Career_Compass.entity.CollegeEvent;
import com.CareerCompassexample.Career_Compass.entity.Reminder;
import com.CareerCompassexample.Career_Compass.entity.Student;
import com.CareerCompassexample.Career_Compass.repository.CollegeEventRepository;
import com.CareerCompassexample.Career_Compass.repository.ReminderRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final CollegeEventRepository collegeEventRepository;
    private final JavaMailSender mailSender;

    public ReminderService(ReminderRepository reminderRepository,
                           CollegeEventRepository collegeEventRepository,
                           JavaMailSender mailSender) {
        this.reminderRepository = reminderRepository;
        this.collegeEventRepository = collegeEventRepository;
        this.mailSender = mailSender;
    }

    /**
     * Adds a reminder for a student for a specific college event
     */
    public Reminder addReminder(Student student, Long eventId) {
        CollegeEvent event = collegeEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("College event not found"));

        Reminder reminder = new Reminder();
        reminder.setStudent(student);
        reminder.setCollegeEvent(event);

        // Example: remind 3 days before application end date
        if (event.getApplicationEndDate() != null) {
            reminder.setReminderDate(event.getApplicationEndDate().minusDays(3));
        } else {
            // fallback: today
            reminder.setReminderDate(LocalDate.now());
        }

        return reminderRepository.save(reminder);
    }

    /**
     * Fetch all reminders of a student
     */
    public List<Reminder> getRemindersByStudent(Student student) {
        return reminderRepository.findByStudent(student);
    }

    /**
     * Send reminder emails to all students
     */
    public void sendAllReminders() {
        List<Reminder> reminders = reminderRepository.findAll();

        for (Reminder reminder : reminders) {
            Student student = reminder.getStudent();
            CollegeEvent event = reminder.getCollegeEvent();

            if (student.getEmail() == null || student.getEmail().isEmpty()) {
                continue; // skip if no email
            }

            String to = student.getEmail();
            String subject = "Reminder: " + event.getName();
            String text = "Hello " + student.getName() + ",\n\n"
                    + "This is a reminder about *" + event.getName() + "*.\n"
                    + "Application Deadline: " + event.getApplicationEndDate() + "\n\n"
                    + "Best of luck!\nCareer Compass";


            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);

                mailSender.send(message);
                System.out.println("✅ Email sent to " + to);

            } catch (Exception e) {
                System.out.println("❌ Failed to send email to " + to + " - " + e.getMessage());
            }
        }
    }
}
