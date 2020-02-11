package bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Files implements Serializable {



    List<FileSendedBySocket> fileSendedBySockets = new ArrayList<>();

    public List<FileSendedBySocket> getFileSendedBySockets() {
        return fileSendedBySockets;
    }
}


