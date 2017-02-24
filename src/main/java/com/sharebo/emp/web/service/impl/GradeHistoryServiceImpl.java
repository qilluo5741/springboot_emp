package com.sharebo.emp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.emp.web.entity.GradeHistory;
import com.sharebo.emp.web.mapper.GradeHistoryMapper;
import com.sharebo.emp.web.service.GradeHistoryService;
@Service
public class GradeHistoryServiceImpl implements GradeHistoryService {
	@Autowired
	GradeHistoryMapper gradeHistoryMapper;
	@Override
	public int addGradeHistory(GradeHistory gradeHistory) {
		return gradeHistoryMapper.addGradeHistory(gradeHistory);
	}

	@Override
	public int updateGradeHistory(GradeHistory gradeHistory) {
		return gradeHistoryMapper.updateGradeHistory(gradeHistory);
	}

	@Override
	public int deleteGradeHistory(String GradeHistoryId) {
		return gradeHistoryMapper.deleteGradeHistory(GradeHistoryId);
	}

	@Override
	public List<GradeHistory> findGradeHistoryByMember(Long memberNumber, Integer pageBegin, Integer pageSize) {
		return gradeHistoryMapper.findGradeHistoryByMember(memberNumber,pageBegin,pageSize);
	}

	@Override
	public List<GradeHistory> findGradeHistoryByIsOnJob(Integer isOnJob, Integer pageBegin, Integer pageSize) {
		return gradeHistoryMapper.findGradeHistoryByIsOnJob(isOnJob,pageBegin,pageSize);
	}

	@Override
	public Integer findCountsByMemberGrade(Long memberNumber) {
		return gradeHistoryMapper.findCountsByMemberGrade(memberNumber);
	}

	@Override
	public Integer findCountsByJobGrade(Integer isOnJob) {
		return gradeHistoryMapper.findCountsByJobGrade(isOnJob);
	}

}
