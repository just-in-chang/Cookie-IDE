//import java.awt.Image;
//import java.awt.SystemTray;
//import java.awt.TrayIcon;
//
//import javax.swing.ImageIcon;
//
//import javafx.scene.media.AudioClip;
//
//
//public class Notification
//{
//
//    private final Image image = new ImageIcon(
//        this.getClass().getResource( "resources/quizlet.png" ) ).getImage();
//
//    private SystemTray tray;
//
//    private TrayIcon trayIcon;
//
//    private QuizletGrabber quizlet;
//
//    private int min;
//
//    private int max;
//
//    private int notiS;
//
//
//    public Notification( String url, int m, int a, int s )
//    {
//        min = m;
//        max = a;
//        notiS = s;
//        try
//        {
//            tray = SystemTray.getSystemTray();
//            trayIcon = new TrayIcon( image, "Tray Demo" );
//            trayIcon.setImageAutoSize( true );
//            trayIcon.setToolTip( "Study Notification" );
//            tray.add( trayIcon );
//            trayIcon.displayMessage( "Quizlet URL",
//                url,
//                TrayIcon.MessageType.INFO );
//        }
//        catch ( Exception ex )
//        {
//            System.out.println( ex );
//        }
//
//    }
//
//
//    public void displayFactTray( String notiTerm, String notiDef )
//    {
//        try
//        {
//            trayIcon
//                .displayMessage( notiTerm, notiDef, TrayIcon.MessageType.INFO );
//        }
//        catch ( Exception ex )
//        {
//            System.out.println( ex );
//        }
//    }
//
//
//    public void startRandomNotify( int numNoti )
//    {
//        if ( quizlet == null )
//        {
//            try
//            {
//                trayIcon.displayMessage( "Add a Quizlet!",
//                    "Don't mess with the source. ",
//                    TrayIcon.MessageType.WARNING );
//            }
//            catch ( Exception ex )
//            {
//                System.out.println( ex );
//            }
//        }
//        else
//            for ( int i = 0; i < numNoti; i++ )
//            {
//                Term ph = quizlet.returnRandom();
//                playSound();
//                displayFactTray( ph.getTerm(), ph.getDef() );
//                try
//                {
//                    Thread.sleep( (int)( 60000
//                        * ( min + ( max - min ) * Math.random() ) ) );
//                }
//                catch ( Exception ex )
//                {
//                    System.out.println( ex );
//                }
//            }
//    }
//
//
//    public void setQuizlet( QuizletGrabber quizlet )
//    {
//        this.quizlet = quizlet;
//    }
//
//
//    private void playSound()
//    {
//        if ( notiS == 5 )
//        {
//            AudioClip notification = new AudioClip( this.getClass()
//                .getResource( "resources/notification"
//                    + ( (int)( Math.random() * 4 ) + 1 ) + ".mp3" )
//                .toString() );
//            notification.play();
//        }
//        else
//        {
//            AudioClip notification = new AudioClip( this.getClass()
//                .getResource( "resources/notification" + notiS + ".mp3" )
//                .toString() );
//            notification.play();
//        }
//    }
//}