import java.io.File;
import java.lang.reflect.Field;
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
        if (pathSeparated == null || idx < 0) {
            return "";
        }
        return pathSeparated[idx];
    }

    /**
     * 需要夸平台测试
     * @param path
     * @return
     */
    static String transSeparatorToLocalFileSystem(String path) throws NotNormalPathException {
        if (isWinFileSystem(path)) {
            path = path.replace("\\", File.separator);
        } else if (isUnixLikeFileSystem(path)) {
            path = path.replace("/", File.separator);
        } else {
            throw new NotNormalPathException();
        }
        return path+appendSeparatorIfNeed(path);
    }

    static boolean isWinFileSystem(String path) {
        if (path == null) {
            return false;
        }
        if (path.contains("\\") && !path.contains("/")) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isUnixLikeFileSystem(String path) {
        if (path == null) {
            return false;
        }
        if (path.contains("/") && !path.contains("\\") && !path.contains(":")) {
            return true;
        } else {
            return false;
        }
    }

    static class NotNormalPathException extends Exception {
    }

    private static String appendSeparatorIfNeed(String path) {
        if (path == null) {
            return "";
        }
        if (!path.endsWith(File.separator)) {
            return File.separator;
        }else
            return "";
    }

}
