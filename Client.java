
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			int count = 0;
			byte[] buff = new byte[8092];
			String x = "bro";
			Scanner sc = new Scanner(System.in);
			Socket a = new Socket("localHost", 6666);
			File file = new File("Text.txt");
			FileOutputStream OOS = new FileOutputStream(file);
			ObjectInputStream OIS = new ObjectInputStream(a.getInputStream());
			// Write data to the output stream of the Client Socket.
			while (!x.equals("quit")) {
				System.out.println("Give File or quit");
				x = sc.nextLine();
				if (x.equals("quit")) {
					x = OIS.readUTF();
					if (x.contains("Ok")) {
						break;
					}
				}
				while ((count = OIS.read(buff)) > 0) {
					OOS.write(buff, 0, count);
				}
				OOS.flush();
			}
			OOS.flush();
			OOS.close();
			a.close();
		} catch (Exception e) {
			System.out.print(e);
		}

	}

}