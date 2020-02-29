package bio;

import java.net.*;
import java.io.*;

import static bio.Utils.FROMFILE;

public class server3 {

    public static void main(String[] args) throws IOException {
        // create socket
        ServerSocket servsock = new ServerSocket(8090);
        while (true) {
            System.out.println("Waiting...");
            Socket sock = servsock.accept();
            System.out.println("Accepted connection : " + sock);
            // sendfile
            File myFile = new File(FROMFILE);
            byte[] mybytearray = new byte[(int) myFile.length()];
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            OutputStream os = sock.getOutputStream();
            System.out.println("Sending...");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            sock.close();
        }
    }

}
