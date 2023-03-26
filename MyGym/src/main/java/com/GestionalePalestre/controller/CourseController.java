package com.GestionalePalestre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.GestionalePalestre.dto.CourseDto;
import com.GestionalePalestre.dto.UserDto;
import com.GestionalePalestre.services.CourseService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("MyGym/admin")
@EnableWebMvc
public class CourseController {

    @Autowired
	private CourseService service;

	@GetMapping("courses/remove")
	public String removeCourses(HttpServletRequest request,Model model){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");
        
		if(user == null)
			return "redirect:/MyGym";

		List<CourseDto> allCourses =  service.findAll();

		if (allCourses == null)
			return "redirect:/MyGym/user/info";

		model.addAttribute("Courses", allCourses);
		return "removeCourses";
	}

	@GetMapping("courses/remove/{uniqueCode}")
	public String deleteCourse(HttpServletRequest request, @PathVariable String uniqueCode){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");
        
		if(user == null)
			return "redirect:/MyGym";

		service.deleteCourse(uniqueCode);

		return "redirect:/MyGym/admin/courses/remove";
	}


	
	@GetMapping("course/add")
	public String modifyCourses(HttpServletRequest request,Model model){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");
        
		if(user == null)
			return "redirect:/MyGym";
		
		model.addAttribute("course", new CourseDto());
		return "createCourses";
	}


	@PostMapping("course/save")
	public String saveCourse(HttpServletRequest request, @ModelAttribute CourseDto course){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");
        
		if(user == null)
			return "redirect:/MyGym";

		CourseDto newCourse = service.addCourse(course);

		if(newCourse == null){
			return "redirect:/MyGym/admin/course/add";
		}

		return "redirect:/MyGym/user/allCourses";
	}

}