package ServerNetworking;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

public class Test1 {
	public static void main(String[] args) {

		try {

			Scanner sc = new Scanner(System.in);
			byte[] bytes = new byte[8192];
			
			ServerSocket a = new ServerSocket(6666);
			Socket ab = a.accept();
			ObjectInputStream OIS = new ObjectInputStream(ab.getInputStream());
			ObjectOutputStream OOS = new ObjectOutputStream(ab.getOutputStream());
			 PrintWriter out = new PrintWriter(
		                new BufferedWriter( new FileWriter( "out.java" ) ) );

			//InputStream FIS = new FileInputStream("out.java");
			Thread Run = new Thread() {
				public void run() {
					try {
						
						String x = (String)OIS.readObject();
                        while ( !x.equals( "quit" ) )
                        {
                        	System.out.println(x);
                            out.println( x );
                            out.flush();
                            x = (String)OIS.readObject();
                        }
							// sends file through
						
						//while ((count = FIS.read(bytes)) > 0) {
						//	 OOS.write(bytes, 0, count);
						//	 }
						ab.close();
						a.close();

					} catch (Exception e) {
						System.out.println(e);
					}
				}
			};
			Run.start();

		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
