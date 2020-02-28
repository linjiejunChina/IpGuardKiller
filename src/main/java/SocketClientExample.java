import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import sun.misc.IOUtils;

public class SocketClientExample {
    //    private final static String IPTOLISTEN = "127.0.0.1";
    private final static String IPTOLISTEN = "192.168.31.223";


    static String targetPath = "/Users/linjiejun/Desktop/Outline";
    static String zipFilePath = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc2/OutlineNow.zip";
    static String unzippedFolderPath = "/Users/linjiejun/Desktop/OutlineUnZip";
    static String password = ""; // keep it EMPTY<""> for applying no password protection


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


        FileInputStream zip = Compressor.zip(targetPath, zipFilePath, "");



        byte[] bytes = IOUtils.readNBytes(zip, zip.available());

        zip.close();


        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        System.out.println("buffer.length" + buffer.array().length);
        if (client != null) {
            client.write(buffer);
        }
        buffer.clear();

//        FilesTraverse.client = client;
//        System.out.println("Client... started");
//        FilesTraverse.main(null);
//        client.close();
    }
}

