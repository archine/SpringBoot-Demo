package com.gj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author Gjing
 **/
@Aspect
//@Component
public class ExecutionAspect {

    @Pointcut("execution(* com.gj.web..*.ok*(..))")
    public void executionCut() {

    }

    @Before("executionCut())")
    public void executionBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("Execution表达式开始拦截, 当前拦截的方法名: " + method.getName());
    }
}
