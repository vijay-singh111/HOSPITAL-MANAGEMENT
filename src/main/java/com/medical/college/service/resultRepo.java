package com.medical.college.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medical.college.model.result;

public interface resultRepo extends JpaRepository<result, String>{
	@Query(value = "select  status from result where username=:username", nativeQuery = true)
	String getStuatus(@Param("username") String username);
}
