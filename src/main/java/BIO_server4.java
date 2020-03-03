import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;

import bean.FilesSpaceShip;
import bean.SpaceShipPassenger;

public class BIO_server4 {

    private static int port;
    private static String to;

    //todo 如果没有带参数，则接下来就进行参数输入要求。
    //校验输入，不能带引号。
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        userInputTips();
        Common.verifyArgs(args, " port pathToStoreFile  in sequence");
        turnArgsIntoField(args);

        Socket socket = initServerListener(port);System.out.println("client connected");

        long launchTime = System.nanoTime();   //获取开始时间
        InputStream is = socket.getInputStream();
        ObjectInputStream objIs = new ObjectInputStream(is);

        FilesSpaceShip ship = (FilesSpaceShip) objIs.readObject();
        long flytime = System.nanoTime();

        long fileSize = spaceShipLanding(ship);System.out.println(ship.hashCode());

        long landingTime = System.nanoTime(); //获取结束时间

        long netTime = flytime - launchTime;
        long fileCreateTime = landingTime - flytime;

        System.out.println(MessageFormat.format("网络使用时间:{0} 创建文件时间:{1}"
                , nano2Second(netTime), nano2Second(fileCreateTime)));
        System.out.println(MessageFormat.format("程序大小:{0}"
                , kb2Mb(fileSize)));
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


    private static void turnArgsIntoField(String[] args) {
        port = Integer.parseInt(args[0]);
        to = args[1];
    }

    private static String userInputTips() {
        String s1 = "请输入：" + "/r/n";
        String s2 = "端口号，存储文件路径" + "/r/n";
        String s3 = "不加引号" + "/r/n";
        return s1 + s2 + s3;
    }

    private static long spaceShipLanding(FilesSpaceShip ship) throws IOException {
        long fileSize = 0;
        for (SpaceShipPassenger fileSendedBySocket : ship.getFileSendedBySockets()) {
            System.out.println(fileSendedBySocket.getFileData().length);
            File file = new File(receiveSizeAbsPath(to, fileSendedBySocket.getFilePath()));
            createFileOrDirectoryIfNeed(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(fileSendedBySocket.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();
            fileSize += fileSendedBySocket.getFileData().length;
        }
        return fileSize;
    }

    private static long nano2Second(long nano) {
        return (nano) / 1000 / 1000 / 1000;
    }

    private static long kb2Mb(long kb) {
        return kb / 1024 / 1024;
    }


    private static Socket initServerListener(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println(Inet4Address.getLocalHost().getHostAddress()+" Server_started...");
        return serverSocket.accept();
    }

    private static void create_tmp_Dir4SvnNeed() {

    }
}
