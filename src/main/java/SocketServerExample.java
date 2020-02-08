import bean.Files;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class SocketServerExample {

    private final static String IPTOLISTEN = "192.168.31.223";
    private Selector selector;
    private Map<SocketChannel, List<byte[]>> dataMapper;
    private InetSocketAddress listenAddress;

    private StringBuilder dataFromNet = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Runnable server = new Runnable() {
            @Override
            public void run() {
                try {
                    new SocketServerExample(IPTOLISTEN, 8090).startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

//		Runnable client = new Runnable() {
//			@Override
//			public void run() {
//				 try {
//					 new SocketClientExample().startClient();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//
//			}
//		};
        new Thread(server).start();
//       new Thread(client, "client-A").start();
//       new Thread(client, "client-B").start();

    }

    public SocketServerExample(String address, int port) throws IOException {
        listenAddress = new InetSocketAddress(address, port);
        dataMapper = new HashMap<SocketChannel, List<byte[]>>();
    }

    // create server channel	
    private void startServer() throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // retrieve server socket and bind to port
        serverChannel.socket().bind(listenAddress);
        serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started...");

        d:
        while (true) {
            // wait for events
            this.selector.select();

            //work on selected keys
            Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();

                // this is necessary to prevent the same key from coming up 
                // again the next time around.
                keys.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    this.accept(key);
                } else if (key.isReadable()) {
                    System.out.println("key.isReadable()");
                    this.read(key);
                }
            }
        }
    }

    //accept a connection made to this channel's socket
    private void accept(SelectionKey key) throws IOException {
//        System.out.println("key:"+key.channel().isBlocking());
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        System.out.println("Connected to client: " + remoteAddr + " local address" + socket.getLocalSocketAddress());

        // register channel with selector for further IO
        dataMapper.put(channel, new ArrayList<byte[]>());
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    //read from the socket channel
    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int numRead = -1;
        numRead = channel.read(buffer);//读取channel数据到buffer

        if (numRead == -1) {
            this.dataMapper.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteAddr);
            channel.close();
            key.cancel();

            System.err.println(dataFromNet.toString());
            Files files = new Gson().fromJson(dataFromNet.toString(), Files.class);

            for (int i = 0; i < files.getFileSendedBySockets().size(); i++) {
                File ff = files.getFileSendedBySockets().get(i).getFile();
                System.out.println("receptfile:" + ff.length() + ff.getName());
            }


            return;
        }

        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
        dataFromNet.append(new String(data));
        System.out.println("Got: " + new String(data));
    }
}