package dev.Innocent.SpringAOP.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpression {

    @Pointcut("execution(* dev.Innocent.SpringAOP.DAO.*.*(..))")
    public void forDaoPackage(){}

    // Create a pointcut for getter methods
    @Pointcut("execution(* dev.Innocent.SpringAOP.DAO.*.get*(..))")
    public void getter(){}

    // Create a pointcut for setter methods
    @Pointcut("execution(* dev.Innocent.SpringAOP.DAO.*.set*(..))")
    public void setter(){}

    // Create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}
}
