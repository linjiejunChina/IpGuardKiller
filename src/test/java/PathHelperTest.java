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

    } @Test
    public void lastDirInPath_Normal_mac_separator_end() {
        assertEquals("doc"
                , PathHelper.lastDirInPath("/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc/")
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
    }/**
     * faild in mac
     * success in win
     */
    @Test
    public void lastDirInPath_Normal_win_separator_end() {
        assertEquals("sdkservice"
                , PathHelper.lastDirInPath("D:\\ljj\\npt\\source\\sdkservice\\")
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

}