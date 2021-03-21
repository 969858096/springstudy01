

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @FileName: ServerSocket
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/3/7 09:02
 *////usr/lib/jvm/java-1.8.0-openjdk-1.8.0.282.b08-1.el7_9.x86_64

public class ServerSocketTest {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket socket = serverSocket.accept();//内核阻塞
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream is = null;
                    try {
                        is = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                        while (true) {
                            String message = bufferedReader.readLine();
                            if (message != null){
                                System.out.println(message);
                            }else {
                                socket.close();
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
