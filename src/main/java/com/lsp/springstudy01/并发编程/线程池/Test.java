package com.lsp.springstudy01.并发编程.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: Test
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/5 08:20
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS
                , new LinkedBlockingDeque<>(3));
        threadPoolExecutor.execute(new MyThread("任务一"));
        threadPoolExecutor.execute(new MyThread("任务二"));
        threadPoolExecutor.execute(new MyThread("任务三"));
        threadPoolExecutor.execute(new MyThread("任务四"));
        threadPoolExecutor.execute(new MyThread("任务五"));
        threadPoolExecutor.execute(new MyThread("任务六"));


    }


    static class MyThread implements Runnable {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"---"+name);
        }
    }
}
