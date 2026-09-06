package com.medical.college.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class doctorInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 60 , nullable = false)
	private String name;
	@Column(length = 40 , nullable = false)
	private String age;
	@Column(length = 30 , nullable = false)
	private String dob;
	@Column(length = 30 , nullable = false)
	private String gender;
	@Column(length = 60 , nullable = false)
	private String Specialization;
	@Column(length = 60 , nullable = false)
	private String doctorpic;
	@Column(length = 60 , nullable = false)
	private String email;
	@Column(length = 60 , nullable = false)
	private String phone;
	@Column(length = 30 , nullable = false)
	private String postdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpecialization() {
		return Specialization;
	}
	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}
	public String getDoctorpic() {
		return doctorpic;
	}
	public void setDoctorpic(String doctorpic) {
		this.doctorpic = doctorpic;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
}
