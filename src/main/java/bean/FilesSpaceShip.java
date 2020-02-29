package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 要被发送的文件集合。
 */
public class FilesSpaceShip implements Serializable {

    List<SpaceShipPassenger> fileSendedBySockets = new ArrayList<>();

    public List<SpaceShipPassenger> getFileSendedBySockets() {
        return fileSendedBySockets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilesSpaceShip that = (FilesSpaceShip) o;
        return fileSendedBySockets.equals(that.fileSendedBySockets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileSendedBySockets);
    }
}


