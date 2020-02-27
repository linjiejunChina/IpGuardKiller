import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientExample {
//    private final static String IPTOLISTEN = "127.0.0.1";
    private final static String IPTOLISTEN = "192.168.31.223";

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startClient();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    public static void startClient() throws IOException, InterruptedException {
        InetSocketAddress hostAddress = new InetSocketAddress(IPTOLISTEN, 8090);
        SocketChannel client = SocketChannel.open(hostAddress);
        FilesTraverse.client = client;
        System.out.println("Client... started");
        FilesTraverse.main(null);
        client.close();
    }
}

