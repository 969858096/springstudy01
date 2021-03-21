package com.lsp.springstudy01.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @FileName: StudentAspect
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 21:38
 */
@Component
@Aspect
public class StudentAspect{




    /*@Before("execution(* com.lsp.springstudy01.service.StudentService.addStudent(..))")
    void before() {
        System.out.println("前置通知");
    }

    @After("execution(* com.lsp.springstudy01.service.StudentService.addStudent(..))")
    void after() {
        System.out.println("后置通知");
    }*/

   /* @Around("execution(* com.lsp.springstudy01.service.StudentService.addStudent(..))")
    void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知前");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知后");
    }

    @AfterThrowing("execution(* com.lsp.springstudy01.service.StudentService.addStudent(..)))")
    void exceptionInform(){
        System.out.println("异常通知");
    }

    @AfterReturning("execution(* com.lsp.springstudy01.service.StudentService.addStudent(..)))")
    void runningInform(){
        System.out.println("运行通知");
    }*/

}
