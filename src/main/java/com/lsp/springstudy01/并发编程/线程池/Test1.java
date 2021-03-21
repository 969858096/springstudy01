package com.lsp.springstudy01.并发编程.线程池;

/**
 * @FileName: Test1
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/5 20:45
 */
public class Test1 {
    public static void main(String[] args) {

    }

}

class MyThread1 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("我是其他线程");
        }
    }
}
