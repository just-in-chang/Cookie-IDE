package ServerNetworking;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Test 
{

    public static void main( String[] args )
    {
        getIP();
    }
    public static void getIP()
    {
    	try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("not a host");
		}
    }

}
