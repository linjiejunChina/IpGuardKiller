import bean.FilesSpaceShip;
import bean.SpaceShipPassenger;

import java.io.*;
import java.net.Socket;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.walkFileTree;

/**
 * 遍历指定路径下的文件夹和文件。
 * 重点就两行。
 * java.nio.file.FilesSpaceShip.walkFileTree(PATH, finder);
 * SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){};
 */
public class BIO_Client4 {


    private static int port;
    private static String ip;
    private static String from;


    public static void main(String[] args) throws IOException {
        Common.verifyArgs(args, "ip port pathToStoreFile in sequence");
        turnArgsIntoField(args);
        FilesSpaceShip fullLoad = getSpaceShipWithFullLoad();
        FireTheSpaceShip(fullLoad);
    }


    /**
     * @param spaceShip
     * @param file
     */
    private static void LoadFileToFilesSpaceShip(FilesSpaceShip spaceShip, File file) throws IOException {

        SpaceShipPassenger passenger = new SpaceShipPassenger();
        passenger.setFile(file);
        passenger.setFileName(file.getName());
        passenger.setFilePath(getInterestFilePath(file.getCanonicalPath(), PathHelper.exclueLastDirInPath(from)));
        System.out.println("Interest Path is " + passenger.getFilePath());
        passenger.setDirectory(file.isDirectory());
        if (!file.isDirectory()) {
            passenger.setFileData(suckBytesFromFile(file));
        }
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
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                BIO_Client4.LoadFileToFilesSpaceShip(spaceShip1,dir.toFile());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                BIO_Client4.LoadFileToFilesSpaceShip(spaceShip1, file.toFile());
                return super.visitFile(file, attrs);
            }
        };
        walkFileTree(Paths.get(from), finder);
        return spaceShip1;
    }


    /**
     * 发送序列化对象集
     * 🔥🔥🔥🔥🔥🔥🔥🔥
     *
     * @throws IOException
     */
    private static void FireTheSpaceShip(FilesSpaceShip obj) throws IOException {
        Socket sock = new Socket(ip, port);
        ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
        System.out.println("hashCode is client-sender" + obj.hashCode());
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

    static String getInterestFilePath(String originPath, String headOfPathToCutOff) {
        if (!headOfPathToCutOff.endsWith(File.separator)) {
            headOfPathToCutOff += File.separator;
        }
        return originPath.replace(headOfPathToCutOff, "");
    }

    private static void turnArgsIntoField(String[] args) {
        ip = args[0];
        port = Integer.parseInt(args[1]);
        from = args[2];
    }
}
