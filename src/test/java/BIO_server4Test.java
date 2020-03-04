import org.junit.Test;

import static org.junit.Assert.*;

public class BIO_server4Test {

    /**
     * fail in win
     * success in mac
     * @throws PathHelper.NotNormalPathException
     */
    @Test
    public void receiveSizeAbsPathNormal_mac() throws PathHelper.NotNormalPathException {
        assertEquals("/user/ljjj/jj/kkk/osd/fdsf/",BIO_server4.receiveSizeAbsPath("/user/ljjj/jj/kkk/","/osd/fdsf"));
    }

    /**
     * fail in win
     * success in mac
     * @throws PathHelper.NotNormalPathException
     */
    @Test
    public void receiveSizeAbsPathNormal2_mac() throws PathHelper.NotNormalPathException {
        assertEquals("/user/ljjj/jj/kkk/osd/fdsf/",BIO_server4.receiveSizeAbsPath("/user/ljjj/jj/kkk/","\\osd\\fdsf"));
    }

    /**
     * fail in mac
     * success in win
     * @throws PathHelper.NotNormalPathException
     */
    @Test
    public void receiveSizeAbsPathNormal_win() throws PathHelper.NotNormalPathException {
        assertEquals("D:\\ljj\\npt\\source123\\sdkservice123\\osd\\fdsf\\"
                ,BIO_server4.receiveSizeAbsPath("D:\\ljj\\npt\\source123\\sdkservice123\\","/osd/fdsf"));
    }

    /**
     * fain in mac
     * success in win
     * @throws PathHelper.NotNormalPathException
     */
    @Test
    public void receiveSizeAbsPathNormal2_win() throws PathHelper.NotNormalPathException {
        assertEquals("D:\\ljj\\npt\\source123\\sdkservice123\\osd\\fdsf\\"
                ,BIO_server4.receiveSizeAbsPath("D:\\ljj\\npt\\source123\\sdkservice123\\","\\osd\\fdsf"));
    }
}