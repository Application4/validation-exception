package com.javatechie.service;

import com.javatechie.dao.CourseDao;
import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseCount;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.model.CourseEntity;
import com.javatechie.util.AppUtil;
import exception.CourseServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    //private List<Course> courses = new ArrayList<>();

    @Autowired
    private CourseDao courseDao;

    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = AppUtil.mapCourseDtoToEntity(courseRequestDTO);
        CourseEntity course = courseDao.save(courseEntity);
        CourseResponseDTO courseResponseDTO = AppUtil.mapCourseEntityToDto(course);
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }

    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> entities = courseDao.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(AppUtil::mapCourseEntityToDto)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO findCourseById(Integer courseId)  {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(()->new CourseServiceException("courseId "+courseId +" doesn't exist"));
        return AppUtil.mapCourseEntityToDto(courseEntity);
    }


    public void deleteCourse(int courseId) {
        courseDao.deleteById(courseId);
    }

    public CourseResponseDTO updateCourse(CourseRequestDTO course, int courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId).orElseGet(null);
        courseEntity.setName(course.getName());
        courseEntity.setTrainerName(course.getTrainerName());
        courseEntity.setCourseType(course.getCourseType());
        courseEntity.setCourseFees(course.getCourseFees());
        courseEntity.setDuration(course.getDuration());
        courseEntity.setStartDate(course.getStartDate());
        courseEntity.setCertificationAvailable(course.isCertificationAvailable());
        CourseEntity updatedCourseEntity = courseDao.save(courseEntity);
        return AppUtil.mapCourseEntityToDto(updatedCourseEntity);
    }

    public List<CourseCount> getCourseCountByType() {
        List<CourseCount> courseCounts = new ArrayList<>();
        Map<String, Long> courseCountMap = StreamSupport.stream(courseDao.findAll().spliterator(), false)
                .collect(Collectors
                        .groupingBy(CourseEntity::getCourseType,
                                Collectors.counting()));
        courseCountMap.forEach((k, v) -> {
            courseCounts.add(new CourseCount(k, v));
        });
        return courseCounts;
    }
}
