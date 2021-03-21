package com.lsp.springstudy01.spring事务.声明式事务;

import com.lsp.springstudy01.service.StudentService;
import com.lsp.springstudy01.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
public class MyTrancationController {
    @Autowired
    private StudentService studentService;

    @Transactional
    @RequestMapping("test")
    public String test(){
        studentService.addStudent(MyUtils.createStudent("lsp"));
        studentService.addStudent(MyUtils.createStudent("zl"));
        return  "success";
    }


}
