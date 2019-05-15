package ServerNetworking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server // extends Application
{

    private static HashMap<String, String> map = new HashMap<String, String>();

    private static byte iterationNo = 1;


    public static void main( String[] args )
    {
        try
        {

            System.out.println( "Sever online. \nPort 6666. \n" );

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

                Thread putOut = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            docHeading( oos );
                            docBody( oos );
                            docEnding( oos );
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
                                String str = (String)ois.readObject();
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


    private static void docHeading( ObjectOutputStream oos ) throws Exception
    {
        oos.writeObject( "import javafx.application.Application;\r\n"
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


    private static void docBody( ObjectOutputStream oos ) throws Exception
    {

    }


    private static void docEnding( ObjectOutputStream oos ) throws Exception
    {
        oos.writeObject( "        stage.show();\r\n" + "    }\r\n" + "}\r\n" );
    }

}