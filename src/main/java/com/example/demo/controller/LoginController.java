package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.MemberLogin;
import com.example.demo.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService user;
	@Resource
	private HttpServletRequest request;
	@Resource
	private HttpSession session;

	@PostMapping("/login")
	public String Login(@ModelAttribute MemberLogin ml, Model model) {
		MemberLogin memberLogin=user.VerificationUser(ml.getAccount(), ml.getPassword());		
		String error=null;
		if(memberLogin!=null) {
			model.addAttribute("command", memberLogin);
			return "welcome";
		}
		error=user.ErrorPassword(ml.getAccount());
		if(error==null){
			error = "帳號密碼出錯";
		}	
		request.setAttribute("error", error);	
		return "login";		
	}

	@GetMapping("/loginPage")
	public String Login() {
		return "login";
	}
	@GetMapping("/welcome")
	public String welcome(Model model) {	
		model.addAttribute("command",session.getAttribute("memberLogin"));
		return "welcome";
	}
	
	@GetMapping("/area")
	public String memberArea() {
		return "memberArea";				
	}
	@GetMapping("/signOut")
	public String signOut() {
//		session.setAttribute("isverify", false);
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/AjaxPage")
	public String AjaxPage() {
		return "loginAjax";
	}
	
	@PostMapping("/loginAjax")
	@ResponseBody
	public Map<String, Object> LoginAjax(@ModelAttribute MemberLogin ml,Model model ) {
		Map<String, Object> response=new HashMap<>();		
		MemberLogin memberLogin=user.VerificationUser(ml.getAccount(), ml.getPassword());
		String error=null;
		if(memberLogin!=null) {
			model.addAttribute("command",memberLogin);
			response.put("code", 100);
			response.put("message", "success");
		}else {
			error=user.ErrorPassword(ml.getAccount());
			if(error==null){
				error = "帳號密碼出錯";
			}
			response.put("code",200);
			response.put("error", error);
		}		
		return response;
	}
	@GetMapping("/welcomeAjax")
	public String welcomeAjax(Model model) {	
		model.addAttribute("command",session.getAttribute("memberLogin"));
		return "welcomeAjax";
	}
}
