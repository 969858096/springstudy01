package com.lsp.springstudy01.utils;

import cn.hutool.core.lang.UUID;
import com.lsp.springstudy01.bean.Student;

/**
 * @FileName: MyUtils
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/20 07:01
 */
public class MyUtils {

    public static Student createStudent(String name) {
        String id = UUID.randomUUID().toString().substring(0, 5);
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(10);
        return student;
    }

    public static String toLowerCaseFirstChart(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String createId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
