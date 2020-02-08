package bean;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FilesMain {
    public static void main(String[] args) throws IOException {

        FileSendedBySocket fileSendedBySocket = new FileSendedBySocket();
        fileSendedBySocket.file = new File("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/src/main/java/HelloWorld.java");
        fileSendedBySocket.fileName = "HelloWorld.java";
        fileSendedBySocket.filePath = "IpGuardKiller/src/main/java/";

        FileSendedBySocket fileSendedBySocket2 = new FileSendedBySocket();
        fileSendedBySocket2.file = new File("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/src/main/java/FilesTraverse.java");
        fileSendedBySocket2.fileName = "FilesTraverse.java";
        fileSendedBySocket2.filePath = "IpGuardKiller/src/main/java/";

        Files files = new Files();
        files.fileSendedBySockets.add(fileSendedBySocket);
        files.fileSendedBySockets.add(fileSendedBySocket2);

        String s = new Gson().toJson(files);
        System.out.println(s);


        Files files1 = new Gson().fromJson(s, Files.class);
        File file = files1.fileSendedBySockets.get(0).file;
        File file2 = files1.fileSendedBySockets.get(1).file;
        System.out.println(file.length());
        System.out.println(file2.length());








        //目标位置。
        FileOutputStream fileOutputStream =
                new FileOutputStream("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/src/main/java/bean/HelloWorld.java");
        FileChannel channel = fileOutputStream.getChannel();
        byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());//数据在这里。
        //向目标位置输出数据。
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        channel.write(wrap);


    }
}
