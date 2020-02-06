import java.io.File;
import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) throws IOException {
        System.out.println("helloworld");
        File file= new File("/Users/linjiejun/Downloads/one.txt");
        file.createNewFile();
        new File("/Users/linjiejun/Downloads/two.txt").createNewFile();
    }
}
