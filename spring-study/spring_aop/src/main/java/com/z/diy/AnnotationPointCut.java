package com.z.diy;
//方式三：使用注解方式实验aop

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect//标注这是一个切面
public class AnnotationPointCut {

    @Before("execution(* com.z.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("=======方法执行前========");
    }

    //在环绕增强中可以给一个参数，代表我们要获取处理切入的点
    @Around("execution(* com.z.service.UserServiceImpl.*(..))")
    public void around(){

    }




}
