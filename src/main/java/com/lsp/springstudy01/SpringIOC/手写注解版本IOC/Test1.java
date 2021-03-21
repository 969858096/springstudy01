package com.lsp.springstudy01.SpringIOC.手写注解版本IOC;

/**
 * @FileName: Test
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 21:06
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        MyAnnotationApplicationContext context = new MyAnnotationApplicationContext("com.lsp.springstudy01.SpringIOC.手写注解版本IOC");
        MyStudentService myStudentServiceImpl = (MyStudentService) context.getBean("myStudentServiceImpl");
        myStudentServiceImpl.add();

    }
}
