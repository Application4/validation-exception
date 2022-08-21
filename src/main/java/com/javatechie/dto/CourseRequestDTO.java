package com.javatechie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javatechie.annotation.CourseTypeValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    @NotBlank(message = "Course Name shouldn't be NULL OR EMPTY")
    private String name;
    private String trainerName;
    @CourseTypeValidation
    private String courseType;//LIVE OR RECORDING
    @Min(1500)
    @Max(8000)
    private double courseFees;
    @NotEmpty(message = "must need to provide duration of the course")
    private String duration;// in month
    @Past(message = "start shouldn't be before current date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    private boolean isCertificationAvailable;
    @Email(message = "must need to pass valid email id")
    private String supportEmailId;
    @Pattern(regexp = "^[0-9]{10}$",message = "invalid mobile number")
    private String contact;


}
