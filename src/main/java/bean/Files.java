package bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Files {
    List<FileSendedBySocket> fileSendedBySockets = new ArrayList<>();
}

class FileSendedBySocket {
    String filePath;
    String fileName;
    File file;
}
