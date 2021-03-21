package com.lsp.springstudy01.网络编程.TCP.Socket连接;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @FileName: TCPClient
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/2/27 20:43
 */
public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            socket = new Socket("localhost", 8888);
            os = socket.getOutputStream();
            os.write("你好服务端".getBytes("utf-8"));

            byte[] bytes = new byte[1024];
            int read = socket.getInputStream().read();
            System.out.println(new String(bytes,0,read));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
