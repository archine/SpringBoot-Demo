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
public class BeanAspect {

    @Pointcut("bean(testController)")
    public void beanCut() {

    }

    @Before("beanCut()")
    public void beanCutProcess(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("Bean开始拦截, 当前拦截的方法名: " + method.getName());
    }
}
