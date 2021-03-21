package com.lsp.springstudy01.SpringIOC.手写xml版本IOC;

import com.lsp.springstudy01.bean.Student;
import org.dom4j.DocumentException;

/**
 * @FileName: Test
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 16:11
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext("xml/spring.xml");
        Student student = (Student) myClassPathXmlApplicationContext.getBean("student");
        System.out.println(student);
    }
}
