package com.GestionalePalestre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GestionalePalestre.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByUniqueCode(String uniqueCode);
}
