package com.jsp.job_portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.job_portal.dto.JobSeeker;
import com.jsp.job_portal.dto.Recruiter;
import com.jsp.job_portal.helper.AES;
import com.jsp.job_portal.repository.JobSeekerRepository;
import com.jsp.job_portal.repository.RecruiterRepository;
import com.jsp.job_portal.service.JobSeekerService;

import jakarta.servlet.http.HttpSession;

@Controller

public class GeneralController {

	@Autowired
	JobSeekerRepository jobSeekerRepository;

	@Autowired
	JobSeekerService service ;

	@Autowired
	RecruiterRepository recruiterRepository;

	@GetMapping("/")
	public String loadHome() {
		return "home.html";
	}

	@GetMapping("/about-us")
	public String loadAbout() {
		return "about-us.html";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact.html";
	}

	@GetMapping("/privacy-policy")
	public String services() {
		return "privacy-policy.html";
	}

	@GetMapping("/terms")
	public String loadTerms() {
		return "terms.html";
	}

	@GetMapping("/login")
	public String loadLogin() {
		return "login.html";
	}

	@PostMapping("/login")
	public String login(@RequestParam("emph") String emph, @RequestParam("password") String password,
			HttpSession session) {
		Long mobile = null;
		String email = null;

		try {
			mobile = Long.parseLong(emph);
			Recruiter recruiter = recruiterRepository.findByMobile(mobile);
			JobSeeker jobSeeker = jobSeekerRepository.findByMobile(mobile);
			if (recruiter == null && jobSeeker == null) {
				session.setAttribute("error", "Invalid Mobile Number");
				return "redirect:/login";
			} else {
				if (recruiter != null) {
					if (AES.decrypt(recruiter.getPassword()).equals(password)) {
						session.setAttribute("success", "Login Success as Recruiter");
						session.setAttribute("recruiter", recruiter);
						return "redirect:/recruiter/home";
					} else {
						session.setAttribute("error", "Invalid Password");
						return "redirect:/login";
					}
				} else {
					if (AES.decrypt(jobSeeker.getPassword()).equals(password)) {
						session.setAttribute("success", "Login Success as JobSeeker");
						session.setAttribute("jobSeeker", jobSeeker);
						return "redirect:/jobseeker/home";
					} else {
						session.setAttribute("error", "Invalid Password");
						return "redirect:/login";
					}
				}
			}

		} catch (NumberFormatException e) {
			email = emph;
			Recruiter recruiter = recruiterRepository.findByEmail(email);
			JobSeeker jobSeeker = jobSeekerRepository.findByEmail(email);
			if (recruiter == null && jobSeeker == null) {
				session.setAttribute("error", "Invalid Email");
				return "redirect:/login";
			} else {
				if (recruiter != null) {
					if (AES.decrypt(recruiter.getPassword()).equals(password)) {
						session.setAttribute("success", "Login Success as Recruiter");
						session.setAttribute("recruiter", recruiter);
						return "redirect:/recruiter/home";
					} else {
						session.setAttribute("error", "Invalid Password");
						return "redirect:/login";
					}
				} else {
					if (AES.decrypt(jobSeeker.getPassword()).equals(password)) {
						session.setAttribute("success", "Login Success as JobSeeker");
						session.setAttribute("jobSeeker", jobSeeker);
						return "redirect:/jobseeker/home";
					} else {
						session.setAttribute("error", "Invalid Password");
						return "redirect:/login";
					}
				}
			}

		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("jobSeeker");
		session.removeAttribute("recruiter");
		session.setAttribute("success", "Logged Out Successfully");
		return "redirect:/";
	}
@GetMapping("/forgot-password")
public String loadForgotPassword(){
	return "forgot-password.html";
}
}

// @PostMapping("/forgot-password")
// public String forgotPassword(@RequestParam String email, HttpSession session) {
// 	return service.forgotPassword(email, session);
// }

// @GetMapping("/reset-password")
// public String loadResetPassword(@RequestParam("token") String token, HttpSession session) {
// 	boolean isValid =service.validatePasswordResetToken(token);
// 	if (!isValid) {
// 		session.setAttribute("message", "Invalid or expired password reset token.");
// 		return "redirect:/forgot-password";
// 	}
// 	session.setAttribute("token", token);
// 	return "reset-password.html";
// }

// @PostMapping("/reset-password")
// public String resetPassword(@RequestParam("password") String password,
// 							@RequestParam("confirmPassword") String confirmPassword,
// 							HttpSession session) {
// 	String token = (String) session.getAttribute("token");
// 	if (token == null) {
// 		session.setAttribute("message", "Invalid session. Please try resetting your password again.");
// 		return "redirect:/forgot-password";
// 	}
// 	if (!password.equals(confirmPassword)) {
// 		session.setAttribute("message", "Passwords do not match.");
// 		return "redirect:/reset-password?token=" + token;
// 	}
// 	return service.resetPassword(token, password, session);
// }
// }


