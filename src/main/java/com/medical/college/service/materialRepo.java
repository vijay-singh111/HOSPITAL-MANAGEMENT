package com.medical.college.service;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medical.college.model.material;

public interface materialRepo extends JpaRepository<material, Integer> {
	@Query("SELECT m from material m where m.program=:program and m.branch=:branch and m.year=:year and m.materialtype=:materialtype")
	List<material> getmaterial(String program, String branch, String year, String materialtype);
	
	
}
