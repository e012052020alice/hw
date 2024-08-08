package com.example.demo.filter;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestUrl=request.getRequestURI().split(";")[0];
		System.out.println(requestUrl);
		if(requestUrl.contains("css")||requestUrl.contains("js")||
				"/demo/".equals(requestUrl)){
			filterChain.doFilter(request, response);
			return;
		}			
		//登入
		System.out.println("filter");
		if (request.getSession(false)!=null) {		
			System.out.println("logined");
//			Boolean isVerify=(Boolean)request.getSession().getAttribute("isverify");
			if("/demo/loginPage".equals(requestUrl)) {	
				response.sendRedirect("/demo/welcome");
			}
			else if("/demo/loginAjaxPage".equals(requestUrl)) {	
				response.sendRedirect("/demo/welcomeAjax");
			}
			filterChain.doFilter(request, response);					
		} 
		//未登入
		else {
			System.out.println("not yet");
			if(requestUrl.contains("login")) {
				filterChain.doFilter(request, response);
			}
			else {
				response.sendRedirect("/demo/loginPage");
			}			
		}
	}
}
