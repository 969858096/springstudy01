package com.lsp.springstudy01.网络编程.TCP.Socket连接;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @FileName: TCPServer
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/2/27 20:43
 */
public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket accept = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(8888);
            accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            String s = new String(bytes, 0, read);
            System.out.println(s);

            String str = "你好客户端";
            outputStream = accept.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                accept.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
