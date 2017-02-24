package com.sharebo.emp.web.entity;

import java.io.Serializable;

/**
 * 分数调整记录
 * 管理员对调休、加班、创新、奉献等对员工分数调整时，记录的历史记录
 * @author zhuhaiyuan
 *
 */
public class GradeHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String time;// 记录时间
	private String type;// 调分类型
	private Integer grade;// 分数细节，正负都可以，废话，减分当然得有
	private String detail;
	private Long membernumber;// 员工工号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Long getMembernumber() {
		return membernumber;
	}
	public void setMembernumber(Long membernumber) {
		this.membernumber = membernumber;
	}
	
	public GradeHistory(String id, String time, String type, Integer grade, String detail, Long membernumber) {
		super();
		this.id = id;
		this.time = time;
		this.type = type;
		this.grade = grade;
		this.detail = detail;
		this.membernumber = membernumber;
	}
	public GradeHistory() {
		super();
	}
	@Override
	public String toString() {
		return "GradeHistory [id=" + id + ", time=" + time + ", type=" + type + ", grade=" + grade + ", detail="
				+ detail + ", membernumber=" + membernumber + "]";
	}
	
}
