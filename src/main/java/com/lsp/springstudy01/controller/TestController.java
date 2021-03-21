package com.lsp.springstudy01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: TestController
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/23 21:51
 */
@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping("/jmeter")
    public String test() throws InterruptedException {
        Object o = new Object();
        Object o1 = new Object();
        MyThread myThread = new MyThread(o);
        MyThread myThread1 = new MyThread(o1);
        Thread thread1 = new Thread(myThread, "thread1");
        Thread thread2 = new Thread(myThread1, "thread2");
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        return "test";
    }


}

class MyThread implements Runnable {
    private Object obj;


    public MyThread(Object obj) {
        this.obj = obj;

    }

    @Override
    public void run() {
            test1();

    }


    private synchronized void test1() {
        synchronized (obj) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2222");
        }
    }
}
