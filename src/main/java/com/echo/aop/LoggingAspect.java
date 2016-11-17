package com.echo.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	//括号中的可以是接口，这样会在接口的每一个实现类开始之前执行一段代码
	@After("execution(* com.echo.controller.CustomerController.signin(..))")
	public void customerLogin(JoinPoint joinPoint){
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(args.get(0));
	}

}
