package com.example.demo.filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.service.LoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter extends OncePerRequestFilter {

	@Autowired
	private LoginService user;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		String requestUrl=request.getRequestURI();
		String requestUrl=request.getServletPath();
		String contextPath=request.getContextPath();
		System.out.println(requestUrl);
		if(requestUrl.contains(".css")||requestUrl.contains(".js")||
				"/".equals(requestUrl)){
			filterChain.doFilter(request, response);
			return;
		}			
		//登入
		System.out.println("filter");
		Boolean isLogin=(Boolean) session.getAttribute("isLogin");
		if (isLogin != null && isLogin) {		
			if("/loginPage".equals(requestUrl)) {	
				response.sendRedirect(contextPath+"/welcome");
			}
			else if("/loginAjaxPage".equals(requestUrl)) {	
				response.sendRedirect(contextPath+"/welcomeAjax");
			}
			filterChain.doFilter(request, response);					
		} 
		//未登入
		else {
			if(requestUrl.equals("/login")) {
				String account = request.getParameter("account");
	            String password = request.getParameter("password");
				request.setAttribute("accountData",user.login(account,password, request));
			}
			if(requestUrl.equals("/loginAjax")) {
				String account = request.getParameter("account");
	            String password = request.getParameter("password");
				request.setAttribute("accountData",user.loginAjax(account,password,request));
			}
			if(requestUrl.contains("login")) {
				filterChain.doFilter(request, response);
			}
			else {
				response.sendRedirect(contextPath+"/loginPage");
			}				
		}
	}
}
