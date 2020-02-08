package bean;

import java.io.File;

public class FileSendedBySocket {
    String filePath;//将要被传输的文件夹路径，不是绝对路径
    String fileName;
    File file;

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