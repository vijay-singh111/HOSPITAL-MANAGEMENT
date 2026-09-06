package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.studentInfo;

public interface studentRepo extends JpaRepository<studentInfo , String> {



}
