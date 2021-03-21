package com.lsp.springstudy01.spring事务.编程式事务;

import com.lsp.springstudy01.service.StudentService;
import com.lsp.springstudy01.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: MyTranction 使用注解的声明式事务
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/20 06:55
 */
//@Component
//@RestController
//@RequestMapping("CodeTrancationController")
@Slf4j
public class CodeTrancationController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private MyTranctionalUtil tranctionalUtil;


    @RequestMapping("test")
    public String test() {
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = tranctionalUtil.begin();
            studentService.addStudent(MyUtils.createStudent("lsp"));
            int i = 1 / 0;
            studentService.addStudent(MyUtils.createStudent("zl"));
            tranctionalUtil.commit(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
            tranctionalUtil.rollBack(transactionStatus);
        }
        return "success";
    }

    @RequestMapping("test1")
    public String test1() {
        studentService.addStudent(MyUtils.createStudent("lsp"));
        int i = 1 / 0;
        studentService.addStudent(MyUtils.createStudent("ls"));
        return "success";
    }


}
