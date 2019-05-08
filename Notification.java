import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;


public class Notification
{

    private final Image image = new ImageIcon(
        this.getClass().getResource( "resources/Save.png" ) ).getImage();

    private SystemTray tray;

    private TrayIcon trayIcon;


    public Notification()
    {

        try
        {
            tray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon( image, "Tray Demo" );
            tray.add( trayIcon );
            trayIcon.displayMessage( "Save",
                "Save completed. ",
                TrayIcon.MessageType.NONE );
        }
        catch ( Exception ex )
        {
            System.out.println( ex );
        }

    }

}