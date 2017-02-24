package com.sharebo.emp.web.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.emp.web.entity.Member;
import com.sharebo.emp.web.entity.Menu;
import com.sharebo.emp.web.entityDto.LevelUpDto;
import com.sharebo.emp.web.mapper.MemberMapper;
import com.sharebo.emp.web.service.MemberService;
import com.sharebo.emp.web.service.UpdateHistoryService;
import com.sharebo.emp.web.utils.EncrypAES;
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	UpdateHistoryService updateHistoryService;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	HttpSession session;
	
	@Override
	public int addMember(Member member) throws Exception{
		// 密码加密
		member.setPassword(EncrypAES.encryptToString(member.getPassword()));
		return memberMapper.addMember(member);
	}

	@Override
	public int updateMember(Member member) {
		member.setPassword(EncrypAES.encryptToString(member.getPassword()));
		return memberMapper.updateMember(member);
	}
	@Override
	public int updateGradeInMember(int grade, Long memberNumber) {
		// 先进行分数的记录
		int result = 0;
		if(memberMapper.updateGradeInMember(grade, memberNumber) > 0){
			result = 1;
		}
		updateHistoryService.updateStep();
		return result;
	}

	@Override
	public List<Member> findAllMembers() {
		return memberMapper.findAllMembers();
	}

	@Override
	public List<Member> findMembers(Integer isOnJob, Integer medal, Integer pageBegin, Integer pageSize) {
		return memberMapper.findMembers(isOnJob, medal, pageBegin, pageSize);
	}

	@Override
	public List<Member> findMemberByName(String name) {
		// 先获取员工信息
		List<Member> ms = memberMapper.findMemberByName(name);
		try {
			// 解密展示
			for (Member member : ms) {
				member.setPassword(EncrypAES.decryptToString(member.getPassword()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ms;
	}

	@Override
	public Member findMemberByNumber(Long memberNumber) {
		return memberMapper.findMemberByNumber(memberNumber);
	}

	@Override
	public int updateMedal() {
		return memberMapper.updateMedal();
	}

	@Override
	public int updateMedalByNumber(Long memberNumber) {
		return memberMapper.updateMedalByNumber(memberNumber);
	}

	@Override
	public int updateNowGrade() {
		return memberMapper.updateNowGrade();
	}

	@Override
	public int updateDailyGrade() {
		return memberMapper.updateDailyGrade();
	}

	@Override
	public int updatePassword(Long number, String oldPwd, String newPwd) {
		// 先校验原密码是否正确
		String nowPwd = findpwdByMemberNumber(number);
		if(!oldPwd.equals(EncrypAES.decryptToString(nowPwd))){
			// 原密码错误
			return -1;
		}
		// 给新密码加密
		newPwd = EncrypAES.encryptToString(newPwd);
		return memberMapper.updatePassword(number, newPwd);
	}

	@Override
	public String findpwdByMemberNumber(Long number) {
		return memberMapper.findpwdByMemberNumber(number);
	}

	@Override
	public List<LevelUpDto> findMemberAndMedal() {
		return memberMapper.findMemberAndMedal();
	}

	@Override
	public List<String> getMenuByMember() {
		return null;
	}

	@Override
	public List<Menu> checkMenuAuthority(HttpSession session) {
		Long number = Long.parseLong((String) session.getAttribute("username"));
		int authority = findAuthorityByNumber(number);
		return memberMapper.checkMenuAuthority(authority);
	}

	@Override
	public Integer findAuthorityByNumber(Long number) {
		return memberMapper.findAuthorityByNumber(number);
	}

	@Override
	public Integer findCountsByMembers(Integer isOnJob, Integer medal) {
		return memberMapper.findCountsByMembers(isOnJob, medal);
	}
}
