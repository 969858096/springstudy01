package com.lsp.springstudy01.SpringIOC.ioc1;

import com.lsp.springstudy01.bean.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @FileName: Test
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/27 21:16
 */
public class Test {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("xml/spring.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        Student student = (Student) factory.getBean("student");
        Student student1 = (Student) factory.getBean("student");
        System.out.println(student);
        System.out.println(student1);
        System.out.println(student == student1);
    }
}
