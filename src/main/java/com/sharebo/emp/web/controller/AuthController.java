package com.sharebo.emp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharebo.emp.web.service.MemberService;

/**
 * 权限验证
 * @author niewei
 */
@Controller
public class AuthController {
	@Autowired
	public MemberService memberService;
	@Autowired
	public HttpSession session;
	/**
	 * 跳转
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String tologin(ModelMap map){
		return "login";
	}
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(ModelMap map){
		String number = (String) session.getAttribute("username");
			map.addAttribute("number", number);
		return "index";
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String editpassword(ModelMap map){
		return "edit";
	}
	@RequestMapping(value="/mation",method=RequestMethod.GET)
	public String mation(ModelMap map,HttpServletRequest req){
		map.addAllAttributes(req.getParameterMap());
		return "mation";
	}
	@RequestMapping(value="/adjust",method=RequestMethod.GET)
	public String Adjust(ModelMap map){
		return "adjust";
	}
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String error(ModelMap map){
		return "error";
	}
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(ModelMap map){
		return "home";
	}
	
	/**
	 * 新增员工信息页面
	 * @return
	 */
	@RequestMapping(value="/addMember",method=RequestMethod.GET)
	public String addMember(){
		return "addMember";
	}
}
