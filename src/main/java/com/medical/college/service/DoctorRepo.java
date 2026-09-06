package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.doctorInfo;

public interface DoctorRepo extends JpaRepository<doctorInfo, Integer> {

}
