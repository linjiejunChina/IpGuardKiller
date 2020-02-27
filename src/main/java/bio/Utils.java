package bio;

public class Utils {

    /**
     * 返回一个新的字节数组
     * @param b1
     * @param b2
     * @return
     */
    public static byte[] combineByteArrays(byte[] b1, byte[] b2) {
        if (b1 == null || b2 == null) {
            return null;
        }
        byte[] combined = new byte[b1.length + b2.length];
        for (int i = 0; i < combined.length; ++i) {
            combined[i] = i < b1.length ? b1[i] : b2[i - b1.length];
        }
        return combined;
    }

//    public static final String FROMFILE = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/test.txt.zip";
//    public static final String TOFILE = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc2/test2.txt.zip";

//    public static final String FROMFILE = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/test.txt";
//    public static final String TOFILE = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc2/test2.txt";

    public static final String FROMFILE = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/Printer-master.7z";
    public static final String TOFILE = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc2/Printer-master-net.7z";
}
