package com.GestionalePalestre.dto;

public class UserDto {

	private String name;
	
	private String surname;
	
	private String email;
	
	private String pass;
	
	private Boolean isadmin;

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

	public Boolean getIsAdmin() {
		return isadmin;
	}

	public void setIsAdmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}
	
}
