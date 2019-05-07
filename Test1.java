import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {

		try {
			String x = "";
			Scanner sc = new Scanner(System.in);
			ServerSocket a = new ServerSocket(6666);
			Socket ab = a.accept();
			ObjectInputStream Ois = new ObjectInputStream(ab.getInputStream());
			ObjectOutputStream OOS = new ObjectOutputStream(ab.getOutputStream());
			FileOutputStream FIS;
			File file;
			while(true)
			{
				x = (String) Ois.readUTF();
				
				if(x .equals("quit"))
				{
					OOS.writeUTF("OK");
					OOS.flush();
					break;
				}
				else
				{
					file = new File(x);
					FIS = new FileOutputStream(x);
				}

			}
			a.close();
			

		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
