package com.kirti.springboot.aop;

import com.kirti.springboot.annotations.LazyAutowired;
import com.kirti.springboot.utils.CustomExceptionHandlerUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ExceptionHandlingAspect {
    @LazyAutowired
    private CustomExceptionHandlerUtil exceptionHandlingObject;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Around(value="execution(* com.kirti.springboot.cucumber.steps.*.*(..))")
    private Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Object obj=proceedingJoinPoint.proceed();
            return obj;
        }
        catch(CustomExceptionHandlerUtil e) {
            logger.info("ExceptionHandlingAspect Message {}",e.getMessage());
            throw new CustomExceptionHandlerUtil(e.getMessage());
        }
    }

}
