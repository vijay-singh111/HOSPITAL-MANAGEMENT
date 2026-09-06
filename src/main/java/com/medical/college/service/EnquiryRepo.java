package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.college.model.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

}
