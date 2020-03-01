import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import bean.FilesSpaceShip;
import bean.SpaceShipPassenger;

public class BIO_server4 {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        long FileSize = 0;
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("Server_started...");
        Socket socket = serverSocket.accept();
        System.out.println("Connected to client");

        long startTime = System.nanoTime();   //获取开始时间
        InputStream is = socket.getInputStream();
        ObjectInputStream objIs = new ObjectInputStream(is);

        FilesSpaceShip ship = (FilesSpaceShip) objIs.readObject();
        System.out.println(ship.getFileSendedBySockets().size() + "recive spaceShip conpactor is ");
        System.out.println(ship.hashCode() + "server-hash code is ");

        for (SpaceShipPassenger fileSendedBySocket : ship.getFileSendedBySockets()) {
            System.out.println(fileSendedBySocket.getFileData().length);
            File file = new File(receiveSizeAbsPath(Common.PathToStoreDirectory, fileSendedBySocket.getFilePath()));
            createFileOrDirectoryIfNeed(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(fileSendedBySocket.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();
            FileSize += fileSendedBySocket.getFileData().length;
        }
        long endTime = System.nanoTime(); //获取结束时间
        System.out.println((endTime - startTime) + "nanoTime");
        System.out.println("程序运行时间： " + (endTime - startTime) / 1000 / 1000 / 1000 + "seconds");
        System.out.println("程序大小" + FileSize / 1024 / 1024 + "MB");
        objIs.close();
        is.close();
        socket.close();
    }


    private static String receiveSizeAbsPath(String prePath, String directory) {
        if (!prePath.endsWith("/")) {
            prePath = prePath + "/";
        }
        String s = prePath + directory;
        s = s.replace("\\", "/");
        System.out.println(s);
        return s;
    }

    private static void createFileOrDirectoryIfNeed(File file) throws IOException {

        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
//        if (!file.exists())
//            file.createNewFile();

    }

}
