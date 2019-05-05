import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {

		try {
			String x = "bro";
			Scanner sc = new Scanner(System.in);
			Socket a = new Socket("localHost", 6666);
			ObjectOutputStream OOS = new ObjectOutputStream(a.getOutputStream());
			ObjectInputStream OIS = new ObjectInputStream(a.getInputStream());
			   // Write data to the output stream of the Client Socket.
	           OOS.writeUTF("HELO");
	    
	           // Flush data.
	           OOS.flush();  
	           OOS.writeUTF("I am Tom Cat");
	           OOS.flush();
			String responseLine;
	           while ((responseLine = OIS.readUTF()) != null) {
	               System.out.println("Server: " + responseLine);
	               if (responseLine.indexOf("OK") != -1) {
	                   break;
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
