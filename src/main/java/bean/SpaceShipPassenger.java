package bean;

import java.io.File;
import java.io.Serializable;

/**
 * 将要被序列化的文件集合。
 */
public class SpaceShipPassenger implements Serializable {
    String filePath;//将要被传输的文件夹路径，不是绝对路径
    String fileName;//文件名
    File file;//文件真实数据

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}