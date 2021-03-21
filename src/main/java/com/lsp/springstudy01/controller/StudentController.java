package com.lsp.springstudy01.controller;

import cn.hutool.core.lang.UUID;
import com.lsp.springstudy01.bean.Student;
import com.lsp.springstudy01.impl.StudentServiceImpl;
import com.lsp.springstudy01.proxy.动态代理.StudentDymnicProxy;
import com.lsp.springstudy01.proxy.静态代理.StudentProxy;
import com.lsp.springstudy01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

/**
 * @FileName: StudentController
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 19:34
 */
//@RestController
//@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentProxy studentProxy;

    @Autowired
    private StudentDymnicProxy studentDymnicProxy;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    private Student student = null;

    @PostMapping("/add")
    public String add(){
        String id = UUID.randomUUID().toString().replace("-", "");
        student.setId(id);
        student.setName("张三"+id.substring(1,5));
        student.setAge(10);
        studentProxy.addStudent(student);
        return "添加成功";
    }

    @PostMapping("add1")
    public String add1(){
        ClassLoader classLoader = studentService.getClass().getClassLoader();
        Class<?>[] interfaces = studentService.getClass().getInterfaces();
        StudentService o = (StudentService) Proxy.newProxyInstance(classLoader, interfaces, studentDymnicProxy);
        String id = UUID.randomUUID().toString().replace("-", "");
        student = new Student();
        student.setId(id);
        student.setName("张三"+id.substring(1,5));
        student.setAge(10);
        o.addStudent(student);
        return "success";
    }

    @PostMapping("add2")
    public String add2(){
        String id = UUID.randomUUID().toString().replace("-", "");
        student = new Student();
        student.setId(id);
        student.setName("张三"+id.substring(1,5));
        student.setAge(10);
        studentService.addStudent(student);
        return "success";
    }

    @PostMapping("saveToA")
    public String saveToA(){
       studentService.saveMoneyToA("123",100L);
        return "success";
    }

    @PostMapping("saveAToB")
    public String saveAToB(){
        studentService.saveMoney("123",50L);
        return "success";
    }




}
