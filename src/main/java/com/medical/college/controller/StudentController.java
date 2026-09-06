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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.medical.college.dto.studentInfoDto;
import com.medical.college.model.Qb;
import com.medical.college.model.material;
import com.medical.college.model.result;
import com.medical.college.model.studentInfo;
import com.medical.college.service.QbRepo;
import com.medical.college.service.materialRepo;
import com.medical.college.service.resultRepo;
import com.medical.college.service.studentRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	materialRepo mrepo;
	@Autowired
	studentRepo srepo;
	@Autowired
	QbRepo qbrepo;
	@Autowired
	resultRepo rerepo;

	@GetMapping("/stdhome")
	public String showStudentHome(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("studentid") != null) {
				studentInfo sinfo = srepo.findById(session.getAttribute("studentid").toString()).get();
				model.addAttribute("sinfo", sinfo);
				studentInfoDto dto = new studentInfoDto();
				model.addAttribute("dto", dto);
				return "student/studenthome";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {

			return "redirect:/studentlogin";
		}
	}

	@GetMapping("/printp")
	public String showData(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("studentid") != null) {
				studentInfo sinfo = srepo.findById(session.getAttribute("studentid").toString()).get();
				model.addAttribute("sinfo", sinfo);

				return "student/printf";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {

			return "redirect:/studentlogin";
		}
	}

	@PostMapping("/stdhome")
	public String UploadPic(HttpSession session, RedirectAttributes redirectAttributes,
			@ModelAttribute studentInfoDto studentInfoDto) {
		if (session.getAttribute("studentid") != null) {
			try {

				MultipartFile filedata = studentInfoDto.getProfilepic();
				String storageFileName = new Date().getTime() + "_" + filedata.getOriginalFilename();
				String uploadDir = "public/user/";
				Path uploadPath = Paths.get(uploadDir);

				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				try (InputStream inputStream = filedata.getInputStream()) {
					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);

					studentInfo std = srepo.findById(session.getAttribute("studentid").toString()).get();
					std.setProfilepic(storageFileName);
					srepo.save(std);
					redirectAttributes.addFlashAttribute("msg", "Profile upload success");
					return "redirect:/student/stdhome";
				} catch (Exception e) {
					// TODO: handle exception
				}

				return "redirect:/student/stdhome";

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("msg", "something went wrong" + e.getMessage());
				return "redirect:/student/stdhome";
			}
		} else {
			return "redirect:/studentlogin";
		}

	}

	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/studentlogin";
	}

	@GetMapping("/change")
	public String showChange(HttpSession session, HttpServletResponse response) {
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("studentid") != null) {
				return "student/change";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {

			return "redirect:/studentlogin";
		}
	}

	@PostMapping("/change")
	public String changePassword(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes attrib) {
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("studentid") != null) {
				studentInfo s = srepo.getById(session.getAttribute("studentid").toString());
				String oldpassword = request.getParameter("oldpassword");
				String newpassword = request.getParameter("newpassword");
				String confirmpassword = request.getParameter("confirmpassword");
				if (!newpassword.equals(confirmpassword)) {
					attrib.addFlashAttribute("msg", "newpassword and confirmpassword are not matched");
					return "redirect:/student/change";
				}
				if (!oldpassword.equals(s.getPassword())) {
					attrib.addFlashAttribute("msg", "oldpassword is not matched");
					return "redirect:/student/change";
				}
				s.setPassword(newpassword);
				srepo.save(s);
				return "redirect:/student/logout";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {

			return "redirect:/studentlogin";
		}
	}

	@GetMapping("/givetest")
	public String showGiveTest() {
		return "student/givetest";
	}

	@GetMapping("/starttest")
	public String showStartTest(HttpSession session, HttpServletResponse response, Model model,
			RedirectAttributes attrib) {
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("studentid") != null) {
				studentInfo sinfo = srepo.findById(session.getAttribute("studentid").toString()).get();
				model.addAttribute("sinfo", sinfo);
				String status = rerepo.getStuatus(sinfo.getUsername());

				try {
					if (status.equals("true")) {
						attrib.addFlashAttribute("msg", "you have alrady given the test");
						return "redirect:/student/givetest";
					} else {
						String year = sinfo.getYear();
						List<Qb> qlist = qbrepo.findQbByYear(year);
						Gson gson = new Gson();
						String json = gson.toJson(qlist);
						model.addAttribute("json", json);
						model.addAttribute("tt", qlist.size() / 2);
						model.addAttribute("tq", qlist.size());

						return "student/starttest";
					}

				} catch (Exception e) {
					String year = sinfo.getYear();
					List<Qb> qlist = qbrepo.findQbByYear(year);
					Gson gson = new Gson();
					String json = gson.toJson(qlist);
					model.addAttribute("json", json);
					model.addAttribute("tt", qlist.size() / 2);
					model.addAttribute("tq", qlist.size());

					return "student/starttest";
				}

			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {

			return "redirect:/studentlogin";
		}

	}

	@GetMapping("/testover")
	public String TestOver(HttpSession session, HttpServletResponse response, @RequestParam int s,
			@RequestParam int t) {
		try {
			response.setHeader("Cache-Control", "no-cache no-store ,must-revalidate");
			if (session.getAttribute("studentid") != null) {

				studentInfo si = srepo.getById(session.getAttribute("studentid").toString());
				result rs = new result();
				rs.setUsername(si.getUsername());

				rs.setName(si.getName());
				rs.setEmail(si.getEmail());
				rs.setProgram(si.getProgram());
				;
				rs.setBranch(si.getBranch());
				rs.setYear(si.getYear());
				rs.setContactno(si.getContactno());
				rs.setTotalmarks(t);
				rs.setGetmarks(s);
				rs.setStatus("true");
				rerepo.save(rs);

				return "student/testover";
			} else {
				return "redirect:/student";
			}
		} catch (Exception e) {
			return "redirect:/student";
		}

	}

	@GetMapping("/viewassignment")
	public String showAssignment(HttpSession session, Model model) {

		try {
			if (session.getAttribute("studentid") != null) {
				studentInfo s = srepo.getById(session.getAttribute("studentid").toString());
				String program = s.getProgram();
				String branch = s.getBranch();
				String year = s.getYear();
				String materialtype = "assign";
				List<material> mlist = mrepo.getmaterial(program, branch, year, materialtype);
				model.addAttribute("mlist", mlist);

				return "student/viewassignment";
			} else {
				return "redirect:/studentlogin";
			}

		} catch (Exception e) {
			return "redirect:/studentlogin";
		}

	}

	@GetMapping("/viewstudymaterial")
	public String showStudyMaterial(HttpSession session, Model model) {

		try {
			if (session.getAttribute("studentid") != null) {

				studentInfo s = srepo.getById(session.getAttribute("studentid").toString());
				String program = s.getProgram();
				String branch = s.getBranch();
				String year = s.getYear();
				String materialtype = "smat";
				List<material> mlist = mrepo.getmaterial(program, branch, year, materialtype);
				model.addAttribute("mlist", mlist);
				return "student/viewstudymaterial";
			} else {
				return "redirect:/stulogin";
			}

		} catch (Exception e) {
			return "redirect:/stulogin";
		}

	}

}
