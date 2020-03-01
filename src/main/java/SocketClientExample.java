import java.io.IOException;

public class SocketClientExample {
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
        System.out.println("Client... started");
        FilesTraverse.main(null);
    }
}

