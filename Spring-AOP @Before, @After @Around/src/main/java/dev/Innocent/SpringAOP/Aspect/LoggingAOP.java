package dev.Innocent.SpringAOP.Aspect;

import dev.Innocent.SpringAOP.Model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class LoggingAOP {

    // Add a new advice for @Around
    @Around("execution(* dev.Innocent.SpringAOP.Service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        // print out which method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @Around on method: " + method);

        // get begin timeStamp
        long begin = System.currentTimeMillis();

        // Now, Let's execute the method
        Object result = null;

        try{
            result = theProceedingJoinPoint.proceed();
        }
        catch(Exception exc){
            // Log the exception
            System.out.println(exc.getMessage());

            // Give user a custom message
           // result = "Major accident! But no worries, your private AOP helicopter is on the way!";

            // rethrow exception
            throw exc;
        }

        // Get end timeStamp
        long end = System.currentTimeMillis();

        // Compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====>>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* dev.Innocent.SpringAOP.DAO.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @After (finally) on method: " + method);
    }

    //  Add a new advice for handling exception
    @AfterThrowing(
            pointcut = "execution(* dev.Innocent.SpringAOP.DAO.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @AfterThrowing on method: " + method);

        // Log the exception
        System.out.println("\n====>>>> The exception is: " + theExc);
    }

    // Add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* dev.Innocent.SpringAOP.DAO.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint  theJoinPoint, List<Account> result){
        // Print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @AfterReturning on method: " + method);

        // Print out the results of the method call
        System.out.println("\n====>>> result is: " + result);

        // Let's post-process the data ... let's modify it :-

        // Convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        System.out.println("\n====>>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // Loop through accounts
        for(Account tempAccount : result){

            // Get upperCase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // Update the name on the account
            tempAccount.setName(theUpperName);
        }
    }

    // This is where we add all of our related advices for logging. Let's start with an @Before advice
    //@Before("execution(public void add*())")
    //@Before("execution(* add*(dev.Innocent.SpringAOP.Model.Account, ..))")

    @Before("dev.Innocent.SpringAOP.Aspect.AopExpression.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n ====>>> Executing @Before advice on addAccount");

        // Display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        // Display method argument
        System.out.println("Method: " + methodSignature);

        // Get args
        Object[] args = theJoinPoint.getArgs();

        // Loop through args
        for(Object tempArg : args){
            System.out.println(tempArg);

            if(tempArg instanceof Account){

                // DownCast and print Account specific data
                Account theAccount = (Account)  tempArg;

                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }
    }

}
