package com.medical.college.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.medical.college.dto.DoctorDto;
import com.medical.college.dto.patientDto;
import com.medical.college.model.appoinment;
import com.medical.college.model.doctorInfo;
import com.medical.college.model.patients;
import com.medical.college.service.DoctorRepo;
import com.medical.college.service.appoinmentRepo;
import com.medical.college.service.patientUserRepo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/patient")
public class patientController {

	@Autowired
	DoctorRepo doc;
	@Autowired
	appoinmentRepo aprepo;
	@Autowired
	patientUserRepo patient;

	@GetMapping("/docthome")
	public String showHospitalPage(HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		// Return the hospital.html page (Thymeleaf will render it)
		return "patient/docthome"; // Renders src/main/resources/templates/hospital.html
	}

	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/hospital/patientlogin";
	}

	@GetMapping("/doctorlist")
	public String showDoctorlist(HttpServletResponse response, HttpSession session, Model model) {

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("patientid") != null) {
				List<doctorInfo> dlist = doc.findAll();
				model.addAttribute("dlist", dlist);

				return "patient/doctorlist";

			} else {
				return "redirect:/patientlogin";

			}

		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/addDoctor")
	public String showDoctor(HttpServletResponse response, HttpSession session, Model model) {

		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("patientid") != null) {
				DoctorDto dto = new DoctorDto();
				model.addAttribute("dto", dto);
				return "patient/addDoctor";

			} else {

				return "redirect:/patrintlogin";
			}

		} catch (Exception e) {
			// TODO: handle exception

			return "redirect:/patientlogin";
		}

	}

	@PostMapping("/addDoctor")
	public String addDoctor(HttpSession session, HttpServletResponse response, @ModelAttribute DoctorDto dto,
			RedirectAttributes attributes) {
		try {

			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("patientid") != null) {

				MultipartFile filedata = dto.getDoctorpic();
				String storageFileName = new Date().getTime() + "_" + filedata.getOriginalFilename();
				String uploadDir = "public/doctor/";
				Path uploadPath = Paths.get(uploadDir);
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);

				}
				try (InputStream inputStream = filedata.getInputStream()) {

					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);
				}

				doctorInfo doct = new doctorInfo();

				doct.setName(dto.getName());
				doct.setAge(dto.getAge());
				doct.setDob(dto.getDob());
				doct.setGender(dto.getGender());
				doct.setSpecialization(dto.getSpecialization());
				doct.setEmail(dto.getEmail());
				doct.setPhone(dto.getPhone());
				doct.setDoctorpic(storageFileName);
				doct.setPostdate(new Date() + "");
				doc.save(doct);
				attributes.addFlashAttribute("msg", "Doctor is Added successful");
				return "redirect:/patient/addDoctor";
			} else {
				return "redirect:/patientlogin";
			}

		} catch (Exception ex) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/doctorlist/delete")
	public String Deletelist(HttpServletResponse response, HttpSession session, @RequestParam Integer id) {

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("patientid") != null) {
				doctorInfo d = doc.findById(id).get();
				doc.delete(d);
				return "redirect:/patient/doctorlist";

			} else {
				return "redirect:/patientlogin";

			}

		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/patientlist")
	public String showPatientList(HttpServletResponse response, HttpSession session, Model model) {

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("patientid") != null) {
				List<patients> plist = patient.findAll();
				model.addAttribute("plist", plist);

				return "patient/patientlist";

			} else {
				return "redirect:/patientlogin";

			}

		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/addPatient")
	public String showPatient(HttpServletResponse response, HttpSession session, Model model) {

		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("patientid") != null) {
				patientDto dto = new patientDto();
				model.addAttribute("dto", dto);
				return "patient/addPatient";

			} else {

				return "redirect:/patrintlogin";
			}

		} catch (Exception e) {
			// TODO: handle exception

			return "redirect:/patientlogin";
		}

	}

	@PostMapping("/addPatient")
	public String addDoctor(HttpSession session, HttpServletResponse response, @ModelAttribute patientDto dto,
			RedirectAttributes attributes) {
		try {

			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("patientid") != null) {

				patients pt = new patients();
				pt.setName(dto.getName());
				pt.setAge(dto.getAge());
				pt.setGender(dto.getGender());
				pt.setBlood(dto.getBlood());
				pt.setEmail(dto.getEmail());
				pt.setAddress(dto.getAddress());
				pt.setContact(dto.getContact());
				pt.setPostdate(new Date() + "");
				patient.save(pt);

				attributes.addFlashAttribute("msg", "Patient is Added successful");
				return "redirect:/patient/addPatient";
			} else {
				return "redirect:/patientlogin";
			}

		} catch (Exception ex) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/patientlist/delete")
	public String DeletePatient(HttpServletResponse response, HttpSession session, @RequestParam Integer id) {

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("patientid") != null) {
				patients d = patient.findById(id).get();
				patient.delete(d);
				return "redirect:/patient/patientlist";

			} else {
				return "redirect:/patientlogin";

			}

		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/appoinmentlist")
	public String viewAppoinment(HttpServletResponse response, HttpSession session, Model model) {

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("patientid") != null) {
				List<appoinment> alist = aprepo.findAll();
				model.addAttribute("alist", alist);

				return "patient/appoinmentlist";

			} else {
				return "redirect:/patientlogin";

			}

		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

	@GetMapping("/appoinmentlist/delete")
	public String DeleteAppoinment(HttpServletResponse response, HttpSession session, @RequestParam Integer id) {

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("patientid") != null) {

				appoinment ap = aprepo.findById(id).get();
				aprepo.delete(ap);
				return "redirect:/patient/appoinmentlist";

			} else {
				return "redirect:/patientlogin";

			}

		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/patientlogin";
		}

	}

}
