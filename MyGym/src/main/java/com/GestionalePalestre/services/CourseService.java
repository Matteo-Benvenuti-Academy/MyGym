package com.GestionalePalestre.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestionalePalestre.dto.CourseDto;
import com.GestionalePalestre.models.Course;
import com.GestionalePalestre.models.User;
import com.GestionalePalestre.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private ModelMapper mapper ;

	public List<CourseDto> findAll() {

		List<Course> courses = repository.findAll();
		List<CourseDto> coursesDto =  new ArrayList<CourseDto>();

		for(Course course : courses)
			coursesDto.add(mapper.map(course,CourseDto.class));

		return coursesDto;

	
	
	}

	public CourseDto addUserToCourse(User user ,String uniqueCode){
		Course course = repository.findByUniqueCode(uniqueCode);
		
		if(course == null) 
			return null;

		course.getUsers().add(user);

		Course newCourse = repository.save(course);

		String userEmail = user.getEmail();
		boolean subscribed = false;
		
		for (User courseUser : newCourse.getUsers()){
			if(courseUser.getEmail().equals(userEmail)){
				subscribed = true;
				break;
			}
		}

		if (subscribed)
			return mapper.map(newCourse,CourseDto.class);

		return null;
	}

	public boolean deleteCourse(String uniqueCode){
		Course course = repository.findByUniqueCode(uniqueCode);

		if(course == null)
			return false;
		
		try {
			repository.delete(course);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public CourseDto addCourse(CourseDto courseDto){
		Course course = mapper.map(courseDto, Course.class);

		String name = course.getName();
		String level = course.getLevel();

		if(name.length() > 2)
			name = name.substring(0, 3);
		if(level.length()>2)
			level = level.substring(0, 3);

		course.setUniqueCode(name + level + UUID.randomUUID().toString());

		try {
			Course newCourse = repository.save(course);
			
			if(newCourse.getId() != null)
				return mapper.map(newCourse,CourseDto.class);
		} catch (Exception e) {
		}
		
		return null;
	}
}