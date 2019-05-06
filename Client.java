
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			String x = "bro";
			Scanner sc = new Scanner(System.in);
			Socket a = new Socket("localHost", 6666);
			ObjectOutputStream OOS = new ObjectOutputStream(a.getOutputStream());
			ObjectInputStream OIS = new ObjectInputStream(a.getInputStream());
			   // Write data to the output stream of the Client Socket.
			while(!x.equals("quit"))
			{
				System.out.println("Give command");
				x = sc.nextLine();
	           OOS.writeUTF(x);
	           OOS.flush();  
			String responseLine;
	           if((responseLine = OIS.readUTF()) != null) {
	               System.out.println("Server: " + responseLine);
	               if (responseLine.indexOf("OK") != -1) {
	                   break;
	               }
	           }
			}
			OOS.flush();
			OOS.close();
			a.close();
		} catch (Exception e) {
			System.out.print(e);
		}

	}
}