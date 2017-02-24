package com.sharebo.emp.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import com.sharebo.emp.web.service.MemberService;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	public MemberService memberService;
	@Autowired
	public HttpSession session;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();  
	
	@Override  
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException, ServletException {  
        //获得授权后可得到用户信息 直接通过org.springframework.security.core.userdetails.User的用户名来接收信息
		User userDetails = (User)authentication.getPrincipal();  
		// session保存用户信息
		session.setAttribute("username", userDetails.getUsername());
		int authority = memberService.findAuthorityByNumber(Long.parseLong(userDetails.getUsername()));
		session.setAttribute("authority", authority);//0没
		//输出登录提示信息  
		redirectStrategy.sendRedirect(request,response,"/index");
	}
}
