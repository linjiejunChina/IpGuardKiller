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
     *
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
        return path + appendSeparatorIfNeed(path);
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


    static String appendSeparatorIfNeed(String path) throws NotNormalPathException {
//        System.out.println("path is "+path);
        if (path == null) {
            throw new NotNormalPathException();
        }
        if (isWinFileSystem(path)) {
            if (!path.endsWith("\\")) {
                return "\\";
            } else
                return "";
        } else if (isUnixLikeFileSystem(path)) {
            if (!path.endsWith("/")) {
                return "/";
            } else
                return "";
        } else throw new NotNormalPathException();


    }


    static String cutSeparatorOfPathHead(String path) throws NotNormalPathException {
        if (path == null||path.equals("")) {
            throw new NotNormalPathException();
        }
        if (path.startsWith(File.separator)&&path.length()>1) {
            path = path.substring(1);
        }
        return path;
    }


}
