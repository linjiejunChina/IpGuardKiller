package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 要被发送的文件集合。
 */
public class FilesSpaceShip implements Serializable {

    List<SpaceShipPassenger> fileSendedBySockets = new ArrayList<>();

    public List<SpaceShipPassenger> getFileSendedBySockets() {
        return fileSendedBySockets;
    }
}


