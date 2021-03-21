package com.lsp.springstudy01.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsp.springstudy01.bean.Student;
import com.lsp.springstudy01.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @FileName: StudentDao
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 08:32
 */
//@Repository
public class StudentDao{

    @Autowired
    private StudentMapper studentMapper;


    public void add(Student student) {
        studentMapper.add(student);
    }


    public void delete(String id) {

    }

    public Student get(String id) {
        return null;
    }
}
