package ServerNetworking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server // extends Application
{

    private static HashMap<String, String> map = new HashMap<String, String>();

    private static HashMap<String, File> fileMap = new HashMap<String, File>();

    private static byte iterationNo = 1;


    @SuppressWarnings("resource")
    public static void main( String[] args )
    {
        try
        {
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println( "Sever online. \nIP: " + IP.getHostAddress()
                + "\nPort 6666\n" );
            loadHashMap();
            while ( true )
            {
                System.out.println( "Iteration Number " + iterationNo );
                iterationNo++;
                // Server initialization
                ServerSocket ss = new ServerSocket( 6666 );
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream() );
                ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream() );

                File file = new File( "Backup/data" + iterationNo + ".java" );
                PrintWriter write = new PrintWriter(
                    new BufferedWriter( new FileWriter( file ) ) );
                Thread putOut = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            oos.writeObject( docHeading() );
                            oos.writeObject( docBody() );
                            oos.writeObject( docEnding() );
                            System.out
                                .println( file.getName() + " sent to client" );
                            write.println( docHeading() );
                            write.println( docBody() );
                            write.print( docEnding() );
                            System.out.println( file.getName() + " backed up" );
                            write.close();
                            fileMap.put( "data" + iterationNo, file );
                            oos.close();
                        }
                        catch ( Exception ex )
                        {
                            System.out.println( "oof" + ex );
                        }
                    }
                };

                Thread takeIn = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            map.put( "docInfo", (String)ois.readObject() );
                            while ( true )
                            {
                                String str = (String)ois.readObject(); // weir
                                if ( str.equals( "quit" ) )
                                    break;
                                System.out.println( str );
                            }
                            putOut.run();
                        }
                        catch ( Exception ex )
                        {
                            System.out.println( "oof" + ex );
                        }
                    }
                };

                putOut.setDaemon( true );
                takeIn.setDaemon( true );

                takeIn.run();
                ss.close();
                System.out.println( "Save Success. \n" );
            }
        }
        catch ( Exception ex )
        {
            System.out.println( ex );
        }
    }


    private static String docHeading()
    {
        return ( "import javafx.application.Application;\r\n"
            + "import javafx.scene.Scene;\r\n"
            + "import javafx.scene.layout.Pane;\r\n"
            + "import javafx.stage.Stage;\r\n" + "\r\n" + "\r\n"
            + "public class out extends Application\r\n" + "{\r\n"
            + "    public static void main( String[] args )\r\n" + "    {\r\n"
            + "        launch( args );\r\n" + "    }\r\n" + "\r\n" + "\r\n"
            + "    @Override\r\n" + "    public void start( Stage stage )\r\n"
            + "    {\r\n"
            + "        stage.setTitle( \"\" ); // TODO Put title of window here. \r\n"
            + "        Pane pane = new Pane();\r\n"
            + "        Scene scene = new Scene( pane, " + map.get( "docInfo" )
            + ");\r\n"
            + "        pane.setStyle( \"-fx-background-color: #ffffff\" );\r\n"
            + "        stage.setScene( scene );\r\n" );
    }


    private static String docBody()
    {
        String out = "";
        return out;
    }


    private static void loadHashMap()
    {
        System.out.println( "Loading backup files... " );
        File directory = new File( "Backup" );
        File[] filesInDirectory = directory.listFiles();
        if ( filesInDirectory != null )
        {
            for ( File file : filesInDirectory )
            {
                fileMap.put(
                    file.getName()
                        .substring( 0, file.getName().indexOf( ".java" ) ),
                    file );
                iterationNo++;
                System.out.println( "Loaded " + file.getName() );
            }
        }
        else
        {
            System.out.println( "No backup files found" );
        }
        System.out.println( "Loading backup files complete\n" );
    }


    private static String docEnding()
    {
        return ( "        stage.show();\r\n" + "    }\r\n" + "}\r\n" );
    }

}