package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.patientInfo;

public interface patientRepo extends JpaRepository<patientInfo, String>{

}
