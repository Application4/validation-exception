package com.javatechie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private int courseId;
    private String name;
    private String trainerName;
    private String courseType;//LIVE OR RECORDING
    private double courseFees;
    private String duration;// in month
    private LocalDate startDate;
    private boolean isCertificationAvailable;
    private String courseUniqueCode;
    private String supportEmailId;
    private String contact;
}
