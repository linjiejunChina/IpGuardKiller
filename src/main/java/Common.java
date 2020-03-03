import java.nio.file.Path;
import java.nio.file.Paths;

public class Common {
//    public final static String IPTOLISTEN = "192.168.31.223";//house wifi
    public  static String IPTOLISTEN = "192.168.2.1";//compony mac ether

    public  static int PORT= 8090;


    public static Path PATH = Paths.get("D:\\ljj\\npt\\source\\sdkservice\\");
//    public static String HEAD_OF_PATH_TO_CUT_OFF = "D:\\ljj\\npt\\source\\";


    public static String FileToSKip = ".DS_Store";

    public static String PathToStoreDirectory = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/";


    public static void verifyArgs(String[] args,String hintIfFailed) {
        if (args == null || args.length < 1) {
            System.out.println("args is null ");
            System.out.println(hintIfFailed);
            return;
        }
    }

}
