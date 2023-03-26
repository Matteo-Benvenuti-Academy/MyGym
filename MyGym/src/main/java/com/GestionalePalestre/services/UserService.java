package com.GestionalePalestre.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestionalePalestre.dto.CourseDto;
import com.GestionalePalestre.dto.UserDto;
import com.GestionalePalestre.models.Course;
import com.GestionalePalestre.models.User;
import com.GestionalePalestre.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CourseService courseService;

	public UserDto upsert(UserDto userDto) {
		try {
			User user = repository.save(mapper.map(userDto, User.class));

			if (user.getId() != null)
				return mapper.map(user, UserDto.class);

		} catch (Exception e) {
		}

		return null;
	}

	public UserDto findByEmailAndPass(UserDto utente) {
		User user = repository.findByEmailAndPass(utente.getEmail(),utente.getPass());
		if(user == null)
			return null;
		return mapper.map(user, UserDto.class);
		
	}

    public List<CourseDto> findAllCourses(UserDto userDto) {
        User user = repository.findByEmail(userDto.getEmail());

		List<CourseDto> coursesDto= new ArrayList<CourseDto>();

		for(Course course: user.getCourses()){
			coursesDto.add(mapper.map(course,CourseDto.class));
		}

		return coursesDto;
	}

	public CourseDto userPerformCourse(UserDto userDto, String uniqueCode) {
		User user = repository.findByEmail(userDto.getEmail());
		
		for (Course course : user.getCourses()){
			if(course.getUniqueCode().equals(uniqueCode))
				return mapper.map(course,CourseDto.class);
		}
		
		return null;
	}

	public boolean unsubscribeFromCourse(UserDto userDto,String uniqueCode) {
		User user = repository.findByEmail(userDto.getEmail());

		List<Course> courses = user.getCourses();
		
		boolean courseFound = false;
		int i;
		
		for(i = 0;i<courses.size();i++){
			if(courses.get(i).getUniqueCode().equals(uniqueCode)){
				courseFound = true;
				break;
			}
		}

		if (!courseFound)
			return false;

		courses.remove(i);
		user.setCourses(courses);
		
		try {
			User updateUser = repository.save(user);

			if (updateUser.getCourses().size() == courses.size())
				return true;

		} catch (Exception e) {
		}

		return false;
	}

    public List<CourseDto> findAllAvaibleCourses(UserDto userDto) {
		User user = repository.findByEmail(userDto.getEmail());
		List<CourseDto> allCourses = courseService.findAll();
		
		if (user == null || allCourses == null)
			return null;

		List<Course> userCourses = user.getCourses();
		List<CourseDto> availableCourses = new ArrayList<CourseDto>(); 

		boolean subscribed;
		String uniqueCode;

		for(CourseDto course : allCourses){

			subscribed = false;
			uniqueCode = course.getUniqueCode();

			for(Course userCourse : userCourses){
				if(uniqueCode.equals(userCourse.getUniqueCode()))
					subscribed = true;
			}

			if (!subscribed)
				availableCourses.add(course);

		}

		return availableCourses;

    }

	public boolean subscribeToCourse(UserDto userDto, String uniqueCode) {
		User user = repository.findByEmail(userDto.getEmail());
		
		if (user == null)
			return false;
		
		CourseDto course = courseService.addUserToCourse(user,uniqueCode);

		if (course == null)
			return false;

		return true;
	}

}