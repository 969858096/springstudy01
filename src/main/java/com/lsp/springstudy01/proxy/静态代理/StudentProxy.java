package com.lsp.springstudy01.proxy.静态代理;

import com.lsp.springstudy01.bean.Student;
import com.lsp.springstudy01.mapper.StudentMapper;
import com.lsp.springstudy01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @FileName: StudentProxy
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 20:37
 */
//@Component
public class StudentProxy implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        System.out.println("代理前....");
        studentMapper.add(student);
        System.out.println("代理后...");
    }

    @Override
    public void saveMoney(String userId, Long money) {

    }

    @Override
    public void saveMoneyToA(String userId, Long money) {

    }

    @Override
    public void addStudentAndMoney(Student student) {

    }

}
