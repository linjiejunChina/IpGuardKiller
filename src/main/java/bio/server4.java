package bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static bio.Utils.TOFILE;

public class server4 {
    
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(5223);
            Socket client = serverSocket.accept();
            System.out.println("Connected to client");
            InputStream dis = client.getInputStream();
            System.err.println("read long "+dis.available());
            File nf = new File(TOFILE);
            OutputStream bos = new FileOutputStream(nf);
            for(int i =0;i<dis.available();i++){
            int j;
            while((j=dis.read())!=-1){
                bos.write(j);
            }
          }
        } catch (IOException ex) {
        }
      
       
           }
         
    }
