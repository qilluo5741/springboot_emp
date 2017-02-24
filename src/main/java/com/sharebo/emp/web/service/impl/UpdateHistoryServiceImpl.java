package com.sharebo.emp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.emp.web.entity.UpdateHistory;
import com.sharebo.emp.web.entityDto.LevelUpDto;
import com.sharebo.emp.web.mapper.UpdateHistoryMapper;
import com.sharebo.emp.web.service.MemberService;
import com.sharebo.emp.web.service.UpdateHistoryService;

@Service
public class UpdateHistoryServiceImpl implements UpdateHistoryService {

	@Autowired
	MemberService memberService;
	@Autowired
	UpdateHistoryMapper updateHistoryMapper;
	@Override
	public int addUpdateHistory(List<LevelUpDto> lud) {
		if(lud.size() > 0){
			return updateHistoryMapper.addUpdateHistory(lud);
		} else{
			return 0;
		}
	}

	@Override
	public int deleteUpdateHistory(String updateHistoryId) {
		return updateHistoryMapper.deleteUpdateHistory(updateHistoryId);
	}

	@Override
	public int updateUpdateHistory(UpdateHistory updateHistory) {
		return updateHistoryMapper.updateUpdateHistory(updateHistory);
	}

	@Override
	public List<UpdateHistory> findUpdateHistoryByMember(Long memberNumber, Integer pageBegin, Integer pageSize) {
		return updateHistoryMapper.findUpdateHistoryByMember(memberNumber, pageBegin, pageSize);
	}

	@Override
	public List<UpdateHistory> findUpdateHistoryByIsOnJob(Integer isOnJob, Integer pageBegin, Integer pageSize) {
		return updateHistoryMapper.findUpdateHistoryByIsOnJob(isOnJob, pageBegin, pageSize);
	}

	@Override
	public void updateStep() {
		// 需要记录升级记录。
		List<LevelUpDto> luds = memberService.findMemberAndMedal();
//		if(luds.size() > 0){
			addUpdateHistory(luds);
//		}
		// 再进行调分的检测
		memberService.updateMedal();
	}
	
	@Override
	public Integer findCountsByMemberUpdate(Long memberNumber) {
		return updateHistoryMapper.findCountsByMemberUpdate(memberNumber);
	}

	@Override
	public Integer findCountsByJobUpdate(Integer isOnJob) {
		return updateHistoryMapper.findCountsByJobUpdate(isOnJob);
	}
}
