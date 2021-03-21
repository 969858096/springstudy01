package com.lsp.springstudy01.impl;

import com.lsp.springstudy01.bean.Student;
import com.lsp.springstudy01.dao.BankADao;
import com.lsp.springstudy01.dao.BankBDao;
import com.lsp.springstudy01.dao.StudentDao;
import com.lsp.springstudy01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @FileName: StudentServiceImpl
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 19:47
 */
//@Service
//@Primary
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private BankADao bankADao;
    @Autowired
    private BankBDao bankBDao;
    @Override
    public void addStudent(Student student) {
        studentDao.add(student);
    }

    @Override
    public void saveMoney(String userId, Long money) {
        bankADao.deleteMoney(userId, money);
        bankBDao.addMoney(userId,money);
    }

    @Override
    public void saveMoneyToA(String userId, Long money) {
        bankADao.saveMoney(userId,money);
    }

    @Override
    public void addStudentAndMoney(Student student) {
        studentDao.add(student);
        int i = 1/0;
        bankADao.saveMoney(student.getId(),1000L);
    }
}
