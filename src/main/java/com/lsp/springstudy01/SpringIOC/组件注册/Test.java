package com.lsp.springstudy01.SpringIOC.组件注册;

import com.lsp.springstudy01.bean.Cat;
import com.lsp.springstudy01.bean.Dog;
import com.lsp.springstudy01.bean.Girl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @FileName: Test
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 13:07
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Dog bean = context.getBean(Dog.class);
        System.out.println(bean);
        System.out.println("-----------------------");
        Cat cat = context.getBean(Cat.class);
        System.out.println(cat);
        System.out.println("------------------------");
        Girl girl =context.getBean(Girl.class);
        Dog girl1 = (Dog) context.getBean("dog");
        System.out.println(girl);
        System.out.println(girl1);

    }
}
