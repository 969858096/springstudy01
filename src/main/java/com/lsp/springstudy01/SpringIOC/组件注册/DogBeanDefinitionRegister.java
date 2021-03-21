package com.lsp.springstudy01.SpringIOC.组件注册;

import com.lsp.springstudy01.bean.Cat;
import com.lsp.springstudy01.bean.Dog;
import com.lsp.springstudy01.bean.Girl;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @FileName: DogBeanDefinitionRegister
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 13:12
 */
public class DogBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //创建一个bean定义对象
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class);
        //把bean定义对象导入到容器中
        registry.registerBeanDefinition("dog",rootBeanDefinition);
    }
}
