package Miscellaneous;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;


public class Notification
{

    private final Image image = new ImageIcon(
        this.getClass().getResource( "resources/Save.png" ) ).getImage();

    private SystemTray tray = SystemTray.getSystemTray();

    private TrayIcon trayIcon = new TrayIcon( image, "Tray Demo" );


    public void saveSuccess()
    {
        notificationSend( "Save completed. ", TrayIcon.MessageType.INFO );
    }


    public void saveFail()
    {
        notificationSend(
            "Save failed. Check if missing .java file extension. ",
            TrayIcon.MessageType.ERROR );
    }


    public void saveCancel()
    {
        notificationSend( "Save canceled. ", TrayIcon.MessageType.WARNING );
    }


    public void connectionFail()
    {
        notificationSend( "Failed to connect to server. Unable to save. ",
            TrayIcon.MessageType.ERROR );
    }


    private void notificationSend( String str, TrayIcon.MessageType type )
    {
        try
        {
            if ( !System.getProperty("os.name").contains( "Mac" ))
            {
                tray.add( trayIcon );
                trayIcon.displayMessage( "Save", str, type );
            }

        }
        catch ( Exception ex )
        {
            System.out.println( ex );
        }
    }

}