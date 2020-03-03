import org.junit.Test;

import static org.junit.Assert.*;

public class FilesTraverseTest {

    @Test
    public void getInterestFilePathNormalMac() {
        assertEquals("doc/test.txt",
                FilesTraverse.getInterestFilePath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/test.txt"
                        ,PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc")));
    }
    @Test
    public void getInterestFilePathNormalMac2() {
        assertEquals("doc/test.txt",
                FilesTraverse.getInterestFilePath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/test.txt"
                        ,PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/")));
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void getInterestFilePathNormalWin() {
        assertEquals("sdkservice\\test.txt",
                FilesTraverse.getInterestFilePath("D:\\ljj\\npt\\source\\sdkservice\\test.txt"
                        ,PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source\\sdkservice")));
    }
    /**
     * faild in mac
     * success in win
     */
    @Test
    public void getInterestFilePathNormalWin2() {
        assertEquals("sdkservice\\test.txt",
                FilesTraverse.getInterestFilePath("D:\\ljj\\npt\\source\\sdkservice\\test.txt"
                        ,"D:\\ljj\\npt\\source\\"));
    }
    /**
     * faild in mac
     * success in win
     */
    @Test
    public void stringReplaceWin() {
        assertEquals("sdkservice\\test.txt",
                "D:\\ljj\\npt\\source\\sdkservice\\test.txt".replace("D:\\ljj\\npt\\source\\", ""));
    }
}