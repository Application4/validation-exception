package com.javatechie.controller;

import com.javatechie.dto.*;
import com.javatechie.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO course) {
        ServiceResponse<CourseResponseDTO> courseResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOS = new ArrayList<>();

//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<CourseRequestDTO>> violations = validator.validate(course);
//
//        if(violations.size()>1){
//            courseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
//            violations.forEach(error->{
//                ErrorDTO errorDTO=new ErrorDTO(error.getMessage());
//                errorDTOS.add(errorDTO);
//            });
//            courseResponse.setErrorDTO(errorDTOS);
//            return courseResponse;
//        }
        CourseResponseDTO responseDTO = courseService.onboardNewCourse(course);
        courseResponse.setHttpStatus(HttpStatus.CREATED);
        courseResponse.setResponse(responseDTO);

        return courseResponse;
    }

    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourse() {
        ServiceResponse<List<CourseResponseDTO>> courseResponse = new ServiceResponse<>();
        try {
            List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourses();
            courseResponse.setHttpStatus(HttpStatus.OK);
            courseResponse.setResponse(courseResponseDTOS);
        } catch (Exception ex) {
            courseResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return courseResponse;
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable int courseId) {
        ServiceResponse<CourseResponseDTO> courseResponse = new ServiceResponse<>();
        CourseResponseDTO course = courseService.findCourseById(courseId);
        courseResponse.setHttpStatus(HttpStatus.OK);
        courseResponse.setResponse(course);

        return courseResponse;
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseWithRequestParm(@RequestParam(required = false) Integer courseId) {
        ServiceResponse<CourseResponseDTO> courseResponse = new ServiceResponse<>();
        try {
            CourseResponseDTO course = courseService.findCourseById(courseId);
            courseResponse.setHttpStatus(HttpStatus.OK);
            courseResponse.setResponse(course);
        } catch (Exception ex) {
            courseResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return courseResponse;
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course) {
        ServiceResponse<CourseResponseDTO> courseResponse = new ServiceResponse<>();
        try {
            CourseResponseDTO courseResponseDTO = courseService.updateCourse(course, courseId);
            courseResponse.setHttpStatus(HttpStatus.OK);
            courseResponse.setResponse(courseResponseDTO);
        } catch (Exception ex) {
            courseResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return courseResponse;
    }

//    @GetMapping("/counts")
//    private ServiceResponse<List<CourseCount>> getCourseCountByCourseType() {
//        List<CourseCount> courseCountList = courseService.getCourseCountByType();
//        return new ServiceResponse(HttpStatus.OK, courseCountList);
//    }
}
