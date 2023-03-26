package com.GestionalePalestre.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseDto {

	private String name;

	private Integer minutesLength;

	private LocalDateTime date;

	private String level;
	
	private Integer maxUsers;

	private String uniqueCode;

	private List<UserDto> users = new ArrayList<UserDto>();

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

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

}
