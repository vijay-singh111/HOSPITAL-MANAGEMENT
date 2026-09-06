package com.medical.college.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "result")
@Entity
public class result {
	@Id
	@Column(length = 50)
	private String username;
	@Column(length = 50 , nullable = false)
	private String email;
	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 500, nullable = false)
	private String program;

	////@Column(length = 100, nullable = false)
	//private String course;

	@Column(length = 100, nullable = false)
	private String branch;
	@Column(length = 100, nullable = false)
	private String year;

	@Column(length = 15, nullable = false)
	private String contactno;

	private int totalmarks;
	private int getmarks;
	@Column(length = 15, nullable = false)
	private String status;
	
	
		public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
		public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public int getTotalmarks() {
		return totalmarks;
	}
	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}
	public int getGetmarks() {
		return getmarks;
	}
	public void setGetmarks(int getmarks) {
		this.getmarks = getmarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	


}
