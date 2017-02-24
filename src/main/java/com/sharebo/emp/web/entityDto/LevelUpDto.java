package com.sharebo.emp.web.entityDto;

/**
 * 获取员工号与勋章数的DTO
 * @author zhuhaiyuan
 *
 */
public class LevelUpDto {

	private String membernumber;// 员工工号、为主键
	private String updatemedal;// 勋章数
	public String getNumber() {
		return membernumber;
	}
	public void setNumber(String number) {
		this.membernumber = number;
	}
	public String getMedal() {
		return updatemedal;
	}
	public void setMedal(String medal) {
		this.updatemedal = medal;
	}
	
}
