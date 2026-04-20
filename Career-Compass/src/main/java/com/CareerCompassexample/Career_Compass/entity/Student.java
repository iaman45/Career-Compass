package com.CareerCompassexample.Career_Compass.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;

    @Column(name = "student_class")
    private String studentClass;

    @Column(name = "favourite_subject")
    private String favoriteSubject;

    @Column(name = "aim")
    private String aim;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @ElementCollection
    @CollectionTable(name = "student_hobbies", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "hobby")
    private List<String> hobbies = new ArrayList<>();
}
