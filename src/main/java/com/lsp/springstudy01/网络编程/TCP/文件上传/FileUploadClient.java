package com.lsp.springstudy01.网络编程.TCP.文件上传;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @FileName: FileUpload
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/2/28 09:28
 */
public class FileUploadClient {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\冲刺2021\\test.txt");
        Socket socket = new Socket("localhost", 6666);
        OutputStream os = socket.getOutputStream();

        int lent = 0;
        byte[] bytes = new byte[1024];
        while ((lent = fileInputStream.read(bytes)) != -1) {
            os.write(bytes, 0, lent);
        }
        //终止socket
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        int redLength = 0;
        while ((redLength = is.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, redLength));
        }


        fileInputStream.close();
        socket.close();
    }
}
