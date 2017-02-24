package com.sharebo.emp.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.sharebo.emp.web.entity.GradeHistory;
import com.sharebo.emp.web.sqlbuilder.MemberSqlBuilder;

@Mapper
public interface GradeHistoryMapper {
	@Insert("insert into gradehistory VALUES(UUID_SHORT(),now(),#{type},#{grade},#{detail},#{membernumber})")
	int addGradeHistory(GradeHistory gradeHistory);
	
	@Update("update gradehistory set time = now(),"
			+ "membernumber = #{membernumber},grade = #{grade}"
			+ ",detail = #{detail} ,type=#{type} where id=#{id}")
	public int updateGradeHistory(GradeHistory gradeHistory);
	
	@Delete("delete from gradehistory")
	public int deleteGradeHistory(@Param("GradeHistoryId")String GradeHistoryId);
	
	@Select("select * from gradehistory where membernumber = #{memberNumber} order by time desc limit #{pageBegin},#{pageSize}")
	public List<GradeHistory> findGradeHistoryByMember(@Param("memberNumber")Long memberNumber, 
			@Param("pageBegin")Integer pageBegin, @Param("pageSize")Integer pageSize);
	
	@Select("select count(1) from gradehistory where membernumber = #{memberNumber}")	
	public Integer findCountsByMemberGrade(Long memberNumber);
	
	@SelectProvider(type = MemberSqlBuilder.class, method = "findCountsByJobGrade")
	public Integer findCountsByJobGrade(@Param("isonjob")Integer isOnJob);
	
	@SelectProvider(type = MemberSqlBuilder.class, method = "findGradeHistoryByIsOnJob")
	public List<GradeHistory> findGradeHistoryByIsOnJob(@Param("isonjob")Integer isOnJob, 
			@Param("pageBegin")Integer pageBegin, @Param("pageSize")Integer pageSize);

}
