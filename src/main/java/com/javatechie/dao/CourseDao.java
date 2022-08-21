package com.javatechie.dao;

import com.javatechie.model.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<CourseEntity,Integer> {
}
