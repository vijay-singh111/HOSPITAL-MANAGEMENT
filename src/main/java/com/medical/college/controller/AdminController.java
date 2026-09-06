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

import com.medical.college.dto.QbDto;
import com.medical.college.dto.materialDto;
import com.medical.college.model.Qb;
import com.medical.college.model.material;
import com.medical.college.model.result;
import com.medical.college.model.studentInfo;
import com.medical.college.service.EnquiryRepo;
import com.medical.college.service.QbRepo;
import com.medical.college.service.materialRepo;
import com.medical.college.service.resultRepo;
import com.medical.college.service.studentRepo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	QbRepo qbrepo;
	@Autowired
	materialRepo mrepo;
	@Autowired
	EnquiryRepo erepo;
	@Autowired
	studentRepo stdrepo;

	@Autowired
	resultRepo rerepo;

	@GetMapping("/adminhome")
	public String ShowAdminHome(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {

				/*
				 * long stdcount = stdrepo.count(); model.addAttribute("stdcount", stdcount);
				 * long recount = srepo.count(); model.addAttribute("recount", recount); long
				 * encount = enrepo.count(); model.addAttribute("encount", encount);
				 */
				long stdcount = stdrepo.count();
				model.addAttribute("stdcount", stdcount);

				long encount = erepo.count();
				model.addAttribute("encount", encount);

				return "admin/adminhome";
			} else {

				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}

	}

	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/adminlogin";
	}

	@GetMapping("/addmaterial")
	public String showAddMaterial(HttpSession session, HttpServletResponse response, Model model) {
		try {

			response.setHeader("Cache-Control", "no-cache,no-store,no-revalidate");
			if (session.getAttribute("adminid") != null) {
				materialDto dto = new materialDto();
				model.addAttribute("dto", dto);
				return "admin/addmaterial";
			} else {
				return "redirect:/adminlogin";
			}

		}

		catch (Exception ex) {

			return "redirect:/adminlogin";

		}

	}

	@PostMapping("/addmaterial")
	public String CreateMaterial(HttpSession session, HttpServletResponse response, @ModelAttribute materialDto dto,
			RedirectAttributes attrib) {
		try {

			response.setHeader("Cache-Control", "no-cache,no-store,no-revalidate");
			if (session.getAttribute("adminid") != null) {

				MultipartFile filedata = dto.getFiledata();
				String storageFileName = new Date().getTime() + "_" + filedata.getOriginalFilename();
				String uploadDir = "public/material/";
				Path uploadPath = Paths.get(uploadDir);
				if (!Files.exists(uploadPath)) {

					Files.createDirectories(uploadPath);

				}

				try (InputStream inputStream = filedata.getInputStream()) {

					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);

				}

				material m = new material();
				m.setProgram(dto.getProgram());
				m.setBranch(dto.getBranch());
				m.setYear(dto.getYear());
				m.setMaterialtype(dto.getMaterialtype());
				m.setSubject(dto.getSubject());
				m.setTopic(dto.getTopic());
				m.setFilename(storageFileName);
				m.setPosteddate(new Date() + "");
				mrepo.save(m);
				attrib.addFlashAttribute("msg", "Material is added");
				return "redirect:/admin/addmaterial";
			} else {
				return "redirect:/adminlogin";
			}

		}

		catch (Exception ex) {

			return "redirect:/adminlogin";

		}

	}

	@GetMapping("/viewstudymaterial")
	public String ViewStudyMaterial(HttpSession session, HttpServletResponse response, Model model) {
		try {

			response.setHeader("Cache-Control", "no-cache,no-store,no-revalidate");
			if (session.getAttribute("adminid") != null) {
				List<material> mlist = mrepo.findAll();
				model.addAttribute("mlist", mlist);
				return "admin/viewstudymaterial";
			} else {
				return "redirect:/adminlogin";
			}

		}

		catch (Exception ex) {

			return "redirect:/adminlogin";

		}

	}

	@GetMapping("/viewstudymaterial/deletematerial")
	public String DeleteMaterial(HttpSession session, HttpServletResponse response, @RequestParam int id) {
		try {

			response.setHeader("Cache-Control", "no-cache,no-store,no-revalidate");
			if (session.getAttribute("adminid") != null) {

				material m = mrepo.getById(id);
				Path filePath = Paths.get("public/material/" + m.getFilename());
				try {
					Files.delete(filePath);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				mrepo.delete(m);
				return "redirect:/admin/viewstudymaterial";
			} else {
				return "redirect:/adminlogin";
			}

		}

		catch (Exception ex) {

			return "redirect:/adminlogin";

		}

	}

	@GetMapping("/addqb")
	public String AddQb(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				QbDto dto = new QbDto();
				model.addAttribute("dto", dto);
				return "admin/addqb";
			} else {

				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}

	}

	@PostMapping("/addqb")
	public String CreateQb(HttpSession session, HttpServletResponse response, @ModelAttribute QbDto dto,
			RedirectAttributes attrib) {
		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				Qb qb = new Qb();
				qb.setYear(dto.getYear());
				qb.setQuestion(dto.getQuestion());
				qb.setA(dto.getA());
				qb.setB(dto.getB());
				qb.setC(dto.getC());
				qb.setD(dto.getD());
				qb.setCorrect(dto.getCorrect());
				qbrepo.save(qb);
				attrib.addFlashAttribute("msg", "Question is added");
				return "redirect:/admin/addqb";
			} else {

				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}

	}

	@GetMapping("/viewqb")
	public String ViewQB(HttpSession session, Model model, HttpServletResponse response) {
		try {
			if (session.getAttribute("adminid") != null) {
				List<Qb> qblist = qbrepo.findAll();
				model.addAttribute("qblist", qblist);
				return "admin/viewqb";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			return "redirect:/adminlogin";

		}

	}

	@GetMapping("/viewqb/delete")
	public String DeleteQB(@RequestParam int id, RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			if (session.getAttribute("adminid") != null) {

				Qb qb = qbrepo.findById(id).get();
				qbrepo.delete(qb);
				redirectAttributes.addFlashAttribute("msg", "question Deleted succesfully");
				return "redirect:/admin/viewqb";

			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			return "redirect:/adminlogin";

		}

	}

	@GetMapping("/viewstudent")
	public String ShowViewStudent(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				List<studentInfo> slist = stdrepo.findAll();
				model.addAttribute("slist", slist);
				return "admin/viewstudent";
			} else {

				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}

	}

	@GetMapping("/viewstudent/delete")
	public String DeleteStudent(@RequestParam String username, HttpSession session,
			RedirectAttributes redirectAttributes) {
		try {
			if (session.getAttribute("adminid") != null) {
				studentInfo st = stdrepo.findById(username).get();
				Path filePath = Paths.get("public/user/" + st.getProfilepic());

				try {
					Files.delete(filePath);
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
				stdrepo.delete(st);
				redirectAttributes.addFlashAttribute("msg", username + "is deleted successfully");
				return "redirect:/admin/viewstudent";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			return "redirect:/adminlogin";
		}
	}

	@GetMapping("/manageresult")
	public String ViewManageResult(HttpSession session, Model model, HttpServletResponse response) {
		try {
			if (session.getAttribute("adminid") != null) {

				List<result> rlist = rerepo.findAll();
				model.addAttribute("rlist", rlist);

				return "admin/manageresult";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			return "redirect:/adminlogin";

		}

	}

	@GetMapping("/manageresult/delete")
	public String DeleteManageResult(@RequestParam String username, HttpSession session,
			RedirectAttributes redirectAttributes) {
		try {
			if (session.getAttribute("adminid") != null) {
				result dq = rerepo.findById(username).get();
				rerepo.delete(dq);

				// redirectAttributes.addFlashAttribute("msg", "is deleted successfully");
				return "redirect:/admin/manageresult";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			return "redirect:/adminlogin";
		}
	}

}