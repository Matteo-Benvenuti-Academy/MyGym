package com.GestionalePalestre.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private Integer Id;

	@Column(name = "user_name")
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String email;
	
	@Column
	private String pass;
	
	@Column(name = "is_admin")
	private Boolean isAdmin;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Users_Courses", 
		joinColumns = { @JoinColumn(name = "userRIF", referencedColumnName = "userID") },
		inverseJoinColumns = {@JoinColumn(name = "courseRIF", referencedColumnName = "courseID") })
	private List<Course> courses = new ArrayList<Course>();

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
