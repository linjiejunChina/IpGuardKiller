import java.io.File;
import java.util.regex.Pattern;

public class PathHelper {
    static String lastDirInPath(String path) {

        String[] strings = splitPathByFileSeparator(path);

        return getOneOfPathByIdx(strings.length - 1, strings);
    }

    static String firstDirInPath(String path) {
        String[] strings = splitPathByFileSeparator(path);
        return getOneOfPathByIdx(0, strings);

    }

    static String exclueLastDirInPath(String path) {
        int endIdx = path.indexOf(lastDirInPath(path));
        return path.substring(0, endIdx);
    }

    static String[] splitPathByFileSeparator(String path) {
        if (path == null) {
            return new String[0];
        }
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        return path.split(pattern);
    }

    static String getOneOfPathByIdx(int idx, String[] pathSeparated) {
        if (pathSeparated == null||idx<0) {
            return "";
        }
        return pathSeparated[idx];
    }
}
