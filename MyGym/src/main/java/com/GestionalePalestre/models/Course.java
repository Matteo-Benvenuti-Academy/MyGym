package com.GestionalePalestre.models;

import java.time.LocalDateTime;
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
@Table(name="Courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "courseID")
	private Integer Id;

	@Column(name = "course_name")
	private String name;
	
	@Column(name="minutes_length")
	private Integer minutesLength;
	
	@Column(name = "course_date")
	private LocalDateTime date ;
	
	@Column(name = "course_level")
	private String level;
	
	@Column(name = "maxUsers")
	private Integer maxUsers;
	
	@Column(name = "unique_code")
	private String uniqueCode;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name="Users_Courses", 
		joinColumns = { @JoinColumn(name="courseRIF", referencedColumnName = "courseID") },
		inverseJoinColumns = { @JoinColumn(name="userRIF", referencedColumnName = "userID") })
	private List<User> users = new ArrayList<User>();

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

	public Integer getMinutesLength() {
		return minutesLength;
	}

	public void setMinutesLength(Integer minutesLength) {
		this.minutesLength = minutesLength;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
