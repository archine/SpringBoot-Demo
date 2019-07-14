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
public class WithinAspect {

    @Pointcut("within(com.gj.web..*)")
    public void withinCut() {

    }

    @Before("withinCut()")
    public void withinBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("Within开始拦截, 当前方法名为: " + method.getName());
    }
}
