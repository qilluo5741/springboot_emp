package com.sharebo.emp.web.service;

import java.util.List;

import com.sharebo.emp.web.entity.UpdateHistory;
import com.sharebo.emp.web.entityDto.LevelUpDto;

/**
 * 升级记录
 * 所有是返回List的都要做分页处理。
 * @author zhuhaiyuan
 *
 */
public interface UpdateHistoryService {

	/**
	 * 添加升级记录
	 * @param updateHistory
	 * @return
	 */
	public int addUpdateHistory(List<LevelUpDto> lud);
	
	/**
	 * 删除升级记录
	 * @param updateHistoryId
	 * @return
	 */
	public int deleteUpdateHistory(String updateHistoryId);
	
	/**
	 * 修改升级记录
	 * @param updateHistory
	 * @return
	 */
	public int updateUpdateHistory(UpdateHistory updateHistory);
	
	/**
	 * 根据员工号查找升级记录
	 * @param memberNumber
	 * @return
	 */
	public List<UpdateHistory> findUpdateHistoryByMember(Long memberNumber, Integer pageBegin, Integer pageSize);
	
	/**
	 * 根据员工是否离职查询历史记录
	 * @param isInJob.  -1：全部,0:离职，1：在职
	 * @return
	 */
	public List<UpdateHistory> findUpdateHistoryByIsOnJob(Integer isOnJob, Integer pageBegin, Integer pageSize);
	
	/**
	 * 用于检测分数够了且要
	 */
	public void updateStep();
	
	/**
	 * 根据工号查询升级记录
	 * @param memberNumber
	 * @param pageBegin
	 * @param pageSize
	 * @return
	 */
	public Integer findCountsByMemberUpdate(Long memberNumber);
	
	/**
	 * 
	 * @param isOnJob
	 * @return
	 */
	public Integer findCountsByJobUpdate(Integer isOnJob);
}
