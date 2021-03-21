/*
package com.lsp.springstudy01;

import com.lsp.springstudy01.bean.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com.lsp.springstudy01.bean","com.lsp.springstudy01.config","com.lsp.springstudy01.controller",
        "com.lsp.springstudy01.dao","com.lsp.springstudy01.mapper","com.lsp.springstudy01.service"})
class Springstudy01ApplicationTests {

    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

}
*/
