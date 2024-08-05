package com.example.demo.filter;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/welcomeAjax","/area","/welcome"})
public class LoginFilter extends OncePerRequestFilter {

	@Resource
	private HttpSession session;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getSession(false)!=null) {
			Boolean isVerify=(Boolean)session.getAttribute("isverify");
			if(isVerify!=null && isVerify) {
				filterChain.doFilter(request, response);
			}
		
		} 
		else {
			String requestUrl=request.getRequestURI();
			if("/demo/welcomeAjax".equals(requestUrl)) {
				response.sendRedirect("/demo/AjaxPage");
			}
			else {
				response.sendRedirect("/demo/loginPage");
			}			
		}

	}

}
