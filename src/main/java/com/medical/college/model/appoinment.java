package com.medical.college.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "appoinments")
@Entity
public class appoinment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 100 ,nullable = false)
	private String regno;
	@Column(length = 50 , nullable = false)
	private String name;
	@Column(length = 20 , nullable = false)
	private String age;
	@Column(length = 50 , nullable = false)
	private String gender;
	@Column(length = 50 , nullable = false)
	private String contact;
	@Column(length = 50 , nullable = false)
	private String Department;
	@Column(length = 50 , nullable = false)
	private String apdate;
	@Column(length = 50 , nullable = false)
	private String email;
	@Column(length = 50 , nullable = false)
	private String blood;
	
	@Column(length = 50 , nullable = false)
	private String dr;
	@Column(length = 50 , nullable = false)
	private String postdate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getApdate() {
		return apdate;
	}
	public void setApdate(String apdate) {
		this.apdate = apdate;
	}

	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
}
