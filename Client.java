
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			String x = "";
			Scanner sc = new Scanner(System.in);
			ServerSocket a = new ServerSocket(6666);
			Socket ab = a.accept();
			ObjectInputStream Ois = new ObjectInputStream(ab.getInputStream());
			ObjectOutputStream OOS = new ObjectOutputStream(ab.getOutputStream());
			while (!x.equals("quit")) 
			{
				x = (String) Ois.readUTF();
				OOS.writeUTF(x);
				OOS.flush();
			}
			a.close();

		} catch (Exception e) {
			System.out.print(e);
		}
	}

}