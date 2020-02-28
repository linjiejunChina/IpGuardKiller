import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import sun.misc.IOUtils;

/**
 * 遍历指定路径下的文件夹和文件。
 * 重点就两行。
 * java.nio.file.Files.walkFileTree(PATH, finder);
 * SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){};
 */
public class FilesTraverse {

    public static Path PATH = Paths.get("C:\\Users\\lin\\Desktop\\temp");
    public static Path prePath = Paths.get("C:\\Users\\lin\\Desktop");
//    public static Path PATH = Paths.get("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc");
//    public static Path prePath = Paths.get("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller");

//    public static Path PATH = Paths.get("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/src/main/java");
//    public static Path prePath = Paths.get("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/src/main");

    //    public static Path PATH = Paths.get("D:\\ljj\\iproject\\IpguardKiller\\src\\main\\java");
//    public static Path prePath = Paths.get("D:\\ljj\\iproject\\IpguardKiller\\src\\main");
    static SocketChannel client;

    public static void main(String[] args) throws IOException {

        final List<File> files = new ArrayList<File>();
        final List<File> diractory = new ArrayList<File>();
        final List<String> dirsStrRelativePath = new ArrayList();//文件夹相对路径
        final List<String> filesStrRelativePath = new ArrayList();//文件相对路径
        bean.Files beanFiles = new bean.Files();
        SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                StringBuffer sbf = new StringBuffer();
                for (int i = prePath.getNameCount(); i < dir.getNameCount(); i++) {
                    sbf.append(dir.getName(i).toString() + File.separator);
                }
                dirsStrRelativePath.add(sbf.toString());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {


//                FilesTraverse.FilesShip(beanFiles, file.toFile());


//                StringBuffer sbf = new StringBuffer();
//                for (int i = prePath.getNameCount(); i < file.getNameCount(); i++) {
//                    sbf.append(file.getName(i).toString() + File.separator);
//                }
//                filesStrRelativePath.add(sbf.toString());

//                InetSocketAddress hostAddress = new InetSocketAddress(IPTOLISTEN, 8090);
//                SocketChannel client = SocketChannel.open(hostAddress);

                File f = file.toFile();
                System.out.println("filename:" + f.getName());
                if (f.getName().equals(".DS_Store")) {
                    System.out.println("file is escape");

                    return super.visitFile(file, attrs);
                }
                InputStream is = new FileInputStream(f);

                byte[] bytes = IOUtils.readNBytes(is, is.available());

                is.close();


                ByteBuffer buffer = ByteBuffer.wrap(bytes);
                System.out.println("buffer.length" + buffer.array().length);
                if (client != null) {
                    client.write(buffer);
                }
                buffer.clear();

//                client.close();
//                System.out.println("client-is-connected?" + client.isConnected());


                return super.visitFile(file, attrs);
            }
        };


        //region getcurrent path
        String path = Paths.get("").toAbsolutePath().toString();
        System.out.println("Working Directory = " + path);
        //endregion

        java.nio.file.Files.walkFileTree(PATH, finder);

        //reigon dirToSend
        for (String s : dirsStrRelativePath) {
            String dirToSend = path + File.separator + s;
            System.err.println(s);
        }
        System.out.println("-----========---------=========");
        for (String s : filesStrRelativePath) {
            String filesToSend = path + File.separator + s;
            System.out.println(s);
        }
        //endregion


        if (client != null) {


            ObjectOutputStream oos = new ObjectOutputStream(client.socket().getOutputStream());
            oos.writeObject(beanFiles);
            oos.close();


        }


    }

    private static void createDir(String strPath) {
        try {
            Path newDir = Files.createDirectory(Paths.get(strPath));
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     *
     * @param ship
     * @param fileToSended
     */
//    private static void FilesShip(bean.Files ship, File fileToSended) {
//
//        FileSendedBySocket fileSendedBySocket = new FileSendedBySocket();
//        fileSendedBySocket.setFile(fileToSended);
//        fileSendedBySocket.setFileName(fileToSended.getName());
//        fileSendedBySocket.setFilePath("java");
//
//
//        ship.getFileSendedBySockets().add(fileSendedBySocket);
//
//    }


}
