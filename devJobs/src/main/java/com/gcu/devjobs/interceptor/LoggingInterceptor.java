package com.gcu.devjobs.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Around("execution(* com.gcu.devjobs.controllers..*(..)) || execution(* com.gcu.devjobs.services..*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("Entering {}", methodName);
		
		try {
			Object result = joinPoint.proceed();
			logger.info("Exiting {}", methodName);
			return result;
		} catch (Exception e) {
			logger.error("Exception in {}", methodName, e);
			throw e;
		}
	}

}
