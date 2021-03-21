package com.lsp.springstudy01.网络编程.TCP.Socket连接.SocketDemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @FileName: ClientSocketTest
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/3/7 09:07
 */
public class ClientSocketTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello world".getBytes());
        socket.close();
        outputStream.close();
    }
}
