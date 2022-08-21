package com.javatechie.util;

import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.model.CourseEntity;

public class AppUtil {


    public static CourseEntity mapCourseDtoToEntity(CourseRequestDTO course){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(course.getName());
        courseEntity.setTrainerName(course.getTrainerName());
        courseEntity.setCourseType(course.getCourseType());
        courseEntity.setCourseFees(course.getCourseFees());
        courseEntity.setDuration(course.getDuration());
        courseEntity.setStartDate(course.getStartDate());
        courseEntity.setCertificationAvailable(course.isCertificationAvailable());
        courseEntity.setSupportEmailId(course.getSupportEmailId());
        courseEntity.setContact(course.getContact());
        return courseEntity;

    }

    public static CourseResponseDTO mapCourseEntityToDto(CourseEntity courseEntity){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(courseEntity.getCourseId());
        courseResponseDTO.setName(courseEntity.getName());
        courseResponseDTO.setTrainerName(courseEntity.getTrainerName());
        courseResponseDTO.setCourseType(courseEntity.getCourseType());
        courseResponseDTO.setCourseFees(courseEntity.getCourseFees());
        courseResponseDTO.setDuration(courseEntity.getDuration());
        courseResponseDTO.setStartDate(courseEntity.getStartDate());
        courseResponseDTO.setCertificationAvailable(courseEntity.isCertificationAvailable());
        courseResponseDTO.setSupportEmailId(courseEntity.getSupportEmailId());
        courseResponseDTO.setContact(courseEntity.getContact());
        return courseResponseDTO;



    }
}
