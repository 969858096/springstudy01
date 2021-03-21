package com.lsp.springstudy01.并发编程.线程池;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.concurrent.*;

/**
 * @FileName: Test2
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/5 21:07
 */
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new MyCallable());
        new Thread(new GetResult(submit.get()));
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "success";
    }
}

class GetResult implements Runnable {

    private String submit;

    public GetResult(String submit) {
        this.submit = submit;
    }

    @Override
    public void run() {
        String s = "";
        while (StringUtils.isNotEmpty(submit)) {
            s = submit;
        }
        System.out.println(s);
    }
}

