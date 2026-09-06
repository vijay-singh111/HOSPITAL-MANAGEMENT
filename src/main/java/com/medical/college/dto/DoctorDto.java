package com.medical.college.dto;

import org.springframework.web.multipart.MultipartFile;

public class DoctorDto {

	private String name;
	
	private String age;
	private String dob;
	private String gender;
	private String Specialization;
	private MultipartFile doctorpic;
	private String email;
	private String phone;
	private String postdate;
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
	public MultipartFile getDoctorpic() {
		return doctorpic;
	}
	public void setDoctorpic(MultipartFile doctorpic) {
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
