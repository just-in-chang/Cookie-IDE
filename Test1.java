import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {

		try {
			String x = "";
			Scanner sc = new Scanner(System.in);
			byte[] bytes = new byte[8192];
			int count;
			ServerSocket a = new ServerSocket(6666);
			Socket ab = a.accept();
			ObjectInputStream OIS = new ObjectInputStream(ab.getInputStream());
			ObjectOutputStream OOS = new ObjectOutputStream(ab.getOutputStream());
			OutputStream FOS = null;
			File file;
			x =  OIS.readUTF();
			file = new File(x);
			try {
			FOS = new FileOutputStream(file);
			}
			catch(Exception e)
			{
				System.out.println("File Not Found");
			}
			while((count = OIS.read(bytes)) > 0)
			{
				FOS.write(bytes, 0, count);
			}
			while(true)
			{
				
				if(x .equals("quit"))
				{
					OOS.writeUTF("OK");
					OOS.flush();
					break;
				}
				else
				{
					OOS.writeUTF("Client 1: " + x);
					OOS.flush();
				}
				
			}
			a.close();
			

		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
