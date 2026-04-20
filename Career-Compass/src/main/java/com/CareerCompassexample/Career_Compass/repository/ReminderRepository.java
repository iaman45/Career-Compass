package com.CareerCompassexample.Career_Compass.repository;

import com.CareerCompassexample.Career_Compass.entity.Reminder;
import com.CareerCompassexample.Career_Compass.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByStudent(Student student);
}
