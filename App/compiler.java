package App;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
                    	oos.writeObject("send");
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
                        
                    }
                    catch ( Exception ex )
                    {
                        System.out.println( "heheo" + ex );
                    }
                }
            };

            putOut.start();
        }
        catch (

        Exception ex )
        {
            System.out.println( ex );
        }
    }
    
    public void retrieve( )
    {
        try
        {
            Socket socket = new Socket( "localhost", 6666 );
            byte[] bytes = new byte[8192];
            ObjectOutputStream oos = new ObjectOutputStream(
                socket.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream(
                socket.getInputStream() );
            OutputStream FOS = new FileOutputStream("copy.java");
            Thread withdraw = new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        oos.writeObject("retrieve");
                        int count;
						 while ((count = ois.read(bytes)) > 0)
						 {
						 FOS.write(bytes, 0, count);
						 
						 }
                    }
                    catch ( Exception ex )
                    {
                        System.out.println( "heheo" + ex );
                    }
                }
            };

  
            withdraw.start();
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
