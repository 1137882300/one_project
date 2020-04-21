package com.z.Log;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class log implements MethodBeforeAdvice {
    //method:要执行的目标对象的方法
    //args：参数
    //target：目标对象
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"de"+method.getName()+"bei zhi xin");
    }
}
