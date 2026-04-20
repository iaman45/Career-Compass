package com.CareerCompassexample.Career_Compass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileUpdateRequest {
    private Long id;
    private String studentClass;
    private String favoriteSubject;
    private String aim;
    private Integer age;
    private String gender;
    private List<String> hobbies;
}
