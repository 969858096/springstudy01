package com.lsp.springstudy01.SpringIOC.手写xml版本IOC;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: MyClassPathXmlApplicationContext
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 16:09
 */
@Slf4j
public class MyClassPathXmlApplicationContext {

    private String xmlPath;
    private static ConcurrentHashMap<String, Object> beans = null;

    public MyClassPathXmlApplicationContext(String xmlPathCome) {
        beans = new ConcurrentHashMap<>();//初始化容器
        this.xmlPath = xmlPathCome;
    }


    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)){
            throw new Exception("id不能为空");
        }
        return initBean(beanId);
    }

    private Object initBean(String beanId) throws Exception {
        //解析xml
        Element targetElement = loadXml(beanId);
        //获取并存储目标节点
        initTargetObject(targetElement, beanId);
        //初始化对象
        Object resultObject = beans.get(beanId);
        if (ObjectUtils.isNotNull(resultObject)) {
            return resultObject;
        }
        return new Exception("class not found!");
    }

    private Element loadXml(String beanId) throws Exception {
        InputStream inputStream = loadClassXml(xmlPath);
        Element rootElement = getRootElement(inputStream);
        return getTargetElement(rootElement, beanId);
    }

    private void initTargetObject(Element element, String beanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classPath = element.attributeValue("class");
        //反射获取class对象存进maP
        Class<?> beanClass = Class.forName(classPath);
        beans.put(beanId, beanClass.newInstance());
    }

    private InputStream loadClassXml(String xmlPath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        if (ObjectUtils.isNull(inputStream)){
            log.error("！！未找到xml文件");
        }
        return inputStream;
    }

    public Element getRootElement(InputStream inputStream) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(inputStream);
        Element rootElement = read.getRootElement();
        if (ObjectUtils.isNull(rootElement)){
            throw new Exception("该xml不存在根节点");
        }
        return rootElement;
    }

    public Element getTargetElement(Element rootElement, String beanId) throws Exception {
        Element next = null;
        Iterator<Element> elementIterator = rootElement.elementIterator();//子节点迭代器
        //递归遍历子节点
        while (elementIterator.hasNext()) {
            next = elementIterator.next();
            //获取当前节点的id所对应的值
            String value = next.attributeValue("id");
            if (StringUtils.isEmpty(value)){
                throw new Exception("节点id不能为空");
            }
            if (beanId.equals(value)){
                return next;
            }
        }
        throw new Exception("目标对象不存在");
    }

}
