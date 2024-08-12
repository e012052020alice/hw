package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.MemberLogin;
import com.example.demo.model.MemberLoginRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class VerifyService {
	
	@Autowired
	private MemberLoginRepository memberLoginRepository;
	
	public MemberLogin verificationUser(String userAccount,String userPassword,HttpSession session) {
		Optional<MemberLogin> result=memberLoginRepository.findByAccountAndPassword(userAccount, userPassword);		
		if(result.isPresent()) {
			MemberLogin memberLogin=result.get();
			session.setAttribute("memberLogin", memberLogin);
			session.setAttribute("isLogin", true);
			return memberLogin;
		}		
		return null;
	}
	public String errorMessage(String userAccount) {
		Optional<MemberLogin> result=memberLoginRepository.findByAccount(userAccount);		
		if(result.isPresent()) {
			return "密碼錯誤";
		}
		else {
			return "無此帳號";
		}
		
	}
	
}
