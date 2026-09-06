package com.medical.college.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.college.dto.EnquiryDto;
import com.medical.college.dto.adminInfoDto;
import com.medical.college.dto.studentInfoDto;
import com.medical.college.model.Enquiry;
import com.medical.college.model.adminInfo;
import com.medical.college.model.studentInfo;
import com.medical.college.service.EnquiryRepo;
import com.medical.college.service.adminRepo;
import com.medical.college.service.studentRepo;
import com.medical.college.sms.sender.smsSender;

import com.softpro.OnlineTest.api.SmsSender;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	
	
	@Autowired
	EnquiryRepo enrepo;
	@Autowired
	studentRepo stdrepo;
	
	@Autowired
	adminRepo adrepo;
	
	@GetMapping("/index")
	public String showIndex() {
		return "index";
	}
	
	@GetMapping("/contact")
	public String showContact(Model model) {
		
		EnquiryDto dto = new EnquiryDto();
		model.addAttribute("dto", dto);
		return "contact";
	}

	@PostMapping("/contact")
	public String submitEnquiry(@ModelAttribute EnquiryDto enquiryDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {

			Enquiry eq = new Enquiry();
			eq.setName(enquiryDto.getName());
			eq.setGender(enquiryDto.getGender());
			eq.setContactno(enquiryDto.getContactno());
			eq.setEmail(enquiryDto.getEmail());
			eq.setEnquirytext(enquiryDto.getEnquirytext());
			eq.setPosteddate(new Date() + "");
			enrepo.save(eq);
			SmsSender ss = new SmsSender();
			ss.sendSms(enquiryDto.getContactno());
	
			redirectAttributes.addFlashAttribute("msg", "Form Submited Successfully");
			return "redirect:/contact";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "somthing went wrong");
			return "redirect:/contact";
		}
	}

	
	@GetMapping("/apply")
	public String showApply(Model model) {
		studentInfoDto dto =new studentInfoDto();
		model.addAttribute("dto",dto);
		
		return "apply";
	}

	
	@PostMapping("apply")
	public String SubmitApply(@ModelAttribute studentInfoDto studentInfoDto, BindingResult result ,RedirectAttributes redirectAttributes ,HttpSession session) {
	
try {
	MultipartFile filedata= studentInfoDto.getProfilepic();
	String storageFileName = new Date().getTime() + "_" + filedata.getOriginalFilename();
	String uploadDir = "public/user/";
	Path uploadPath = Paths.get(uploadDir);
	if (!Files.exists(uploadPath)) {
		Files.createDirectories(uploadPath);
		
	}
	try(InputStream inputStream =filedata.getInputStream()){

		Files.copy(inputStream, Paths.get(uploadDir+storageFileName),StandardCopyOption.REPLACE_EXISTING);
	}
	studentInfo stdu = new studentInfo();
stdu.setName(studentInfoDto.getName());
stdu.setGender(studentInfoDto.getGender());
stdu.setEmail(studentInfoDto.getEmail());
stdu.setDob(studentInfoDto.getDob());
stdu.setCity(studentInfoDto.getCity());
stdu.setDist(studentInfoDto.getDist());
stdu.setState(studentInfoDto.getState());
	
stdu.setCountry(studentInfoDto.getCountry());
stdu.setProfilepic(storageFileName);
stdu.setProgram(studentInfoDto.getProgram());
stdu.setYear(studentInfoDto.getYear());
stdu.setUsername(studentInfoDto.getUsername());
stdu.setPassword(studentInfoDto.getPassword());
stdu.setContactno(studentInfoDto.getContactno());
stdu.setHighschool(studentInfoDto.getHighschool());
stdu.setIntermediate(studentInfoDto.getIntermediate());
stdu.setBranch(studentInfoDto.getBranch());
stdu.setResdate(new Date()+ "");
stdrepo.save(stdu);


redirectAttributes.addFlashAttribute("massage", "form submited successfully");
	return"redirect:/apply";
} catch (Exception e) {
	// TODO: handle exception
	
	redirectAttributes.addFlashAttribute("massage", "somthing went wrong");
	return"redirect:/apply";
}
	}
	
	
	
	@GetMapping("/studentlogin")
	public String showStudentLogin(Model model) {
		studentInfoDto dto =new studentInfoDto();
		model.addAttribute("dto", dto);
		
		return "studentlogin";
	}
	@PostMapping("/studentlogin")
	public String validateStudent(@ModelAttribute studentInfoDto dto ,HttpSession session ,RedirectAttributes attributes) {
		try {
			studentInfo s =stdrepo.getById(dto.getUsername());
			if (s.getPassword().equals(dto.getPassword())) {
				session.setAttribute("studentid", s.getUsername());
				return "redirect:/student/stdhome";
			}else {
				attributes.addFlashAttribute("msg", "invalid user");
			}
			return "redirect:/studentlogin";
			
		} catch (Exception e) {
			// TODO: handle exception
			attributes.addFlashAttribute("msg", "student does not exist");
			return "redirect:/studentlogin";

		}
		
		
		

	}
	

	@GetMapping("/adminlogin")
	public String showAdminLogin(Model model) {
		adminInfoDto dto = new adminInfoDto();
		model.addAttribute("dto", dto);
		return "adminlogin";
	}

	@PostMapping("/adminlogin")
	public String AdminLogin(@ModelAttribute adminInfoDto adminInfoDto, HttpSession session,
			RedirectAttributes redirectAttributes) {
		try {

			adminInfo adinfo = adrepo.getById(adminInfoDto.getUserid());
			if (adinfo.getPassword().equals(adminInfoDto.getPassword())) {
				// redirectAttributes.addFlashAttribute("msg","valid user");
				session.setAttribute("adminid", adminInfoDto.getUserid());
				return "redirect:/admin/adminhome";
			} else {
				redirectAttributes.addFlashAttribute("msg", "invalid user");
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "user does not exist" + e.getMessage());
			return "redirect:/adminlogin";
		}

	}
	
	  @GetMapping("/hospital")
	    public String showHospital() {
	        // Redirect to HospitalController (mapped at /hospital)
	        return "redirect:/hospital/hosthome";
	    }
}
