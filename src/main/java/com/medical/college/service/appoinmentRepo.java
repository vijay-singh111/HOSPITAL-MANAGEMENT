package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.appoinment;

public interface appoinmentRepo extends JpaRepository<appoinment, Integer>{

}
