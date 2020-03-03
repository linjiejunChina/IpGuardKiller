import java.io.File;
import java.util.regex.Pattern;

public class PathHelper {
    static String lastDirInPath(String path) {
        if (path == null) {
            return "";
        }
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] split = path.split(pattern);
        return split[split.length - 1];
    }
}
