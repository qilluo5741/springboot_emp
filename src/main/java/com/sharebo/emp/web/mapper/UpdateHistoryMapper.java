package com.sharebo.emp.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.sharebo.emp.web.entity.UpdateHistory;
import com.sharebo.emp.web.entityDto.LevelUpDto;
import com.sharebo.emp.web.sqlbuilder.MemberSqlBuilder;

@Mapper
public interface UpdateHistoryMapper {
	/**
	 * 批量插入
	 * @param lud
	 * @return
	 */
	@InsertProvider(method = "addUpdateHistory", type = MemberSqlBuilder.class)
	public int addUpdateHistory(@Param("luds")List<LevelUpDto> luds);
	
	/**
	 * 删除升级记录
	 * @param updateHistoryId
	 * @return
	 */
	@Delete("delete from updatehistory where id = #{updateHistoryId}")
	public int deleteUpdateHistory(@Param("updateHistoryId")String updateHistoryId);
	
	/**
	 * 更新升级记录
	 * @param updateHistory
	 * @return
	 */
	@Update("update updatehistory set time = now(),"
			+ "membernumber = #{membernumber},updatemedal = #{updatemedal}"
			+ "where id=#{id}")
	public int updateUpdateHistory(UpdateHistory updateHistory);
	
	/**
	 * 根据在职查询升级记录
	 * @param isOnJob
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(method = "findUpdateHistoryByIsOnJob", type = MemberSqlBuilder.class)
	public List<UpdateHistory> findUpdateHistoryByIsOnJob(@Param("isonjob")Integer isOnJob, 
			@Param("pageBegin")Integer pageBegin, @Param("pageSize")Integer pageSize);
	
	/**
	 * 根据工号查升级记录
	 * @param memberNumber
	 * @return
	 */
	@Select("select * from updatehistory where membernumber = #{memberNumber} order by time desc limit #{pageBegin},#{pageSize}")
	public List<UpdateHistory> findUpdateHistoryByMember(@Param("memberNumber")Long memberNumber, 
			@Param("pageBegin")Integer pageBegin, @Param("pageSize")Integer pageSize);
	
	/**
	 * 根据工号查升级记录的总数
	 * @param memberNumber
	 * @return
	 */
	@Select("select count(1) from updatehistory where membernumber = #{memberNumber}")
	public Integer findCountsByMemberUpdate(@Param("memberNumber")Long memberNumber);
	
	/**
	 * 根据是否在职查升级记录的总数
	 * @param isOnJob
	 * @return
	 */
	@SelectProvider(method = "findCountsByJobUpdate", type = MemberSqlBuilder.class)
	public Integer findCountsByJobUpdate(@Param("isonjob")Integer isOnJob);
}
