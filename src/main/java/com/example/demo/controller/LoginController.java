package com.example.demo.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.MemberLogin;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
//	@Resource
//	private HttpServletRequest request;

	@PostMapping("/login")
	public String login(@ModelAttribute MemberLogin ml, Model model, HttpServletRequest request) {
		model.addAttribute("command", request.getSession().getAttribute("Data"));
		return (String) request.getAttribute("accountData");
	}

	@GetMapping("/loginPage")
	public String login() {
		return "login";
	}
	
	@GetMapping("/welcome")
	public String welcome(Model model, HttpServletRequest request) {	
		model.addAttribute("command",request.getSession().getAttribute("memberLogin"));		
		return "welcome";
	}
	
	@GetMapping("/area")
	public String memberArea() {
		return "memberArea";				
	}
	@GetMapping("/signOut")
	public String signOut(HttpSession session) {
//		session.setAttribute("isverify", false);
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/loginAjaxPage")
	public String loginAjaxPage() {
		return "loginAjax";
	}
	
	@PostMapping("/loginAjax")
	@ResponseBody
	public Map<String, Object> loginAjax(@ModelAttribute MemberLogin ml,Model model,HttpServletRequest request ) {
		model.addAttribute("command", request.getSession().getAttribute("Data"));
		return (Map<String, Object>) request.getAttribute("accountData");
	}
	@GetMapping("/welcomeAjax")
	public String welcomeAjax(Model model,HttpSession session) {	
		model.addAttribute("command",session.getAttribute("memberLogin"));
		return "welcomeAjax";
	}
}
