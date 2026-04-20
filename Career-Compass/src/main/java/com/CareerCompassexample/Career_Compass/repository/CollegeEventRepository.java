package com.CareerCompassexample.Career_Compass.repository;

import com.CareerCompassexample.Career_Compass.entity.CollegeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeEventRepository extends JpaRepository<CollegeEvent, Long> {
    // You can add custom queries later if needed
}
