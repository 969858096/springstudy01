package com.lsp.springstudy01.SpringIOC.手写注解版本IOC;

/**
 * @FileName: MyStudentServiceImpl
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 20:20
 */
@MyService
public class MyStudentServiceImpl implements MyStudentService {

    @MyAutowired
    private TestAutowired testAutowired;

    @Override
    public void add() {
        testAutowired.test();
        System.out.println("add.....");
    }
}
