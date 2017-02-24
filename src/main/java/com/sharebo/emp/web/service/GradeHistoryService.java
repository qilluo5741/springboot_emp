package com.sharebo.emp.web.service;

import java.util.List;

import com.sharebo.emp.web.entity.GradeHistory;

/**
 * 分数调整方法接口
 * 所有是返回List的都要做分页处理。
 * @author zhuhaiyuan
 *
 */
public interface GradeHistoryService {

	/**
	 * 新增分数调整记录
	 * @param gradeHistory
	 * @return
	 */
	public int addGradeHistory(GradeHistory gradeHistory);
	
	/**
	 * 修改分数调整记录
	 * @param gradeHistory
	 * @return
	 */
	public int updateGradeHistory(GradeHistory gradeHistory);
	
	/**
	 * 删除分数调整记录
	 * @param GradeHistoryId
	 * @return
	 */
	public int deleteGradeHistory(String GradeHistoryId);
	
	/**
	 * 根据员工号查询分数调整记录
	 * @param memberNumber
	 * @return
	 */
	public List<GradeHistory> findGradeHistoryByMember(Long memberNumber, Integer pageBegin, Integer pageSize);
	
	/**
	 * 根据是否在职查询分数调整记录
	 * @param isInJob  -1:全部，0:离职，1：在职.
	 * @return
	 */
	public List<GradeHistory> findGradeHistoryByIsOnJob(Integer isOnJob, Integer pageBegin, Integer pageSize);
	
	public Integer findCountsByMemberGrade(Long memberNumber);
	
	public Integer findCountsByJobGrade(Integer isOnJob);
}
