package com.lsp.springstudy01.SpringIOC.组件注册;
import com.lsp.springstudy01.bean.Girl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @FileName: MainConfig
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 13:03
 */
@Configuration
@Import(value = {Girl.class,CustomImportSelector.class,DogBeanDefinitionRegister.class})
public class MainConfig {
}
