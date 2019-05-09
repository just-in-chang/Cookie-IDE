import javafx.scene.Cursor;
import javafx.scene.control.Control;
import javafx.scene.control.Label;


/**
 *  label that can be manipulated in size and position
 *
 *  @author  Andrew
 *  @version May 8, 2019
 *  @author  Period: 1
 *  @author  Assignment: Cookie IDE
 *
 *  @author  Sources: TODO
 */
public class guiLabel extends Label
{
    private double x = 0;

    private double y = 0;
    
    private boolean activeDrag = false;

    private String name = "";


    public guiLabel()
    {
        this( "" );
    }


    public guiLabel( String label )
    {
        super( label );
        name = label;
        this.setOnMouseExited( e -> {
            if ( activeDrag == false )
            {
                this.getScene().setCursor( Cursor.DEFAULT );
            }
        } );
        this.setOnMousePressed( e -> {
            x = e.getX();
            y = e.getY();
            this.getScene().setCursor( Cursor.MOVE );
            activeDrag = true;
        } );
        this.setOnMouseDragged( e -> {
            Control src = ( (Control)e.getSource() );
            src.setDisable( true );
            src.setTranslateX( src.getTranslateX() + e.getX() - x );
            src.setTranslateY( src.getTranslateY() + e.getY() - y );
        } );
        this.setOnMouseReleased( e -> {
            this.getScene().setCursor( Cursor.DEFAULT );
            activeDrag = false;
            this.setDisable( false );
        } );
    }


    public String getName()
    {
        return name;
    }
}
