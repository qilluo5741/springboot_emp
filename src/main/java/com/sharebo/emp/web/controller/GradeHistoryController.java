package com.sharebo.emp.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.emp.web.entity.GradeHistory;
import com.sharebo.emp.web.entity.Pagers;
import com.sharebo.emp.web.service.GradeHistoryService;
import com.sharebo.emp.web.service.MemberService;
import com.sharebo.emp.web.utils.Result;
/**
 * 302异常，303失败。200成功
 * @author zhuhaiyuan
 */
@Controller
@RequestMapping("/gradeHistory")
public class GradeHistoryController {
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	GradeHistoryService gradeHistoryService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/updateGradeHistory",method=RequestMethod.GET)
	public String updateGradeHistory(){
		return "/updateGradeHistory";
	}
	
	/**
	 * 修改调分记录
	 * @param gradeHistory
	 * @return
	 */
	@RequestMapping(value="/updateGradeHistory",method=RequestMethod.POST)
	@ResponseBody
	public Result updateGradeHistory(GradeHistory gradeHistory){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(gradeHistory == null){
			return new Result(303, "参数为空");
		}
		try {
			if(gradeHistoryService.updateGradeHistory(gradeHistory) > 0){
				return new Result(200, "成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "失败");
	}
	
	/**
	 * 删除调分记录
	 * 根据该记录ID
	 * @param gradeHistoryId
	 * @return
	 */
	@RequestMapping(value="/deleteGradeHistory",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteGradeHistory(String gradeHistoryId){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(gradeHistoryId == null){
			return new Result(303, "参数为空");
		}
		try {
			if(gradeHistoryService.deleteGradeHistory(gradeHistoryId) > 0){
				return new Result(200, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "删除失败");
	}
	/*	
	@RequestMapping(value="/findGradeHistoryByMember",method=RequestMethod.GET)
	public String findGradeHistoryByMember(){
		return "/findGradeHistoryByMember";
	}*/
	/**
	 * 根据员工号查找调分记录
	 * @param memberNumber
	 * @return
	 */
	@RequestMapping(value="/findGradeHistory",method=RequestMethod.GET)
	public String findGradeHistory(Integer pageIndex,ModelMap map,HttpServletRequest req,Integer isOnJobF){
		
		if(pageIndex == null){
			pageIndex = 1;
		}
		int pageSize=8;
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority != 1){
			map.addAttribute("error", "权限不足");
			return "grade";
		}
		
		//从请求参数中获取值
		Map<String, String[]> ma=req.getParameterMap();
//		for (String str : ma.keySet()) {
//			System.out.println(ma.get(str)[0]+"  key:"+str);
//		}
		Integer isOnJob=null;
		if(isOnJobF != null){
			isOnJob = isOnJobF;
			
		} else{
			String isOnJobs[] =ma.get("amp;isOnJob");
			
			if (isOnJobs != null && isOnJobs.length != 0) {
				isOnJob = Integer.valueOf(ma.get("amp;isOnJob")[0].toString());
			} else {
				isOnJob = -1;
			}
		}
		
		Pagers<GradeHistory> pager=new Pagers<GradeHistory>();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		int pageBegin=(pageIndex - 1) * pageSize;
		Long memberNumber = 0L;
		//判断memberNumber是否有这个值
		if(ma.get("memberNumber") != null && !ma.get("memberNumber")[0].equals("") && ma.get("memberNumber").length != 0){
			memberNumber = Long.valueOf(ma.get("memberNumber")[0].toString());
			List<GradeHistory> ghs = gradeHistoryService.findGradeHistoryByMember(memberNumber,pageBegin,pageSize);
			pager.setList(ghs);
			//查询总数
			pager.setTotalRecords(gradeHistoryService.findCountsByMemberGrade(memberNumber));
		}
		//判断amp;memberNumber是否有这个值
		else if (ma.get("amp;memberNumber") != null && !ma.get("amp;memberNumber")[0].equals("") && ma.get("amp;memberNumber").length != 0) {
			memberNumber = Long.valueOf(ma.get("amp;memberNumber")[0].toString());
			List<GradeHistory> ghs = gradeHistoryService.findGradeHistoryByMember(memberNumber,pageBegin,pageSize);
			pager.setList(ghs);
			//查询总数
			pager.setTotalRecords(gradeHistoryService.findCountsByMemberGrade(memberNumber));
		} else {
			List<GradeHistory> ghs = gradeHistoryService.findGradeHistoryByIsOnJob(isOnJob,pageBegin,pageSize);
			pager.setList(ghs);
			pager.setTotalRecords(gradeHistoryService.findCountsByJobGrade(isOnJob));
		}
			pager.setTotalPages();//设置总页数
			map.addAttribute("pager", pager);
			if(memberNumber == 0){
				map.addAttribute("memberNumber", null);
			} else{
				map.addAttribute("memberNumber", memberNumber);
			}
			map.addAttribute("authority",authority);
			map.addAttribute("isOnJob",isOnJob);
			map.addAttribute("abc", new String[]{"-1","0","1"});
			return "grade";
	}
	@RequestMapping(value="/findGradeHistoryByIsOnJob",method=RequestMethod.GET)
	public String findGradeHistoryByIsOnJob(){
		return "/findGradeHistoryByIsOnJob";
	}
	
	/**
	 * 根据是否在职查询
	 * -1:全部，0:离职，1：在职.
	 * @param isOnJob
	 * @return
	 */
	@RequestMapping(value="/findGradeHistoryByIsOnJob",method=RequestMethod.POST)
	@ResponseBody
	public Result findGradeHistoryByIsOnJob(Integer isOnJob, Integer pageIndex, Integer pageSize){
			if(isOnJob == null){
				return new Result(303, "参数为空");
			}
			Pagers<GradeHistory> pager=new Pagers<GradeHistory>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			int pageBegin=(pageIndex - 1) * pageSize;
			List<GradeHistory> ghs = gradeHistoryService.findGradeHistoryByIsOnJob(isOnJob,pageBegin,pageSize);
			pager.setList(ghs);
			//查询总数
			pager.setTotalRecords(gradeHistoryService.findCountsByJobGrade(isOnJob));
			pager.setTotalPages();//设置总页数
			return new Result(200, "查询成功", pager);
	}
}
