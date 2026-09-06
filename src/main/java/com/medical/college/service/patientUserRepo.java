package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.patients;


public interface patientUserRepo extends JpaRepository<patients, Integer>{

}
