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

import com.GestionalePalestre.dto.UserDto;
import com.GestionalePalestre.dto.CourseDto;
import com.GestionalePalestre.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("MyGym")
@EnableWebMvc
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public String redirectToLoginPage(){
		return "redirect:MyGym/";
	}

	@GetMapping("/")
	public String loginPage(HttpServletRequest request,Model model) {
		if(this.checkSession(request) != null){
			return "redirect:/MyGym/user/info";
		}
		
		this.buildModel(model);
		return "loginPage";
	}

	@PostMapping("login/checkCredentials")
	public String checkCredentials(@ModelAttribute UserDto objDto, HttpServletRequest request) {
		UserDto user = service.findByEmailAndPass(objDto);
		
		if (user != null){
			HttpSession session = request.getSession();
			session.setAttribute("MyGymUser", user);
			session.setMaxInactiveInterval(600);
		}
	 	
		return "redirect:/MyGym/";
	}

	@GetMapping("signUp")
	public String signUp(Model model) {
		this.buildModel(model);
		return "userForm";
	}

	@PostMapping("save/user")
	public String saveUser(@ModelAttribute UserDto objDto, HttpServletRequest request) {

		UserDto user = service.upsert(objDto);


		if (user != null) {
			return "redirect:/MyGym/";
		}

		return "redirect:/MyGym/signUp";
	}

	@GetMapping("user/info")
	public String userInfo(HttpServletRequest request,Model model) {
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");

		if(user == null)
			return "redirect:/MyGym";

		model.addAttribute("user", user);
		return "userInfo";
	}

	@GetMapping("user/myCourses")
	public String myCourses(HttpServletRequest request,Model model){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");

		if(user == null)
			return "redirect:/MyGym";

		List<CourseDto> courses = service.findAllCourses(user); 
		
		model.addAttribute("user", user);
		model.addAttribute("Courses", courses);
		return "myCourses";
	}

	@GetMapping("course/{uniqueCode}")
	public String courseInfo(HttpServletRequest request, @PathVariable String uniqueCode,Model model){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");

		if(user == null)
			return "redirect:/MyGym";
		CourseDto course = service.userPerformCourse(user,uniqueCode);

		if(course == null)
			return "redirect:/MyGym/user/myCourses";
		
		model.addAttribute("user", user);
		model.addAttribute("course", course);
		return "courseInfo";
	}

	@GetMapping("course/subscribe/{uniqueCode}")
	public String courseSubscribe(HttpServletRequest request, @PathVariable String uniqueCode,Model model){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");

		if(user == null || !service.subscribeToCourse(user,uniqueCode))
			return "redirect:/MyGym";


		return "redirect:/MyGym/user/allCourses";
	}
	
	@PostMapping("course/unsubscribe" )
	public String unsubscribeFromCourse(@ModelAttribute CourseDto course, HttpServletRequest request){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");

		if(user == null || !service.unsubscribeFromCourse(user,course.getUniqueCode()))
			return "redirect:/MyGym";
        
		return "redirect:/MyGym/user/myCourses";
	}

	
	@GetMapping("user/allCourses")
	public String allCourses(HttpServletRequest request,Model model){
		UserDto user = (UserDto) request.getSession().getAttribute("MyGymUser");

		if(user == null)
			return "redirect:/MyGym";

		List<CourseDto> courses = service.findAllAvaibleCourses(user);

		if(courses == null)
			return "redirect:/MyGym";


		model.addAttribute("user", user);
		model.addAttribute("Courses", courses);
		return "allCourses";
	}

	@GetMapping("error")
	public String error() {
		return "error";
	}

	private UserDto checkSession(HttpServletRequest request) {
		HttpSession sessione = request.getSession();
		return (UserDto) sessione.getAttribute("MyGymUser");
	}

	private void buildModel(Model model) {
		UserDto persona = new UserDto();
		model.addAttribute("user", persona);
	}

}
