package com.sharebo.emp.web.sqlbuilder;

import java.text.MessageFormat;
import java.util.List;

import org.apache.ibatis.jdbc.SQL;

import com.sharebo.emp.web.entityDto.LevelUpDto;

/**
 * 注解@SelectProvider所需的拼接SQL类
 * 分页都还没处理
 * @author zhuhaiyuan
 *
 */
public class MemberSqlBuilder {
	/**
	 * 查询员工信息针对是否在职、勋章数的SQL拼接
	 * @param isOnJob
	 * @param medal
	 * @param pageBegin
	 * @param pageSzie
	 * @return
	 */
	public String findMembers(final Integer isOnJob, final Integer medal,final Integer pageBegin, final Integer pageSzie) {
		return new SQL() {
			{
				SELECT("*");
				FROM("member");
				if (isOnJob != -1) {
					WHERE("isonjob = " + isOnJob);
				}
				if (medal != -1) {
					AND();
					WHERE("medal = " + medal);
					ORDER_BY("nowgrade" + " desc limit " + pageBegin + "," + pageSzie);
				} else{
					ORDER_BY("medal" + " desc limit " + pageBegin + "," + pageSzie);
				}
			}
		}.toString();
	}
	
	 /**
	  * 查询员工信息针对是否在职、勋章数的SQL拼接的总数
	  * @param isOnJob
	  * @param medal
	  * @return
	  */
	 public String findCountsByMembers(final Integer isOnJob, final Integer medal) {
			return new SQL() {
				{
					SELECT("count(1)");
					FROM("member");
					
					if (isOnJob != -1) {
						WHERE("isonjob = " + isOnJob);
					} 
					if (medal == -1) {
						
					}else{ AND();
					WHERE("medal = " + medal);}
					
					
				}
			}.toString();
		}
	
	/**
	 * 通过是否在职查找调分记录
	 * @param isOnJob
	 * @param pageBegin
	 * @param pageSzie
	 * @return
	 */
	public String findGradeHistoryByIsOnJob(final Integer isOnJob,final Integer pageBegin, final Integer pageSzie) {
		return new SQL() {
			{
				SELECT_DISTINCT("*");
				FROM("gradehistory,member");
				if (isOnJob != -1) {
					WHERE("member.isonjob = " + isOnJob);
				}
				AND();
				WHERE("gradehistory.membernumber = member.number");
				ORDER_BY("time" + " desc limit " + pageBegin + "," + pageSzie);
			}
		}.toString();
	}
	
	/**
	 * 根据在职查询调分记录
	 * @param isOnJob
	 * @return
	 */
	public String findCountsByJobGrade(final Integer isOnJob) {
		return new SQL() {
			{
				SELECT("count(1)");
				FROM("gradehistory,member");
				if (isOnJob != -1) {
					WHERE("member.isonjob = " + isOnJob);
				}
				AND();
				WHERE("gradehistory.membernumber = member.number");
			}
		}.toString();
	}
	
	/**
	 * 通过是否在职查找升级记录,按照时间降序
	 * @param isOnJob
	 * @param pageBegin
	 * @param pageSzie
	 * @return
	 */
	public String findUpdateHistoryByIsOnJob(final Integer isOnJob,final Integer pageBegin, final Integer pageSzie) {
		return new SQL() {
			{
				SELECT_DISTINCT("*");
				FROM("updatehistory,member");
				if (isOnJob != -1) {
					WHERE("member.isonjob = " + isOnJob);
				}
				AND();
				WHERE("updatehistory.membernumber = member.number");
				ORDER_BY("time" + " desc limit " + pageBegin + "," + pageSzie);
			}
		}.toString();
	}
	
	/**
	 * 根据在职查询升级记录总数
	 * @param isOnJob
	 * @return
	 */
	public String findCountsByJobUpdate(final Integer isOnJob) {
		return new SQL() {
			{
				SELECT("count(1)");
				FROM("updatehistory,member");
				if (isOnJob != -1) {
					WHERE("member.isonjob = " + isOnJob);
				}
				AND();
				WHERE("updatehistory.membernumber = member.number");
			}
		}.toString();
	}
	
	
	/**
	 * 批量插入升级记录
	 * @param luds
	 * @return
	 */
	 public String addUpdateHistory(List<LevelUpDto> luds) {  
	        StringBuilder sb = new StringBuilder();  
	        sb.append("INSERT INTO updatehistory ");  
	        sb.append("(id, time,updatemedal,membernumber) ");  
	        sb.append("VALUES ");  
	        MessageFormat mf = new MessageFormat("(UUID_SHORT(),now(),#'{'luds[{0}].updatemedal},#'{'luds[{0}].membernumber})");  
	        for (int i = 0; i < luds.size(); i++) {  
	            sb.append(mf.format(new Object[]{i}));  
	            if (i < luds.size() - 1) {  
	                sb.append(",");  
	            }  
	        }
	        return sb.toString();  
	    }  
	 
	 /**
	  * 根据权限查找菜单列表
	  * @param authority
	  * @return
	  */
	 public String checkMenuAuthority(final Integer authority) {  
		 return new SQL() {
				{
					SELECT("*");
					FROM("menu");
					if (authority == 0) {
						WHERE("authority = " + authority);
					}
				}
			}.toString();  
	    }  
	 
}
