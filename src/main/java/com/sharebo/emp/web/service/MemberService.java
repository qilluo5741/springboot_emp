package com.sharebo.emp.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sharebo.emp.web.entity.Member;
import com.sharebo.emp.web.entity.Menu;
import com.sharebo.emp.web.entityDto.LevelUpDto;

/**
 * 员工方法接口
 * @author zhuhaiyuan
 *
 */
public interface MemberService {
	/**
	 * 新增员工信息
	 * @param member
	 * @return
	 */
	public int addMember(Member member) throws Exception;
	
	/**
	 * 修改员工信息
	 * @param member
	 * @return
	 */
	public int updateMember(Member member);
	
	/**
	 * 调分记录在员工信息中
	 * @param grade
	 * @param memberNumber
	 * @return
	 */
	public int updateGradeInMember(int grade, Long memberNumber);
	
	/**
	 * 查看所有在记录的员工的勋章、分数信息
	 * @return
	 */
	public List<Member> findAllMembers();
	
	/**
	 * 根据是否在职，勋章数查找员工信息
	 * @param isInJob
	 * @param medal
	 * @return
	 */
	public List<Member> findMembers(Integer isOnJob, Integer medal, Integer pageBegin, Integer pageSize);
	
	/**
	 * 根据姓名查找员工
	 * @param name
	 * @return
	 */
	public List<Member> findMemberByName(String name);
	
	/**
	 * 根据工号查找员工
	 * @param memberNumber
	 * @return
	 */
	public Member findMemberByNumber(Long memberNumber);
	
	/**
	 * 检测全体分数满365分更新勋章
	 * @return
	 */
	public int updateMedal();
	
	/**
	 * 根据员工号进行检测365分更新勋章
	 * @param memberNumber
	 * @return
	 */
	public int updateMedalByNumber(Long memberNumber);
	
	/**
	 * 年度清空当年分数
	 * @return
	 */
	public int updateNowGrade();
	
	/**
	 * 日常更新日常分数，每个员工+1
	 * @return
	 */
	public int updateDailyGrade();
	
	/**
	 * 员工修改密码
	 * @param number
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public int updatePassword(Long number, String oldPwd, String newPwd);
	
	/**
	 * 根据用户名查找密码
	 * @param name
	 * @return
	 */
	public String findpwdByMemberNumber(Long number);
	
	public List<LevelUpDto> findMemberAndMedal();
	
	public List<String>	getMenuByMember();
	
	public List<Menu> checkMenuAuthority(HttpSession session);
	
	public Integer findAuthorityByNumber(Long number);
	
	public Integer findCountsByMembers(Integer isOnJob, Integer medal);
}
