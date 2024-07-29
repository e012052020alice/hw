package com.example.demo.controller;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.MemberLogin;
import com.example.demo.model.MemberLoginRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	@Autowired
	private MemberLoginRepository memberLoginRepository;
	@Resource
	private HttpServletRequest request;
	
	@PostMapping("/login")
	public String Login(@ModelAttribute MemberLogin ml,Model model ) {
		System.out.println(ml.getAccount());
		LinkedList<String> errors=new LinkedList<>();
		request.setAttribute("errors", errors);
		Optional<MemberLogin> result=memberLoginRepository.findByAccountAndPassword(ml.getAccount(), ml.getPassword());
		MemberLogin memberLogin;		
		if(result.isPresent()) {
			memberLogin=result.get();
			model.addAttribute("command",memberLogin);
			return "welcome";
		}
		result=memberLoginRepository.findByAccount(ml.getAccount());
		if(result.isPresent()) {			
			errors.add("密碼錯誤");
		}
		else if(!ml.getAccount().isEmpty()){
			errors.add("帳號密碼出錯");				
		}		
		return "fail";
	}

}
