package com.example.demo.advice;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Pointcut("execution(* say*(..) )")
	public void pointcut(){  } //포인트컷을 적용한 메서드. Advice관련 어노테이션에서 해당메서드이름으로 포인트컷을 사용할 수 있게된다.

//	@Before("pointcut()")
	public void logBefore(JoinPoint jp) throws Throwable{
		logger.error("@Before:" +  jp.getSignature());
	}
	@Around("execution(* say*(..) )")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable { 
		long begin = System.currentTimeMillis(); 
		logger.error("메서드 호출 시작 시간 : " + new Date(begin));
		
		Object result = pjp.proceed();
		
		long end = System.currentTimeMillis(); 
		logger.error("메서드 호출 종료 시간 : " + new Date(end));
		logger.error("총소요시간 : " +  (end- begin) + "mills"); 
		
		//return result;
		return result + "♥";
	}

}
