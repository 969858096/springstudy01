package com.lsp.springstudy01.bean;

import java.lang.String;
import java.lang.Integer;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @FileName: Student
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 07:36
 */
@Component
@Data
public class Student {

    private String id = "123";
    private String name = "lsp";
    private Integer age = 20;

    public Student() {
    }
}
