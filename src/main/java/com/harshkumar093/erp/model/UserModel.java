package com.harshkumar093.erp.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserModel {

	@Id
	@GeneratedValue
	private long userId;

	private String name;

	private Roles role;

	private String email;

	private String password;

	protected UserModel() {
	}

	public UserModel(long userId, String name, Roles role, String email, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public Roles getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	@OneToMany(mappedBy = "userModel")
	List<AttendanceModel> attendanceModelList;

}