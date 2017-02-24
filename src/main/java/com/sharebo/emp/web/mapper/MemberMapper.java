package com.sharebo.emp.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.sharebo.emp.web.entity.Member;
import com.sharebo.emp.web.entity.Menu;
import com.sharebo.emp.web.entityDto.LevelUpDto;
import com.sharebo.emp.web.sqlbuilder.MemberSqlBuilder;
/**
 * 员工类的mapper封装接口
 * @author zhuhaiyuan
 *
 */
@Mapper
public interface MemberMapper {
	@Insert("insert into member(number,password,name,authority"
			+ ",medal,isonjob,nowgrade,levelgrade) VALUE(#{number},#{password},#{name},#{authority}"
			+ ",#{medal},#{isonjob},0,0)")
	int addMember(Member member) throws Exception;
	
	@Update("update member set name=#{name},authority"
			+ "=#{authority},medal=#{medal},isonjob=#{isonjob}"
			+ ",nowgrade=#{nowgrade},levelgrade = #{levelgrade},password = #{password}"
			+ " where number = #{number}")
	public int updateMember(Member member);
	
	@Update("update member set nowgrade = nowgrade + #{grade},"
			+ "levelgrade = levelgrade + #{grade} where number=#{memberNumber}")
	public int updateGradeInMember(@Param("grade") Integer grade,@Param("memberNumber") Long memberNumber);
	
	@Select("select * from member")
	public List<Member> findAllMembers();
	
	@SelectProvider(type = MemberSqlBuilder.class, method = "findMembers")
	public List<Member> findMembers(@Param("isonjob")Integer isOnJob,@Param("medal") Integer medal
			,@Param("pageBegin")Integer pageBegin, @Param("pageSize") Integer pageSize);
	
	@SelectProvider(type = MemberSqlBuilder.class, method = "findCountsByMembers")
	public Integer findCountsByMembers(@Param("isonjob")Integer isOnJob,@Param("medal") Integer medal);
	
	@Select("select * from member where name = #{name}")
	public List<Member> findMemberByName(@Param("name")String name);
	
	@Select("select * from member where number = #{memberNumber}")
	public Member findMemberByNumber(@Param("memberNumber")Long memberNumber);
	
	@Update("update member set levelgrade = levelgrade - 365, medal = medal + 1 where levelgrade >= 365")
	public int updateMedal();
	
	@Update("update member set levelgrade = levelgrade - 365, medal = medal + 1 where number = #{memberNumber}"
			+ "and levelgrade >= 365")
	public int updateMedalByNumber(Long memberNumber);
	
	@Update("update member set nowgrade = 0")
	public int updateNowGrade();
	
	@Update("update member set levelgrade = levelgrade + 1, nowgrade = nowgrade +1 where isonjob = 1")
	public int updateDailyGrade();
	
	@Update("update member set password = #{newPwd} where number = #{number}")
	public int updatePassword(@Param("number")Long number,@Param("newPwd") String newPwd);
	
	@Select("select password from member where number = #{number}")
	public String findpwdByMemberNumber(Long number);
	
	@Select("select number,medal from member where levelgrade >= 365")
	public List<LevelUpDto> findMemberAndMedal();
	
	@SelectProvider(type = MemberSqlBuilder.class, method = "checkMenuAuthority")
	public List<Menu> checkMenuAuthority(@Param("authority")int authority);
	
	@Select("select authority from member where number = #{number}")
	public Integer findAuthorityByNumber(Long number);
}
