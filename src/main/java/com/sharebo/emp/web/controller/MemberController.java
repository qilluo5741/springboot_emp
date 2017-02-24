package com.sharebo.emp.web.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.emp.web.entity.GradeHistory;
import com.sharebo.emp.web.entity.Member;
import com.sharebo.emp.web.entity.Menu;
import com.sharebo.emp.web.entity.Pagers;
import com.sharebo.emp.web.service.GradeHistoryService;
import com.sharebo.emp.web.service.MemberService;
import com.sharebo.emp.web.service.MenuService;
import com.sharebo.emp.web.utils.EncrypAES;
import com.sharebo.emp.web.utils.Result;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	public MemberService memberService;
	@Autowired
	public GradeHistoryService gradeHistoryService;
	
	@Autowired
	public MenuService menuService;
	
	@Autowired
	public HttpSession session;
	
	/**
	 * 新增员工信息
	 * @param member
	 * @return
	 */
	@RequestMapping(value="/addMember",method=RequestMethod.POST)
	@ResponseBody
	public Result addMember(Member member){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(member == null){
			return new Result(303, "参数为空");
		}
		try {
			if(memberService.addMember(member) > 0){
				return new Result(200, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "新增失败");
	}
	
	
	/**
	 * 管理员修改员工信息,包括修改员工在职情况
	 * @param member
	 * @return
	 */
	@RequestMapping(value="/updateMember",method=RequestMethod.POST)
	@ResponseBody
	public Result updateMember(Member member){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(member == null){
			return new Result(303, "参数为空");
		}
		try {
			if(memberService.updateMember(member) > 0){
				return new Result(200, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "修改失败");
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.GET)
	public String updatePassword(){
		return "/updatePassword";
	}
	
	/**
	 * 员工根据原密码，修改密码
	 * @param number
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	@ResponseBody
	public Result updatePassword(String oldPwd, String newPwd){
		String numberString = (String) session.getAttribute("username");
		Long number = Long.parseLong(numberString);
		if(oldPwd == null || newPwd == null){
			return new Result(303, "参数为空");
		}
		try {
			int updateResult = memberService.updatePassword(number, oldPwd, newPwd);
			if(updateResult > 0){
				return new Result(200, "密码修改成功");
			} else if(updateResult == -1){
				return new Result(304, "原密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "密码修改失败");
	}
	
/*	@RequestMapping(value="/findMembers",method=RequestMethod.GET)
	public String findMembers(){
		return "/findMembers";
	}*/
	
	/**
	 * 查询员工信息，可根据是否在职与勋章数
	 * @param isOnJob
	 * @param medal
	 * @return
	 */
	@RequestMapping(value="/findMembers",method=RequestMethod.GET)
	public String findMembers(Integer pageIndex,ModelMap map,HttpServletRequest req,Integer isOnJobF){
		if(pageIndex == null){
			pageIndex = 1;
		}
		Integer pageSize=8;
		
		//从请求参数中获取值
		Map<String, String[]> ma=req.getParameterMap();
		for (String str : ma.keySet()) {
			System.out.println(ma.get(str)[0]+"  key:"+str);
		}
		//是否在职
		Integer isOnJob=null;
		String isOnJobs[] =ma.get("amp;isOnJob");
		if (isOnJobs != null && isOnJobs.length != 0) {
			isOnJob = Integer.valueOf(ma.get("amp;isOnJob")[0].toString());
		} else {
			isOnJob = -1;
		}
		// 勋章值
		Integer medal=null;
		String medals[] =ma.get("amp;medal");
		if (medals != null && medals.length != 0) {
			medal = Integer.valueOf(ma.get("amp;medal")[0].toString());
		} else {
			medal = -1;
		}
		if(medal == null){
			medal = -1;
		}
		if(pageIndex==null || pageSize==null || pageIndex==0){
			return "growup";
		}
		//查询当前用户权限
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		//分页展示
		Pagers<Member> pager=new Pagers<Member>();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		int pageBegin = (pageIndex - 1) * pageSize;
		List<Member> members = memberService.findMembers(isOnJob, medal, pageBegin, pageSize);
		if(authority == 1){
			for (Member member : members) {
				if(member != null && member.getPassword() != null && member.getPassword().length() > 0){
					member.setPassword(EncrypAES.decryptToString(member.getPassword()));
				}
			}
		}
		pager.setList(members);
		//查询总数
		pager.setTotalRecords(memberService.findCountsByMembers(isOnJob, medal));
		pager.setTotalPages();//设置总页数
		if(medal == 0){
			map.addAttribute("medal", null);
		} else{
			map.addAttribute("medal", medal);
		}
		map.addAttribute("authority", authority);
		map.addAttribute("pager", pager);
		map.addAttribute("isOnJob",isOnJob);
		map.addAttribute("abc", new String[]{"-1","0","1"});
		return "growup";
	}
	
	/**
	 * 根据姓名查找员工信息
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/findMemberByName",method=RequestMethod.POST)
	@ResponseBody
	public Result findMemberByName(String name){
		if(name == null){
			return new Result(303, "参数为空");
		}
		List<Member> members = memberService.findMemberByName(name);
		for (Member member : members) {
			if(member != null && member.getPassword().length() > 0){
				member.setPassword(EncrypAES.decryptToString(member.getPassword()));
			}
		}
		if(members.size() > 0){
			return new Result(200, "查询成功",members);
		}
		return new Result(303, "查询失败","无数据");
	}
	
	/**
	 * 根据工号查找员工信息
	 * @param number
	 * @return
	 */
	@RequestMapping(value="/findMemberByNumber",method=RequestMethod.POST)
	@ResponseBody
	public Result findMemberByNumber(Long number){
		if(number == null){
			return new Result(303, "参数为空");
		}
		Member member = memberService.findMemberByNumber(number);
		if(member != null && member.getPassword().length() > 0){
			member.setPassword(EncrypAES.decryptToString(member.getPassword()));
		}
		return new Result(200, "查询成功", member);
	}
	
	/**
	 * 为员工加调分项页面
	 * @return
	 */
	@RequestMapping(value="/addGrade",method=RequestMethod.GET)
	public String addGrade(){
		return "/addGrade";
	}
	
	/**
	 * 为员工加调分项
	 * 奉献、加班、调休、创新等
	 * @param gh
	 * @param number
	 * @return
	 */
	@RequestMapping(value="/addGrade",method=RequestMethod.POST)
	@ResponseBody
	public Result addGrade(GradeHistory gh){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(gh == null || gh.getMembernumber() == null){
			return new Result(303, "参数为空");
		}
		// 先更新员工信息表里的分数。里会自动根据是否达到365分进行勋章升级
		memberService.updateGradeInMember(gh.getGrade(),gh.getMembernumber());
		// 然后添加一条调分记录
		gh.setId(UUID.randomUUID().toString());
		if(gradeHistoryService.addGradeHistory(gh) > 0){
			return new Result(200, "新增成功");
		}
	return new Result(303, "新增失败");
	}
	
	@RequestMapping(value="/getMenu",method=RequestMethod.GET)
	@ResponseBody
	public Result getMenu(){
		List<Menu> menus= memberService.checkMenuAuthority(session);
		if(menus.size() > 0){
			return new Result(200, "查询成功",menus);
		}
		return new Result(302, "查询失败");
	}
	
	@RequestMapping(value="/addMenu",method=RequestMethod.POST)
	@ResponseBody
	public Result addMenu(Menu menu){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(menu == null || menu.getMenuname() == null){
			return new Result(303, "参数为空");
		}
		if(menu != null && menuService.addMenu(menu) > 0){
			return new Result(200, "新增菜单成功");
		}
		return new Result(302, "新增菜单失败");
	}
}
