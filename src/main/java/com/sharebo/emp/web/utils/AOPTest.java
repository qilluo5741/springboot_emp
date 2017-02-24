package com.sharebo.emp.web.utils;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * aop测试 记录访问日志
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
public class AOPTest {
	private Logger logger = Logger.getLogger(getClass());
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	@Pointcut("execution(public * com.sharebo.emp.web.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		//设置进入时间
		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("请求路劲（URL）: " + request.getRequestURL().toString());
		logger.info("请求方法（HTTP_METHOD）: " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容 
		logger.info("RESPONSE : " + ret);
		logger.info("处理时间SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
	}
}
