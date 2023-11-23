package com.luv2code.springboot.thymeleafdemo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAOP {

    // Setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // Setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    // Do the same for service and DAO
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDAOPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        // Display method we are calling
        String thMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("====>> in @Before: calling method: " + thMethod);

        // Display the arguments to the method

        // Get the arguments
        Object[] args = theJoinPoint.getArgs();

        // Loop through and display args
        for(Object tempArg : args){
            myLogger.info("====>>>> argument: " + tempArg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult){
        // Display method we are calling
        String thMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("====>> in @AfterReturning: from method: " + thMethod);

        // Display data returned
        myLogger.info("====>>> result: " + theResult);
    }
}
