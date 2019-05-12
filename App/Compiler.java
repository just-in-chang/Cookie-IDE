package App;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;


public class Compiler
{
    public void send( LinkedList<Node> list, Pane workspace )
    {
        try
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
                        oos.writeObject( workspace.getWidth() + "\n"
                            + workspace.getHeight() + "\n" );
                        for ( Node n : list )
                        {
                            Bounds boundsInScene = n
                                .localToParent( n.getLayoutBounds() );
                            if ( n instanceof Labeled )
                            {
                                sendLabeled( oos, boundsInScene, n );
                            }
                            else if ( n instanceof TextInputControl )
                            {
                                sendTextField( oos, boundsInScene, n );
                            }
                        }
                        oos.writeObject( "quit" );
                        oos.close();
                    }
                    catch ( Exception ex )
                    {
                        System.out.println( "heheo" + ex );
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
                        PrintWriter out = new PrintWriter( new BufferedWriter(
                            new FileWriter( "out.java" ) ) );

                        String meme = (String)ois.readObject();
                        while ( !meme.equals( "quit" ) )
                        {
                            out.println( meme );
                            meme = (String)ois.readObject();
                        }
                        out.close();
                    }
                    catch ( Exception ex )
                    {
                        System.out.println( "hehei" + ex );

                    }
                }
            };

            putOut.start();
            takeIn.start();

            System.out.println( "lol? " );
        }
        catch (

        Exception ex )
        {
            System.out.println( ex );
        }
    }


    private void sendLabeled(
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
