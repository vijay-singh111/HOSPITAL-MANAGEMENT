
package com.medical.college.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.college.dto.appoinmentDto;
import com.medical.college.dto.patientInfoDto;
import com.medical.college.model.appoinment;

import com.medical.college.model.patientInfo;
import com.medical.college.service.appoinmentRepo;
import com.medical.college.service.patientRepo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

	@Autowired
	patientRepo parepo;

	@Autowired
	appoinmentRepo aprepo;

	@GetMapping("/hosthome")
	public String showHospitalPage(HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		// Return the hospital.html page (Thymeleaf will render it)
		return "hospital/hospitalhome"; // Renders src/main/resources/templates/hospital.html
	}

	/*
	 * @GetMapping("/logout") public String Logout(HttpSession session) {
	 * session.invalidate(); return "redirect:/hospital/patientlogin"; }
	 */
	@GetMapping("/patientlogin")
	public String ShowPatientLogin(Model model) {

		patientInfoDto dto = new patientInfoDto();
		model.addAttribute("dto", dto);
		return "hospital/patientlogin";
	}

	@PostMapping("/patientlogin")
	public String patientLogin(@ModelAttribute patientInfoDto patientInfoDto, HttpSession session,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {

		try {

			patientInfo painfo = parepo.getById(patientInfoDto.getUserid());
			if (painfo.getPassword().equals(patientInfoDto.getPassword())) {
				session.setAttribute("patientid", patientInfoDto.getUserid());
				return "redirect:/patient/docthome";

			} else {
				redirectAttributes.addFlashAttribute("msg", "invalid user");
				return "redirect:/hospital/patientlogin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("msg", "user does not exist" + e.getMessage());

			return "redirect:/hospital/patientlogin";
		}
	}

	@GetMapping("/appoinment")
	public String showAppoinment(HttpServletResponse response, HttpSession session, Model model) {

		try {

			appoinmentDto dto = new appoinmentDto();
			model.addAttribute("dto", dto);
			return "hospital/appoinment";

		} catch (Exception e) {
			// TODO: handle exception

			return "redirect:/hospital";
		}

	}

	@PostMapping("/appoinment")
	public String submitAppoinment(HttpSession session, HttpServletResponse response, @ModelAttribute appoinmentDto dto,
			RedirectAttributes attributes, Model model) {
		try {

			String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			long count = aprepo.count() + 1;
			String regNo = "REG" + today + String.format("%03d", count);
			appoinment ap = new appoinment();
			ap.setRegno(regNo);
			ap.setName(dto.getName());
			ap.setAge(dto.getAge());
			ap.setGender(dto.getGender());
			ap.setContact(dto.getContact());
			ap.setDepartment(dto.getDepartment());
			ap.setApdate(dto.getApdate());
			ap.setEmail(dto.getEmail());
			ap.setBlood(dto.getBlood());
			ap.setDr(dto.getDr());
			ap.setPostdate(new Date() + "");
			aprepo.save(ap);
			attributes.addFlashAttribute("msg", "appoinment  is Added successful");

			model.addAttribute("ap", ap);
			return "redirect:/hospital/appoinment";

		} catch (Exception ex) {
			// TODO: handle exception
			return "redirect:/patientlogin/hosthome";
		}

	}

	@GetMapping("/department")
	public String showDepartment() {
		return "hospital/department";
	}

	@GetMapping("/gallery")
	public String showGallery() {
		return "hospital/gallery";
	}

	@GetMapping("/contact")
	public String showContact() {
		return "hospital/contact";
	}
}