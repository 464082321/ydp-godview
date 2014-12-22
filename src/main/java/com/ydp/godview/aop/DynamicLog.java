package com.ydp.godview.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DynamicLog {
	
//	@Pointcut("execution(* com..service..*.*(..))")
//	@Pointcut("execution(* com.john.service..*.*(..))")
//	public void normalCut(){};
	
//	@Around("within(@org.springframework.stereotype.Controller *)")
	@Around("execution(* com.john.service..*.*(..))")
	public Object markLog(final ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("有东西被,拦截了......");
		
		return pjp.proceed();
	}
}
