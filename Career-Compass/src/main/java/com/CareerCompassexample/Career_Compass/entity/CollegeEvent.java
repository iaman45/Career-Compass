package com.CareerCompassexample.Career_Compass.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "college_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollegeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // College name
    private String type; // Private, Government, Deemed
    private String description;
    private String website;

    private LocalDate applicationStartDate;
    private LocalDate applicationEndDate;
    private LocalDate examDate;
}
