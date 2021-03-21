package com.lsp.springstudy01.SpringMVC;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @FileName: MyServerConfig
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/24 10:45
 */
//@Configuration
public class MyServerConfig {
    /**
     * 注册servlet组件
     * @return
     */
    @Bean
    public ServletRegistrationBean myDispatcherServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyDispatcherServlet(), "/");
        // 设置启动顺序
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
    @Bean //一定要将这个定制器加入到容器中
    public WebServerFactoryCustomizer webServerFactory() {
        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                factory.setPort(8888);
            }
        };
    }

}
