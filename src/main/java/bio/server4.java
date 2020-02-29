package bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import bean.FilesSpaceShip;
import bean.SpaceShipPassenger;

import static bio.Utils.TOFILE;

public class server4 {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        ServerSocket serverSocket = new ServerSocket(8090);
        Socket socket = serverSocket.accept();System.out.println("Connected to client");

        InputStream is = socket.getInputStream();System.out.println(is.available());
        ObjectInputStream objIs = new ObjectInputStream(is);

        FilesSpaceShip ship = (FilesSpaceShip) objIs.readObject();
        System.out.println(ship.getFileSendedBySockets().size() + "recive spaceShip conpactor is ");
        System.out.println(ship.hashCode() + "server-hash code is ");

        for (SpaceShipPassenger fileSendedBySocket : ship.getFileSendedBySockets()) {

            System.out.println(fileSendedBySocket.getFileData().length);
            File file = new File(receiveSizeAbsPath(Common.PathToStoreDirectory,fileSendedBySocket.getFileName()));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(fileSendedBySocket.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        objIs.close();
        is.close();
        socket.close();
    }


    private static String receiveSizeAbsPath(String prePath, String directory) {
        if (!prePath.endsWith("/")) {
            prePath = prePath + "/";
        }
        String s = prePath + directory;
        System.out.println(s);
        return s;
    }


}
