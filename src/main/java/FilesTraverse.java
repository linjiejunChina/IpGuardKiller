import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
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

import bean.SpaceShipPassenger;
import bean.FilesSpaceShip;
import sun.misc.IOUtils;

import static java.nio.file.Files.walkFileTree;

/**
 * 遍历指定路径下的文件夹和文件。
 * 重点就两行。
 * java.nio.file.FilesSpaceShip.walkFileTree(PATH, finder);
 * SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){};
 */
public class FilesTraverse {
    private static String HEAD_OF_PATH_TO_CUT_OFF = "";
    static SocketChannel client;

    public static void main(String[] args) throws IOException {

        FilesSpaceShip fullLoad = getSpaceShipWithFullLoad();
        FireTheSpaceShip(fullLoad);

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
     * @param spaceShip
     * @param file
     */
    private static void LoadFileToFilesSpaceShip(FilesSpaceShip spaceShip, File file) throws IOException {

        SpaceShipPassenger passenger = new SpaceShipPassenger();
        passenger.setFile(file);
        passenger.setFileName(file.getName());
        passenger.setFilePath(getInterestFilePath(file.getCanonicalPath(),HEAD_OF_PATH_TO_CUT_OFF));
        passenger.setFileData(suckBytesFromFile(file));
        spaceShip.getFileSendedBySockets().add(passenger);
    }


    /**
     * 遍历指定文件夹，将所有文件转载到 FilesSpaceShip
     *
     * @return 转载了文件序列化到SpaceShip
     */
    private static FilesSpaceShip getSpaceShipWithFullLoad() throws IOException {
        FilesSpaceShip spaceShip1 = new FilesSpaceShip();
        SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                StringBuffer sbf = new StringBuffer();
//                for (int i = Common.PREPATH.getNameCount(); i < dir.getNameCount(); i++) {
//                    sbf.append(dir.getName(i).toString() + File.separator);
//                }
//            dirsStrRelativePath.add(sbf.toString());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println(getInterestFilePath(file.toFile().getCanonicalPath(), HEAD_OF_PATH_TO_CUT_OFF));
                FilesTraverse.LoadFileToFilesSpaceShip(spaceShip1, file.toFile());
                return super.visitFile(file, attrs);
            }
        };
        walkFileTree(Common.PATH, finder);
        return spaceShip1;
    }


    /**
     * 发送序列化对象集
     * 🔥🔥🔥🔥🔥🔥🔥🔥
     *
     * @throws IOException
     */
    private static void FireTheSpaceShip(FilesSpaceShip obj) throws IOException {
        Socket sock = new Socket(Common.IPTOLISTEN, Common.PORT);
        ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
        System.out.println(obj.hashCode() + "hashCode is client-sender");
        System.out.println(obj.getFileSendedBySockets().size() + "this is send size");
        oos.writeObject(obj);
        oos.close();
        System.out.println("Connection ended");
    }


    private static byte[] suckBytesFromFile(File file) throws IOException {
        BufferedInputStream bferIs = new BufferedInputStream(new FileInputStream(file));
        byte[] transferStation = new byte[((int) file.length())];
        bferIs.read(transferStation, 0, transferStation.length);
        return transferStation;
    }

    private static String getInterestFilePath(String originPath,String headOfPathToCutOff) {
        if (!headOfPathToCutOff.endsWith(File.separator)) {
            headOfPathToCutOff += File.separator;
        }
        return originPath.replace(headOfPathToCutOff, "");
    }

}
