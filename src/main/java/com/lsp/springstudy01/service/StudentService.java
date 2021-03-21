package com.lsp.springstudy01.service;

import com.lsp.springstudy01.bean.Student;
import org.springframework.stereotype.Service;

//@Service
public interface StudentService {
    void addStudent(Student student);

    void saveMoney(String userId,Long money);

    void saveMoneyToA(String userId,Long money);

    void addStudentAndMoney(Student student);

}
