
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.xml.soap.Text;

public class Client {

	public static void main(String[] args) {
		try {
			String output = "bro";
			String name = " ";
			Scanner sc = new Scanner(System.in);
			Socket a = new Socket("127.0.0.1", 6666);
			System.out.println("Give File or quit");
			File file = new File(sc.nextLine());
			InputStream FOS = new FileInputStream(file);
			ObjectOutputStream OOS = new ObjectOutputStream(a.getOutputStream());
			ObjectInputStream OIS = new ObjectInputStream(a.getInputStream());
			// Write data to the output stream of the Client Socket.
			if (!output.equals("quit")) {
				OOS.writeUTF("hello there");
				OOS.flush();
				name = OIS.readUTF();
				System.out.println("server says" + name);
			}
			OOS.flush();
			OOS.close();
			a.close();
		} catch (Exception e) {
			System.out.print(e);
		}

	}

}