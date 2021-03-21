package com.lsp.springstudy01.controller;

import com.lsp.springstudy01.bean.Student;
import com.lsp.springstudy01.impl.StudentServiceImpl;
import com.lsp.springstudy01.proxy.动态代理.StudentDymnicProxy;
import com.lsp.springstudy01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

/**
 * @FileName: ProxyController
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/19 07:34
 */
//@RestController
public class ProxyController {



    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private StudentDymnicProxy dynamicProxy;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "dynamicProxy")
    public void dynamicProxy(){

        StudentServiceImpl studentService1 = new StudentServiceImpl();

        ClassLoader classLoader = studentService1.getClass().getClassLoader();
        Class<?>[] interfaces = studentService1.getClass().getInterfaces();
        StudentService studentService = (StudentService)Proxy.newProxyInstance(classLoader, interfaces, dynamicProxy);

        Student student = new Student();
        student.setId("lsp");
        student.setName("lsp");
        student.setAge(26);
        studentService.addStudent(student);
    }
}
