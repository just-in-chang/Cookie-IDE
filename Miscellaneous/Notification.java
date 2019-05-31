package Miscellaneous;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;


/**
 * 
 * Creates notifications.
 *
 * @author Justin Chang
 */
public class Notification
{

    private boolean rItWorkBro;

    private final Image image = new ImageIcon(
        this.getClass().getResource( "resources/Save.png" ) ).getImage();

    private SystemTray tray = SystemTray.getSystemTray();

    private TrayIcon trayIcon = new TrayIcon( image, "Tray Demo" );


    /**
     * Creates a notification object
     */
    public Notification()
    {
        rItWorkBro = System.getProperty( "os.name" ).contains( "Windows" );
    }


    /**
     * Sends Successful save notification
     */
    public void saveSuccess()
    {
        notificationSend( "Save completed. ", TrayIcon.MessageType.INFO );
    }


    /**
     * Sends failure to save notification
     */
    public void saveFail()
    {
        notificationSend(
            "Save failed. Check if missing .java file extension. ",
            TrayIcon.MessageType.ERROR );
    }


    /**
     * 
     * Sends canceling a save notification.
     */
    public void saveCancel()
    {
        notificationSend( "Save canceled. ", TrayIcon.MessageType.WARNING );
    }


    /**
     * 
     * Sends connection failure notification.
     */
    public void connectionFail()
    {
        notificationSend( "Failed to connect to server. Unable to save. ",
            TrayIcon.MessageType.ERROR );
    }


    /**
     * 
     * Sends initialization failure notification.
     */
    public void initializationFail()
    {
        notificationSend( "Failed to initialize. Please check parameters. ",
            TrayIcon.MessageType.ERROR );
    }


    /**
     * 
     * Sends notification.
     * 
     * @param str
     *            Type of notification
     * @param type
     *            Notification background type
     */
    private void notificationSend( String str, TrayIcon.MessageType type )
    {
        if ( rItWorkBro )
            try
            {
                tray.add( trayIcon );
                trayIcon.displayMessage( "Save", str, type );
            }
            catch ( Exception ex )
            {
                System.out.println( ex );
            }
    }

}