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
import com.sharebo.emp.web.entity.Pagers;
import com.sharebo.emp.web.entity.UpdateHistory;
import com.sharebo.emp.web.service.MemberService;
import com.sharebo.emp.web.service.UpdateHistoryService;
import com.sharebo.emp.web.utils.Result;

@Controller
@RequestMapping("/updateHistory")
public class UpdateHistoryController {
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	UpdateHistoryService updateHistoryService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/updateUpdateHistory",method=RequestMethod.GET)
	public String updateUpdateHistory(){
		return "updateUpdateHistory";
	}
	
	/**
	 * 修改升级记录
	 * @param updateHistory
	 * @return
	 */
	@RequestMapping(value="/updateUpdateHistory",method=RequestMethod.POST)
	@ResponseBody
	public Result updateUpdateHistory(UpdateHistory updateHistory){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(updateHistory == null){
			return new Result(303, "参数为空");
		}
		try {
			if(updateHistoryService.updateUpdateHistory(updateHistory) > 0){
				return new Result(200, "成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "失败");
	}
	
	/**
	 * 删除升级记录
	 * @param updateHistoryId
	 * @return
	 */
	@RequestMapping(value="/deleteUpdateHistory",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteUpdateHistory(String updateHistoryId){
		String number = (String) session.getAttribute("username");
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority == 0){
			return new Result(302,"无权限");
		}
		if(updateHistoryId == null){
			return new Result(303, "参数为空");
		}
		try {
			if(updateHistoryService.deleteUpdateHistory(updateHistoryId) > 0){
				return new Result(200, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(302, "异常错误");
		}
		return new Result(303, "删除失败");
	}
	
	/*@RequestMapping(value="/findUpdateHistoryByMember",method=RequestMethod.GET)
	public String findUpdateHistoryByMember(){
		return "/findUpdateHistoryByMember";
	}*/
	
	/**
	 * 根据员工工号或者是否在职查询
	 * @param pageIndex
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/findUpdateHistoryByMember",method=RequestMethod.GET)
	public String findUpdateHistoryByMember(Integer pageIndex,ModelMap map,HttpServletRequest req,Integer isOnJobF){
		if(pageIndex == null){
			pageIndex = 1;
		}
		int pageSize=8;
		String number = (String) session.getAttribute("username");
//		String number="1";
		int authority = memberService.findAuthorityByNumber(Long.parseLong(number));
		if(authority != 1){
			map.addAttribute("error", "权限不足");
			return "levelup";
		}
		
		//从请求参数中获取值
		Map<String, String[]> ma=req.getParameterMap();
		for (String str : ma.keySet()) {
			System.out.println(ma.get(str)[0]+"  key:"+str);
		}
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
		
		Pagers<UpdateHistory> pager=new Pagers<UpdateHistory>();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		int pageBegin=(pageIndex - 1) * pageSize;
		Long memberNumber = 0L;
		//判断memberNumber是否有这个值
		if(ma.get("memberNumber") != null && !ma.get("memberNumber")[0].equals("") && ma.get("memberNumber").length != 0){
				memberNumber = Long.valueOf(ma.get("memberNumber")[0].toString());
				List<UpdateHistory> uhs = updateHistoryService.findUpdateHistoryByMember(memberNumber,pageBegin, pageSize);
				pager.setList(uhs);
				//查询总数
				pager.setTotalRecords(updateHistoryService.findCountsByMemberUpdate(memberNumber));
			}
			//判断amp;memberNumber是否有这个值
			else if (ma.get("amp;memberNumber") != null && !ma.get("amp;memberNumber")[0].equals("") && ma.get("amp;memberNumber").length != 0) {
			memberNumber = Long.valueOf(ma.get("amp;memberNumber")[0].toString());
			List<UpdateHistory> uhs = updateHistoryService.findUpdateHistoryByMember(memberNumber,pageBegin, pageSize);
			pager.setList(uhs);
			//查询总数
			pager.setTotalRecords(updateHistoryService.findCountsByMemberUpdate(memberNumber));
		} else {
			List<UpdateHistory> uhs = updateHistoryService.findUpdateHistoryByIsOnJob(isOnJob, pageBegin, pageSize);
			pager.setList(uhs);
			pager.setTotalRecords(updateHistoryService.findCountsByJobUpdate(isOnJob));
		}
			pager.setTotalPages();//设置总页数
			map.addAttribute("pager", pager);
			if(memberNumber == 0){
				map.addAttribute("memberNumber", null);
			} else{
				map.addAttribute("memberNumber", memberNumber);
			}
			map.addAttribute("isOnJob",isOnJob);
			map.addAttribute("abc", new String[]{"-1","0","1"});
			return "levelup";
	}
	
	@RequestMapping(value="/findUpdateHistoryByIsOnJob",method=RequestMethod.GET)
	public String findUpdateHistoryByIsOnJob(){
		return "findUpdateHistoryByIsOnJob";
	}
	
	/**
	 * 根据是否在职显示升级记录
	 * -1:全部，0:离职，1：在职.
	 * @param isOnJob
	 * @return
	 */
	@RequestMapping(value="/findUpdateHistoryByIsOnJob",method=RequestMethod.POST)
	@ResponseBody
	public Result findUpdateHistoryByIsOnJob(Integer isOnJob, Integer pageIndex, Integer pageSize){
		if(isOnJob == null){
			return new Result(303, "参数为空");
		}
		
			Pagers<UpdateHistory> pager=new Pagers<UpdateHistory>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			int pageBegin=(pageIndex - 1) * pageSize;
			List<UpdateHistory> uhs = updateHistoryService.findUpdateHistoryByIsOnJob(isOnJob, pageBegin, pageSize);
			pager.setList(uhs);
			//查询总数
			pager.setTotalRecords(updateHistoryService.findCountsByJobUpdate(isOnJob));
			pager.setTotalPages();//设置总页数
			return new Result(200, "查询成功", pager);
	}
}
