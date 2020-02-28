import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class Compressor {
    public static FileInputStream zip(String targetPath, String destinationFilePath, String password) {
        try {
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            EncrypZipParametersIfPasswordExsit(password, parameters);

            ZipFile zipFile = new ZipFile(destinationFilePath);

            File targetFile = new File(targetPath);
            if (targetFile.isFile()) {
                zipFile.addFile(targetFile, parameters);
            } else if (targetFile.isDirectory()) {
                zipFile.addFolder(targetFile, parameters);
            }

            File file = zipFile.getFile();

            FileInputStream fileOutputStream = new FileInputStream(file);
            return fileOutputStream;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void unzip(String targetZipFilePath, String destinationFolderPath, String password) {
        try {
            ZipFile zipFile = new ZipFile(targetZipFilePath);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destinationFolderPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**/ /// for test only
    public static void main(String[] args) {

        String targetPath = "/Users/linjiejun/Desktop/Outline";
        String zipFilePath = "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc2/OutlineNow.zip";
        String unzippedFolderPath = "/Users/linjiejun/Desktop/OutlineUnZip";
        String password = ""; // keep it EMPTY<""> for applying no password protection

        Compressor.zip(targetPath, zipFilePath, password);
//            Compressor.unzip(zipFilePath, unzippedFolderPath, password);
    }/**/


    private static void EncrypZipParametersIfPasswordExsit(String pwd, ZipParameters parameters) {
        if (pwd != null && pwd.length() > 0) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword(pwd);
        }
    }
}


