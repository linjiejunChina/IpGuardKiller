import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathHelperTest {

    PathHelper pathHelper;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void lastDirInPath_Normal_mac() {
        assertEquals("doc"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc")
        );

    }

    @Test
    public void lastDirInPath_Normal_mac2() {
        assertEquals("doc123"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller123/doc123")
        );

    }

    @Test
    public void lastDirInPath_Normal_mac3() {
        assertEquals("doc.123"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller.123/doc.123")
        );
    }

    @Test
    public void lastDirInPath_Normal_mac_separator_end() {
        assertEquals("doc"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/")
        );

    }

    @Test
    public void lastDirInPath_Normal_mac_separator_end2() {
        assertEquals("doc123"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller123/doc123/")
        );

    }

    @Test
    public void lastDirInPath_Normal_mac_separator_end3() {
        assertEquals("doc.123"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller.123/doc.123/")
        );

    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win() {
        assertEquals("sdkservice"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source\\sdkservice")
        );
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win_separator_end() {
        assertEquals("sdkservice"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source\\sdkservice\\")
        );
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win2() {
        assertEquals("sdkservice123"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source123\\sdkservice123")
        );
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win_separator_end2() {
        assertEquals("sdkservice123"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source123\\sdkservice123\\")
        );
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win3() {
        assertEquals("sdkservice.123"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source.123\\sdkservice.123")
        );
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win_separator_end3() {
        assertEquals("sdkservice.123"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source.123\\sdkservice.123\\")
        );
    }

    @Test
    public void lastDirInPath_Null() {
        assertEquals(""
                , PathHelper.lastDirInPath(null)
        );
    }

    @Test
    public void lastDirInPath_Empty() {
        assertEquals(""
                , PathHelper.lastDirInPath("")
        );
    }

    //////////
    @Test
    public void exclueLastDirInPath_Normal_mac() {
        assertEquals("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/"
                , PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc")
        );
    }

    @Test
    public void exclueLastDirInPath_Normal_mac_separator_end() {
        assertEquals("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/"
                , PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/")
        );
    }

    @Test
    public void exclueLastDirInPath_Normal_mac2() {
        assertEquals("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller123/"
                , PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller123/doc123")
        );
    }

    @Test
    public void exclueLastDirInPath_Normal_mac_separator_end2() {
        assertEquals("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller123/"
                , PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller123/doc123/")
        );
    }

    @Test
    public void exclueLastDirInPath_Normal_mac3() {
        assertEquals("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller.123/"
                , PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller.123/doc.123")
        );
    }

    @Test
    public void exclueLastDirInPath_Normal_mac_separator_end3() {
        assertEquals("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller.123/"
                , PathHelper.exclueLastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller.123/doc.123/")
        );
    }

    /**
     * faild in mac
     * success in win
     */
    @Test
    public void exclueLastDirInPath_Normal_win() {
        assertEquals("D:\\ljj\\npt\\source\\"
                , PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source\\sdkservice"));
    }


    /**
     * faild in mac
     * success in win
     */
    @Test
    public void exclueLastDirInPath_Normal_win_separator_end() {
        assertEquals("D:\\ljj\\npt\\source\\"
                , PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source\\sdkservice\\"));
    }


    /**
     * faild in mac
     * success in win
     */
    @Test
    public void exclueLastDirInPath_Normal_win2() {
        assertEquals("D:\\ljj\\npt\\source123\\"
                , PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source123\\sdkservice123"));
    }


    /**
     * faild in mac
     * success in win
     */
    @Test
    public void exclueLastDirInPath_Normal_win_separator_end2() {
        assertEquals("D:\\ljj\\npt\\source123\\"
                , PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source123\\sdkservice123\\"));
    }


    /**
     * faild in mac
     * success in win
     */
    @Test
    public void exclueLastDirInPath_Normal_win3() {
        assertEquals("D:\\ljj\\npt\\source.123\\"
                , PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source.123\\sdkservice.123"));
    }


    /**
     * faild in mac
     * success in win
     */
    @Test
    public void exclueLastDirInPath_Normal_win_separator_end3() {
        assertEquals("D:\\ljj\\npt\\source.123\\"
                , PathHelper.exclueLastDirInPath("D:\\ljj\\npt\\source.123\\sdkservice.123\\"));
    }


    @Test
    public void exclueLastDirInPath_Null() {
        assertEquals(""
                , PathHelper.lastDirInPath(null)
        );
    }

    @Test
    public void exclueLastDirInPath_Empty() {
        assertEquals(""
                , PathHelper.lastDirInPath("")
        );
    }

//isWinFileSystem


    @Test
    public void isWinFileSystemNull() {
        assertFalse(PathHelper.isWinFileSystem(null));
    }

    @Test
    public void isWinFileSystemEmpty() {
        assertFalse(PathHelper.isWinFileSystem(""));
    }

    @Test
    public void isWinFileSystemNormal() {
        assertTrue(PathHelper.isWinFileSystem("D:\\ljj\\npt\\source.123\\"));
    }

    @Test
    public void isWinFileSystemNormal2() {
        assertTrue(PathHelper.isWinFileSystem("D:\\ljj\\npt\\source.123"));
    }

    @Test
    public void isWinFileSystemFreak() {
        assertFalse(PathHelper.isWinFileSystem("D:\\ljj\\npt/source.123\\"));
    }


    //isUnixLikeFileSystem


    @Test
    public void isUnixLikeFileSystemNull() {
        assertFalse(PathHelper.isUnixLikeFileSystem(null));
    }

    @Test
    public void isUnixLikeFileSystemEmpty() {
        assertFalse(PathHelper.isUnixLikeFileSystem(""));
    }

    @Test
    public void isUnixLikeFileSystemNormal() {
        assertTrue(PathHelper.isUnixLikeFileSystem("/Users/linjiejun/Documents/"));
    }

    @Test
    public void isUnixLikeFileSystemNormal2() {
        assertTrue(PathHelper.isUnixLikeFileSystem("/Users/linjiejun/Documents"));
    }

    @Test
    public void isUnixLikeFileSystemFreak() {
        assertFalse(PathHelper.isUnixLikeFileSystem("/Users/linjiejun/Documents\\/"));
    }

    @Test
    public void isUnixLikeFileSystemFreak2() {
        assertFalse(PathHelper.isUnixLikeFileSystem("d:/Users/linjiejun/Documents/"));
    }


}