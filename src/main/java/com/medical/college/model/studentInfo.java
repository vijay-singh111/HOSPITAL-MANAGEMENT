package com.medical.college.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="studentInfo")
public class studentInfo {

	@Column(length = 50, nullable = false)
	private String name;
	@Column(length = 50, nullable = false)
	private String gender;
	@Column(length = 50, nullable = false)
	private String email;
	@Column(length = 50, nullable = false)
	private String dob;
	@Column(length = 50, nullable = false)
	private String city;
	@Column(length = 50, nullable = false)
	private String dist;
	@Column(length = 50, nullable = false)
	private String state;
	@Column(length = 50, nullable = false)
	private String country;
	@Column(length = 60, nullable = false)
	private String profilepic;
	@Column(length = 50, nullable = false)
	private String program;
	@Column(length = 50, nullable = false)
	private String year;
	@Id
	@Column(length = 50)
	private String username;
	@Column(length = 50, nullable = false)
	private String password;
	@Column(length = 50, nullable = false)
	private String contactno;
	@Column(length = 50, nullable = false)
	private String highschool;
	@Column(length = 50, nullable = false)
	private String intermediate;
	@Column(length = 50, nullable = false)
	private String branch;
	@Column(length = 50, nullable = false)
	private String resdate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
	
	
	
	
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getHighschool() {
		return highschool;
	}
	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}
	public String getIntermediate() {
		return intermediate;
	}
	public void setIntermediate(String intermediate) {
		this.intermediate = intermediate;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getResdate() {
		return resdate;
	}
	public void setResdate(String resdate) {
		this.resdate = resdate;
		
		
	}
	public String getCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
