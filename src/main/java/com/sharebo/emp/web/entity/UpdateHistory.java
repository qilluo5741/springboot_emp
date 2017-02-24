package com.sharebo.emp.web.entity;

import java.io.Serializable;
/**
 * 升星记录
 * 当一个员工的升级分数满365后，会进行勋章升级
 * blingbling地升级
 * 然后勋章值+1
 * 接着升级分数-365
 * 然后就这么记录了。
 * @author zhuhaiyuan
 *
 */
public class UpdateHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberId;// 员工外键
	private String time;// 记录时间
	private Long membernumber;// 员工工号
	private String updatemedal;// 勋章升级细节
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getMembernumber() {
		return membernumber;
	}
	public void setMembernumber(Long membernumber) {
		this.membernumber = membernumber;
	}
	public String getUpdatemedal() {
		return updatemedal;
	}
	public void setUpdatemedal(String updatemedal) {
		this.updatemedal = updatemedal;
	}
	public UpdateHistory(String id, String memberId, String time, Long membernumber, String updatemedal) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.time = time;
		this.membernumber = membernumber;
		this.updatemedal = updatemedal;
	}
	
	public UpdateHistory() {
		super();
	}
	@Override
	public String toString() {
		return "UpdateHistory [id=" + id + ", memberId=" + memberId + ", time=" + time + ", membernumber="
				+ membernumber + ", updatemedal=" + updatemedal + "]";
	}
	
}
