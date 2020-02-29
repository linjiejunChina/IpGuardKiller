package bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import bean.FilesSpaceShip;

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


        objIs.close();
        is.close();
        socket.close();
    }

}
