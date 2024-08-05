package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.MemberLogin;
import com.example.demo.model.MemberLoginRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	
	@Autowired
	private MemberLoginRepository memberLoginRepository;
	@Resource
	private HttpSession session;
	
	public MemberLogin VerificationUser(String userAccount,String userPassword) {
		Optional<MemberLogin> result=memberLoginRepository.findByAccountAndPassword(userAccount, userPassword);		
		if(result.isPresent()) {
			MemberLogin memberLogin;
			memberLogin=result.get();
			session.setAttribute("memberLogin", memberLogin);
			session.setAttribute("isverify", true);
			return memberLogin;
		}		
		return null;
	}
	public String ErrorPassword(String userAccount) {
		Optional<MemberLogin> result=memberLoginRepository.findByAccount(userAccount);		
		if(result.isPresent()) {
			return "密碼錯誤";
		}		
		return null;
	}
	
}
