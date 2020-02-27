package bio;

import java.io.*;
import java.net.Socket;

import static bio.Utils.FROMFILE;

public class client4 {
    public static void main(String[] args) throws IOException{
        {
//            BufferedReader br = null;
            FileInputStream fis = null;
            try {
                Socket echoSocket = new Socket("127.0.0.1", 5223);
                System.out.println("Connected to server");
                File f =new  File(FROMFILE);
                FileReader r = new FileReader(f);
                String s = f.getName();
                long fs = f.length();
                System.err.println("file length "+fs);
                OutputStream os = echoSocket.getOutputStream();
                fis =new FileInputStream(f);
//                br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                int j;
                while((j=fis.read())!=-1){
                    os.write(j);
                }
                 
            } catch (FileNotFoundException ex) {
                 
            } finally {
            }
        }
             
    }            
   
}