package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.adminInfo;

public interface adminRepo extends JpaRepository<adminInfo, String> {

}
