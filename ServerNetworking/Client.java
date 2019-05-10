package ServerNetworking;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client
{
    public static void main( String[] args )
    {
        try
        {
            byte[] bytes = new byte[8192];
            Scanner sc = new Scanner( System.in );
            String x, name;
            Socket socket = new Socket( "localHost", 6666 );
            System.out.println( "IP Address" + socket.getInetAddress() );
            System.out.println( "Input fileplease: " );
            x = sc.nextLine();
            File file = new File( x );
            ObjectOutputStream OS = new ObjectOutputStream( socket.getOutputStream() );
            ObjectInputStream IS = new ObjectInputStream( socket.getInputStream() );
            InputStream FIS = new FileInputStream( file );
            OS.writeUTF( x );
            OS.flush();
            name = IS.readUTF();
            System.out.println( name );
            int count;
            while ( ( count = FIS.read( bytes ) ) > 0 )
            {
                OS.write( bytes, 0, count );
            }

            OS.close();
            IS.close();
            socket.close();
        }
        catch ( Exception e )
        {
            System.out.println( e );
        }
    }
}