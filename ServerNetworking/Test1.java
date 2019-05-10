package ServerNetworking;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Test1
{
    public static void main( String[] args )
    {

        try
        {
            String x = "";
            Scanner sc = new Scanner( System.in );
            byte[] bytes = new byte[8192];
            int count;
            int s = 0;
            ServerSocket a = new ServerSocket( 6666 );
            Socket ab = a.accept();
            ObjectOutputStream OOS = new ObjectOutputStream(
                ab.getOutputStream() );
            ObjectInputStream OIS = new ObjectInputStream(
                ab.getInputStream() );
            OutputStream FOS = new FileOutputStream( "xdxd.txt" );
            x = OIS.readUTF();
            OOS.writeUTF( x );
            OOS.flush();
            while ( ( count = OIS.read( bytes ) ) > 0 )
            {
                FOS.write( bytes, 0, count );
            }
            ab.close();
            a.close();

        }
        catch ( Exception e )
        {
            System.out.print( e );
        }
    }

}
