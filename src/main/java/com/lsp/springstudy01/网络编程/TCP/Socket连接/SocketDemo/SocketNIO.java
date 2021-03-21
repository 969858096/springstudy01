
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @FileName: SocketNIO
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/3/7 15:32
 */
public class SocketNIO {
    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9898));//绑定端口
        socketChannel.configureBlocking(false);//开启非阻塞模式

        while (true){
            Thread.sleep(1000);
            SocketChannel clientSocketChannel = socketChannel.accept();//非阻塞
            if (clientSocketChannel == null){
                System.out.println("null---");
            }else {
                clientSocketChannel.configureBlocking(false);//获取到客户端的channelSocket
                System.out.println("客户端的端口是："+clientSocketChannel.socket().getPort());
                clients.add(clientSocketChannel);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            for (SocketChannel client : clients) {
                int read = client.read(buffer);//读取客户端传来的数据
                if (read > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println("客户端端口："+client.socket().getPort()+" 接受的数据是："+new String(bytes));
                    buffer.clear();
                }
            }

        }


    }
}
