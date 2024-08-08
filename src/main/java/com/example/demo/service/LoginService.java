package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.MemberLogin;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class LoginService {

	@Autowired
	private VerifyService user;
//	@Resource
//	private HttpServletRequest request;
	
	public String login(String account,String password, HttpServletRequest request) {
		MemberLogin memberLogin=user.verificationUser(account, password);		
		String error=null;
		if(memberLogin!=null) {
//			model.addAttribute("command", memberLogin);
			request.getSession().setAttribute("Data", memberLogin);			
			return "welcome";
		}
		error=user.errorMessage(account);
		request.setAttribute("account", account);
		request.setAttribute("error", error);	
		return "login";		
	}
	
	@ResponseBody
	public Map<String, Object> loginAjax(String account,String password,HttpServletRequest request ) {
		Map<String, Object> response=new HashMap<>();		
		MemberLogin memberLogin=user.verificationUser(account, password);
		String error=null;
		if(memberLogin!=null) {
			request.getSession().setAttribute("Data", memberLogin);
			response.put("code", 100);
		}else {
			error=user.errorMessage(account);
			System.out.println(error);
			response.put("code",200);
			response.put("error", error);
		}		
		return response;
	}	
}
