import java.io.File;

public class PathHelper {
    static String lastDirInPath(String path) {
        if (path == null) {
            return "";
        }
        String[] split = path.split(File.separator);
        return split[split.length - 1];
    }
}
