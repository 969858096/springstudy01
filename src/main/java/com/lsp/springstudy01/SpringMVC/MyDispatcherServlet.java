package com.lsp.springstudy01.SpringMVC;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsp.springstudy01.utils.ClassUtil;
import com.lsp.springstudy01.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: MyDispathtcherServlet
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/24 09:48
 */
@Slf4j
public class MyDispatcherServlet extends DispatcherServlet {

    private ConcurrentHashMap<String, Object> beanId_Object_Mapping = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> url_className_Mapping = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> url_methodName_Mapping = new ConcurrentHashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        initBean();
    }

    private void initBean() {
        //扫包,注册Bean
        scanPackageRegisterBean();
    }

    private void scanPackageRegisterBean() {
        List<Class<?>> classes = ClassUtil.getClasses("com.lsp.springstudy01.SpringMVC");
        for (Class<?> aClass : classes) {
            try {
                MyController myController = aClass.getDeclaredAnnotation(MyController.class);
                if (ObjectUtils.isNotNull(myController)) {
                    String beanId = MyUtils.toLowerCaseFirstChart(aClass.getSimpleName());
                    beanId_Object_Mapping.put(beanId, aClass.newInstance());
                    //判断类上有映射注解，映射urI和类名与映射urI和方法名
                    urlClassNameAndMethodMapping(aClass);
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.info("class not found");
            }
        }
    }

    private void urlClassNameAndMethodMapping(Class<?> aClass) {
        String baseURI = "";
        MyRequestMapping myrequestMapping = aClass.getAnnotation(MyRequestMapping.class);
        if (ObjectUtils.isNotNull(myrequestMapping)) {
            baseURI = myrequestMapping.value();
        }
        String methodUrl = "";
        String requestURI = "";
        Method[] methods = aClass.getDeclaredMethods();
        if (ObjectUtils.isNotNull(methods) && methods.length > 0) {
            for (Method method : methods) {
                MyRequestMapping myRequestMappingOnMethod = method.getAnnotation(MyRequestMapping.class);
                if (ObjectUtils.isNotNull(myRequestMappingOnMethod)) {
                    methodUrl = myRequestMappingOnMethod.value();
                    requestURI = "/"+baseURI + "/" + methodUrl;
                    url_methodName_Mapping.put(requestURI, method.getName());
                    url_className_Mapping.put(requestURI, aClass.getSimpleName());
                }
            }
        }
    }

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
       //initBean();
        String requestURI = request.getRequestURI();
        String className = url_className_Mapping.get(requestURI);
        if (StringUtils.isEmpty(className)){
            throw new Exception("class not found");
        }
        Object object = beanId_Object_Mapping.get(MyUtils.toLowerCaseFirstChart(className));
        if (ObjectUtils.isNull(object)){
            throw new Exception("class not found");
        }
        String methodName = url_methodName_Mapping.get(requestURI);
        if (StringUtils.isEmpty(className)){
            throw new Exception("method not found");
        }
        Method method = object.getClass().getMethod(methodName);
        Object result = method.invoke(object);
        System.out.println(result);
        response.getWriter().write("hello MyServlet"+result);
    }
}
