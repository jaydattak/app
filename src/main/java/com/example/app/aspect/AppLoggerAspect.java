package com.example.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AppLoggerAspect {

	private static final Logger logger = LoggerFactory.getLogger(AppLoggerAspect.class);

	@Before("execution( * com.example.app..*.*(..))")
	public void before(JoinPoint joinpoint) {
		Signature sign = joinpoint.getSignature();
		logger.info(sign.getDeclaringTypeName() + " : " + sign.getName() + " : Started");
	}

	@After("execution( * com.example.app..*.*(..))")
	public void after(JoinPoint joinpoint) {
		Signature sign = joinpoint.getSignature();
		logger.info(sign.getDeclaringTypeName() + " : " + sign.getName() + " : Completed");
	}

}
