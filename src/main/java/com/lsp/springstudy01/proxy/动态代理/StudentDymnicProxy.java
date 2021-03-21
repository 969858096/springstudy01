package com.lsp.springstudy01.proxy.动态代理;

import com.lsp.springstudy01.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @FileName: StudentDymnicProxy  动态代理
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 20:58
 */
//@Component
public class StudentDymnicProxy implements InvocationHandler {

    //注入被代理对象
    @Autowired
    private StudentServiceImpl studentService;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        System.out.println("动态代理前");
        result = method.invoke(studentService,args);
        System.out.println("动态代理后");
        return result;
    }

}
