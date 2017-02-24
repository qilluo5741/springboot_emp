package com.sharebo.emp.web.utils;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sharebo.emp.web.service.MemberService;

/**
 * 定时任务测试
 * @author niewei
 *
 */
//创建扫描
@Component
public class TimingTask {
	/*
	 * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
	 * 
	 * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
	 * 
	 * @Scheduled(initialDelay=1000, fixedRate=5000)
	 * ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
	 * 
	 * @Scheduled(cron="* /5 * * * * *") ：通过cron表达式定义规则
	 */
	@Autowired
	MemberService memberService;
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/* @Scheduled(fixedRate = 5000)
	    public void reportCurrentTime() {
	        System.out.println("现在时间：" + sdf.format(new Date()));
	    }*/
	 
	 /**
	  * 日常加分的定时。
	  * 定时为每日凌晨0点0分
	  * @throws InterruptedException
	  */
	 @Scheduled(cron = "0 0 0 * * ?")
	    public void updateDailyGrade() throws InterruptedException {
		 //日常给分
		 memberService.updateDailyGrade();
		 //给分之后，检测是否有满365分的,如果有，就加分。
		 memberService.updateMedal();
	     System.out.println("日常加分成功：" + sdf.format(new Date()));
	    }
	 
	 /**
	  * 每年清空当年分数
	  * 在年底最后秒。
	  * 12月31日23点59分59秒
	  * @throws InterruptedException
	  */
	 @Scheduled(cron = "59 59 23 31 12 ?")
	    public void updateNowGrade() throws InterruptedException {
		 //年底清分
		 memberService.updateNowGrade();
	     System.out.println("当年分数清空成功：" + sdf.format(new Date()));
	    }
}
