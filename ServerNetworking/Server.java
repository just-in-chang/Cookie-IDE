package ServerNetworking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * The server side, receives files and saves it into the backup folder in the
 * computer that runs the server. Client can retrieve and access the files.
 * 
 * @author Justin and Nick
 *
 */
public class Server // extends Application
{
    // storing server data
    private static LinkedList<String> map = new LinkedList<String>();

    // storing files inside hashMap
    private static HashMap<String, File> fileMap = new HashMap<String, File>();

    private static byte iterationNo = 1;

    private static String backupFileDirectory = "";

    private static final String LABELED_LIST = "ButtonLabelTextFieldRadioButtonCheckBox";

    private static final String RESIZABLE_LIST = "ButtonLabelTextField";


    /**
     * 
     * Initializes a server object.
     * 
     * @param args
     *            Arguments for main
     */
    public static void main( String[] args )
    {
        try
        {
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println(
                "Sever Online\nIP: " + IP.getHostAddress() + "\nPort 6969\n" );
            // renew the hashmap for file so that it's updated for client to
            // pull
            loadHashMap();
            while ( true )
            {
                System.out.println( "Iteration Number " + iterationNo );
                iterationNo++;
                // initialization of the server socket and streams
                ServerSocket ss = new ServerSocket( 6969 );
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream() );
                ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream() );

                System.out.println();
                // receive byte
                byte type = ois.readByte();
                // chooses either to save the files, or open and retrieve it
                switch ( type )
                {
                    // saves the file and the date it was last modified
                    case 0:
                        System.out.println( "Case: Save" );
                        DateTimeFormatter dtf = DateTimeFormatter
                            .ofPattern( "yyyy-MM-dd HH:mm" );
                        LocalDateTime time = LocalDateTime.now();
                        String Time = dtf.format( time );
                        String title = "data" + ( iterationNo - 1 );
                        File file = new File(
                            backupFileDirectory + "\\" + title + ".java" );
                        PrintWriter write = new PrintWriter(
                            new BufferedWriter( new FileWriter( file ) ) );
                        map = new LinkedList<String>();
                        Thread putOut = new Thread()
                        {
                            @SuppressWarnings("null")
                            @Override
                            public void run()
                            {
                                try
                                {
                                    // prints all of the data into code format
                                    // then send all of it back
                                    oos.writeObject(
                                        docHeading( title, Time ) );
                                    String body = docBody();
                                    if ( body != null && body.length() > 0 )
                                        oos.writeObject( body.substring( 0,
                                            body.length() - 2 ) );
                                    oos.writeObject( docEnding() );
                                    System.out.println(
                                        file.getName() + " sent to client" );
                                    // saves a backup file inside the server for
                                    // user to open
                                    write.println(
                                        "package ServerNetworking.Backup;\r\n"
                                            + "\r\n" );
                                    write.println( docHeading( title, Time ) );
                                    if ( body != null && body.length() > 0 )
                                        write.print( body );
                                    write.print( docEnding() );
                                    System.out.println(
                                        file.getName() + " backed up" );
                                    write.close();
                                    fileMap.put( title, file );
                                    oos.close();
                                }
                                catch ( Exception ex )
                                {
                                    System.out.println( "oof1" + ex );
                                    ex.printStackTrace();
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
                                    // puts the information received from client
                                    // into a LinkedList to store
                                    map.add( (String)ois.readObject() );
                                    while ( true )
                                    {
                                        String str = (String)ois.readObject();
                                        if ( str.equals( "quit" ) )
                                        {
                                            break;
                                        }
                                        toCode( str, ois );
                                    }
                                    putOut.run();
                                }
                                catch ( StringIndexOutOfBoundsException ex )
                                {
                                    putOut.run();
                                }
                                catch ( Exception ex )
                                {
                                    putOut.run();

                                    System.out.println( "oof2" + ex );
                                }
                            }
                        };

                        putOut.setDaemon( true );
                        takeIn.setDaemon( true );

                        takeIn.run();
                        ss.close();
                        System.out.println( "Save Success\n" );
                        break;
                    // Opens the all the java files available for the user to
                    // select
                    case 1:
                        System.out.println( "Case Open" );
                        int mapSize = fileMap.keySet().size();
                        System.out.println( mapSize + " Backup Files" );
                        for ( String key : fileMap.keySet() )
                        {
                            oos.writeObject( key );
                        }

                        oos.flush();
                        // System.out.println( (String)ois.readObject() );

                        System.out.println( "Open Success\n" );
                        oos.close();
                        ss.close();
                        iterationNo--;
                        break;
                    // Retrieves the file so that the code is sent to the user
                    case 2:
                        System.out.println( "Case Retrieve" );
                        String fileName = (String)ois.readObject();
                        System.out
                            .println( "Sending " + fileName + " to Client" );
                        BufferedReader read = new BufferedReader(
                            new FileReader( fileMap.get( fileName ) ) );
                        String str = read.readLine();
                        int count = 3;
                        while ( str != null )
                        {
                            if ( count == 0 )
                                oos.writeObject( str );
                            else
                                count--;
                            str = read.readLine();
                        }
                        oos.writeObject( "quit" );
                        oos.close();
                        ss.close();
                        System.out.println( "Retrieve Success\n" );
                        iterationNo--;
                        break;
                }
            }
        }
        catch ( Exception ex )
        {
            System.out.println( ex );
        }
    }


    /**
     * The code that's going to be placed inside a java file when the data is
     * transferred over
     * 
     * @return the start of the code
     */
    private static String docHeading( String title, String time )
    {
        return ( "import javafx.application.Application;\r\n"
            + "import javafx.scene.Scene;\r\n"
            + "import javafx.scene.control.Button;\r\n"
            + "import javafx.scene.control.CheckBox;\r\n"
            + "import javafx.scene.control.Label;\r\n"
            + "import javafx.scene.control.RadioButton;\r\n"
            + "import javafx.scene.control.TextField;\r\n"
            + "import javafx.scene.image.Image;\r\n"
            + "import javafx.scene.image.ImageView;\r\n"
            + "import javafx.scene.layout.Pane;\r\n"
            + "import javafx.stage.Stage;\r\n\n" + "public class " + title
            + " extends Application { // Time of Creation: " + time + "\r\n\n"
            + "    public static void main( String[] args )\r\n" + "    {\r\n"
            + "        launch( args );\r\n" + "    }\r\n" + "\r\n" + "\r\n"
            + "    @Override\r\n" + "    public void start( Stage stage )\r\n"
            + "    {\r\n"
            + "        stage.setTitle( \"\" ); // TODO Put title of window here. \r\n"
            + "        Pane pane = new Pane();\r\n"
            + "        Scene scene = new Scene( pane, " + map.getFirst()
            + ");\r\n"
            + "        pane.setStyle( \"-fx-background-color: #ffffff\" );\r\n"
            + "        stage.setScene( scene );\r\n" );
    }


    /**
     * @return Body of code with all the stuff
     */
    private static String docBody()
    {
        boolean meme = true;
        String out = "";
        for ( String code : map )
        {
            if ( !meme )
                out += code + "\r\n";
            else
                meme = false;
        }
        return out;
    }


    /**
     * @return the ending of the code
     */
    private static String docEnding()
    {
        return ( "        stage.show();\r\n" + "    }\r\n" + "}\r\n" );
    }


    /**
     * loads all the files inside the backup folder to a hashmap so that it is
     * accessible for the user
     */
    private static void loadHashMap()
    {
        File directory = new File( "ServerNetworking\\Backup" );
        backupFileDirectory = directory.getAbsolutePath();
        System.out
            .println( "Loading backup files from " + backupFileDirectory );

        File[] filesInDirectory = directory.listFiles();
        if ( filesInDirectory != null )
        {
            for ( File file : filesInDirectory )
            {
                if ( file.getName()
                    .substring( file.getName().length() - 5 )
                    .equals( ".java" ) )
                {
                    fileMap.put(
                        file.getName()
                            .substring( 0, file.getName().indexOf( ".java" ) ),
                        file );
                    iterationNo++;
                    System.out.println( "Loaded " + file.getName() );
                }
            }
        }
        else
        {
            System.out.println( "No backup files found" );
        }
        System.out.println( "Loading backup files complete\n" );
    }


    /**
     * 
     * Turns the Nodes received into code
     * 
     * @param type
     *            Type of node
     * @param ois
     *            Input stream to receive information for node
     * @throws Exception
     *             Any exception
     */
    private static void toCode( String type, ObjectInputStream ois )
        throws Exception
    {
        String name = (String)ois.readObject();
        String indent = "        ";
        String out = indent + type + " " + name + " = new " + type + "(";
        if ( LABELED_LIST.contains( type ) )
            out += "\"" + (String)ois.readObject() + "\" );\r\n";
        else
            out += ");\r\n";
        out += indent + name + ".setTranslateX(" + (String)ois.readObject()
            + ");\r\n";
        out += indent + name + ".setTranslateY(" + (String)ois.readObject()
            + ");\r\n";
        if ( RESIZABLE_LIST.contains( type ) )
        {
            out += indent + name + ".setMinWidth(" + (String)ois.readObject()
                + ");\r\n";
            out += indent + name + ".setMinHeight(" + (String)ois.readObject()
                + ");\r\n";
        }
        if ( type.equals( "ImageView" ) )
        {
            out += indent + "Image " + name + "Image = new Image(\""
                + ois.readObject() + "\");\r\n";
            out += indent + name + ".setImage(" + name + "Image);\r\n";
        }
        out += indent + "pane.getChildren().add(" + name + ");\r\n";
        map.add( out );
    }
}