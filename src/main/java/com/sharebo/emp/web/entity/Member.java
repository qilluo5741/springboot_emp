package com.sharebo.emp.web.entity;

import java.io.Serializable;

/**
 * 员工表
 * @author zhuhaiyuan
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;// 员工姓名
	private Long number;// 员工工号、为主键
	private String password;// 员工密码
	private Long nowgrade;// 当年分数
	private Long levelgrade;//升级所需分数
	private Long medal;// 勋章数
	private Integer isonjob;// 是否在职：0，离职；1，在职
	private Integer authority;// 权限：0，普通员工；1，管理员
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getNowgrade() {
		return nowgrade;
	}
	public void setNowgrade(Long nowgrade) {
		this.nowgrade = nowgrade;
	}
	public Long getLevelgrade() {
		return levelgrade;
	}
	public void setLevelgrade(Long levelgrade) {
		this.levelgrade = levelgrade;
	}
	public Long getMedal() {
		return medal;
	}
	public void setMedal(Long medal) {
		this.medal = medal;
	}
	public Integer getIsonjob() {
		return isonjob;
	}
	public void setIsonjob(Integer isonjob) {
		this.isonjob = isonjob;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	public Member(Long number, String name, Long nowgrade, Long levelgrade, Long medal, Integer isonjob,
			Integer authority) {
		super();
		this.number = number;
		this.name = name;
		this.nowgrade = nowgrade;
		this.levelgrade = levelgrade;
		this.medal = medal;
		this.isonjob = isonjob;
		this.authority = authority;
	}
	
	public Member() {
		super();
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", number=" + number + ", nowgrade=" + nowgrade + ", levelgrade="
				+ levelgrade + ", medal=" + medal + ", isonjob=" + isonjob + ", authority=" + authority + "]";
	}
}
