package App;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.LinkedList;

import Miscellaneous.Notification;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Compiler
{
    private Notification noti = new Notification();


    public void send( LinkedList<Node> list, Pane workspace, final Stage stage )
    {
        try
        {
            FileChooser fileChoose = new FileChooser();
            File file = fileChoose.showSaveDialog( stage );

            if ( file == null )
            {
                noti.saveCancel();
            }

            else if ( file.getName().length() < 5 || !file.getName()
                .substring( file.getName().length() - 5 )
                .equals( ".java" ) )
            {
                noti.saveFail();
            }
            else
            {
                Socket socket = new Socket( "localhost", 6666 );
                ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream() );
                ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream() );

                Thread putOut = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            oos.writeObject( workspace.getWidth() + ", "
                                + workspace.getHeight() );
                            for ( Node n : list )
                            {
                                Bounds boundsInScene = n
                                    .localToParent( n.getLayoutBounds() );
                                if ( n instanceof Label )
                                {
                                    sendLabel( oos, boundsInScene, n );
                                }
                                else if ( n instanceof Button )
                                {
                                    sendButton( oos, boundsInScene, n );
                                }
                                else if ( n instanceof TextInputControl )
                                {
                                    sendTextField( oos, boundsInScene, n );
                                }
                            }
                            oos.writeObject( "quit" );
                        }
                        catch ( Exception ex )
                        {
                            System.out.println( "heheo " + ex );
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
                            String meme = (String)ois.readObject();
                            while ( !meme.equals( "quit" ) )
                            {
                                out.println( meme );
                                meme = (String)ois.readObject();
                            }
                        }
                        catch ( EOFException ex )
                        {
                            out.close();
                            try
                            {
                                socket.close();
                            }
                            catch ( IOException e )
                            {
                                e.printStackTrace();
                            }
                        }
                        catch ( Exception ex )
                        {
                            System.out.println( "hehei " + ex );
                        }
                    }
                };

                putOut.setDaemon( true );
                takeIn.setDaemon( true );

                takeIn.start();
                putOut.start();

                noti.saveSuccess();
            }
        }
        catch ( ConnectException ex )
        {
            noti.connectionFail();
        }
        catch ( Exception ex )
        {
            System.out.println( ex );
        }
    }


    private void sendButton(
        ObjectOutputStream oos,
        Bounds boundsInScene,
        Node n )
        throws Exception
    {
        oos.writeObject( "Labeled" );
        oos.writeObject( ( (Labeled)n ).getText() );
        oos.writeObject( Double.toString( boundsInScene.getMinX() ) );
        oos.writeObject( Double.toString( boundsInScene.getMinY() ) );
        oos.writeObject( Double.toString( boundsInScene.getWidth() ) );
        oos.writeObject( Double.toString( boundsInScene.getHeight() ) );
    }


    private void sendLabel(
        ObjectOutputStream oos,
        Bounds boundsInScene,
        Node n )
        throws Exception
    {
        oos.writeObject( "Labeled" );
        oos.writeObject( ( (Labeled)n ).getText() );
        oos.writeObject( Double.toString( boundsInScene.getMinX() ) );
        oos.writeObject( Double.toString( boundsInScene.getMinY() ) );
        oos.writeObject( Double.toString( boundsInScene.getWidth() ) );
        oos.writeObject( Double.toString( boundsInScene.getHeight() ) );
    }


    private void sendTextField(
        ObjectOutputStream oos,
        Bounds boundsInScene,
        Node n )
        throws Exception
    {
        oos.writeObject( "TextField" );
        oos.writeObject( ( (TextInputControl)n ).getText() );
        oos.writeObject( Double.toString( boundsInScene.getMinX() ) );
        oos.writeObject( Double.toString( boundsInScene.getMinY() ) );
        oos.writeObject( Double.toString( boundsInScene.getWidth() ) );
        oos.writeObject( Double.toString( boundsInScene.getHeight() ) );
    }

}
