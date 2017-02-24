package com.sharebo.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 程序入口
 * @author niewei
 */
//主入口配置
@SpringBootApplication
//启动定时任务配置 
@EnableScheduling
//启动aop
//@EnableAspectJAutoProxy
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
