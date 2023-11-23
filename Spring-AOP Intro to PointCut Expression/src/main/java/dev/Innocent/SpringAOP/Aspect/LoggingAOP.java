package dev.Innocent.SpringAOP.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAOP {
    // This is where we add all of our related advices for logging. Let's start with an @Before advice
    //@Before("execution(public void add*())")
    //@Before("execution(* add*(dev.Innocent.SpringAOP.Model.Account, ..))")
    @Before("execution(* dev.Innocent.SpringAOP.DAO.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("\n ====>>> Executing @Before advice on addAccount");
    }
}
