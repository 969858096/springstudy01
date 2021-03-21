package com.lsp.springstudy01.网络编程.TCP.文件上传;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @FileName: FileUploadServer
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/2/28 09:46
 */
public class FileUploadServer {
    public static void main(String[] args) throws IOException {
        //服务器保持连接
        ServerSocket serverSocket = new ServerSocket(6666);
        for (;;){
            Executors.newCachedThreadPool().submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Socket accept = serverSocket.accept();
                        InputStream is = accept.getInputStream();
                        File file = new File("F:\\冲刺2021\\test");
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        String fileName = "test"+System.currentTimeMillis()+new Random().nextInt(99999) +".txt";
                        FileOutputStream fileOutputStream = new FileOutputStream(file + "\\" + fileName);
                        int length = 0;
                        byte[] bytes = new byte[1024];
                        while ((length = is.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0, length);
                        }
                        OutputStream os = accept.getOutputStream();
                        os.write("上传成功".getBytes("UTF-8"));
                        accept.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
