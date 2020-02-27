import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SocketServerExample {

//    private final static String IPTOLISTEN = "127.0.0.1";
        private final static String IPTOLISTEN = "192.168.31.223";
    private Selector selector;
    private Map<SocketChannel, List<byte[]>> dataMapper;
    private InetSocketAddress listenAddress;
    private byte[] combineByte = new byte[0];
    private StringBuilder dataFromNet = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Runnable server = new Runnable() {
            @Override
            public void run() {
                try {
                    new SocketServerExample(IPTOLISTEN, 8090).startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
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
    private void startServer() throws IOException, ClassNotFoundException {
        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // retrieve server socket and bind to port
        serverChannel.socket().bind(listenAddress);
        serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started...");

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
    private void read(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel channel = (SocketChannel) key.channel();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                ObjectInputStream ois = null;
//                try {
//                    ois = new ObjectInputStream(channel.socket().getInputStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                String s = null;
//                try {
//                    s = (String) ois.readObject();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(s);
//            }
//        }).start();


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
//            System.out.println("combineByteLength"+combineByte.length);
//            System.out.println("Got: " + new String(combineByte));

            File file = new File(
                    "/Users/linjiejun/Documents/linwork/iproject/java/" +
                            "IpGuardKiller/doc2/Printer-master-net.7z");
            FileOutputStream fos = new FileOutputStream(file);
//            byte[] outByte = Arrays.copyOfRange(combineByte, 0, combineByte.length - 126);
            byte[] outByte = this.combineByte;

            fos.write(outByte);
            fos.flush();
            fos.close();
            System.out.println(outByte.length+"server_file_byte_size");
            this.combineByte = new byte[0];
            return;
        }

        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
//        dataFromNet.append(new String(data));
//        System.out.println("Got: " + new String(data));

        combineByte = Utils.combineByteArrays(combineByte, data);
    }
}