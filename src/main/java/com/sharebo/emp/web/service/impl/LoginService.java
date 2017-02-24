package com.sharebo.emp.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sharebo.emp.web.service.MemberService;
import com.sharebo.emp.web.utils.EncrypAES;

@Service
public class LoginService implements UserDetailsService {
	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("---------欢迎"+username+"登录---------");
		//根据用户名查找密码
		memberService.findpwdByMemberNumber(Long.parseLong(username));
		String testPwd = EncrypAES.decryptToString(memberService.findpwdByMemberNumber(Long.parseLong(username)));
		// 设置权限（或者角色）
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		getRoles(list);
		// 把账号密码放置user中
		org.springframework.security.core.userdetails.User auth_user 
		= new org.springframework.security.core.userdetails.User(
				username, testPwd, list);// 返回包括权限角色的User给security
		return auth_user;
	}

	/*
	 * 获取所属角色
	 * 
	 * @param user
	 * 
	 * @param list
	 */
	public void getRoles(List<GrantedAuthority> list) {
		for (String role : "USER,ADMIN,".split(",")) {
			list.add(new SimpleGrantedAuthority("ROLE_" + role));
			// 权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
		}
	}

}
