package bio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient {
	
	private Socket s;
	
	public FileClient(String host, int port, String file) {
		try {
			s = new Socket(host, port);
			sendFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void sendFile(String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		File file1 = new File(file);
		if (!file1.exists()) {
			file1.createNewFile();
		}
		FileInputStream fis = new FileInputStream(file1);
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		fis.close();
		dos.close();	
	}
	
	public static void main(String[] args) {
		FileClient fc = new FileClient("localhost", 1988
				, "/Users/linjiejun/Documents/linwork/iproject/java/IpGuardKiller/doc2/Printer-master-net.7z");
	}

}