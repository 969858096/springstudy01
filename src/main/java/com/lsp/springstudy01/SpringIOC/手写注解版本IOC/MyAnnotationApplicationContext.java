package com.lsp.springstudy01.SpringIOC.手写注解版本IOC;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsp.springstudy01.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: MyAnnotationApplicationContext
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 20:25
 */
@Slf4j
public class MyAnnotationApplicationContext {

    private String packageName;
    private static ConcurrentHashMap<String, Object> beans = null;

    public MyAnnotationApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        if (StringUtils.isEmpty(packageName)) {
            throw new Exception("包名不能为空");
        }
        //容器初始化
        this.beans = new ConcurrentHashMap<>();
        //对象初始化
        initBean();
        //依赖注入
        for (Map.Entry<String, Object> entity : beans.entrySet()) {
            Object object = entity.getValue();
            attrDI(object);
        }
    }

    private void initBean() throws Exception {
        //扫描报下所有类,把拥有@MyService的class初始化后存到容器中
        getAllClass();
    }

    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("beanId不能为空");
        }
        Object bean = beans.get(beanId);
        if (ObjectUtils.isNull(bean)) {
            throw new Exception("class not found");
        }
        return bean;
    }

    private void getAllClass() throws Exception {
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        for (Class<?> aClass : classes) {
            MyService annotation = aClass.getAnnotation(MyService.class);
            if (ObjectUtils.isNotNull(annotation)) {
                String className = aClass.getSimpleName();
                if (StringUtils.isEmpty(className)) {
                    throw new Exception("类名不存在");
                }
                String beanId = toLowerCaseFirstChart(className);
                beans.put(beanId, aClass.newInstance());
            }
        }

    }

    //属性依赖注入
    private void attrDI(Object object) throws Exception {
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            MyAutowired annotation = field.getAnnotation(MyAutowired.class);
            if (ObjectUtils.isNotNull(annotation)) {
                String attName = field.getName();
                if (StringUtils.isEmpty(attName)) {
                    throw new Exception("属性名不存在");
                }
                //从容器中获取bean
                String beanId = toLowerCaseFirstChart(attName);
                Object bean = beans.get(beanId);
                if (ObjectUtils.isNull(bean)) {
                    throw new Exception("class not found");
                }
                //给属性赋值
                field.setAccessible(true);
                field.set(object, bean);
            }
        }

    }

    private String toLowerCaseFirstChart(String className) {
        String firstChar = className.substring(0, 1);
        String substring = className.substring(1);
        return firstChar.toLowerCase() + substring;
    }

}
