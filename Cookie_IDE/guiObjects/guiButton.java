package guiObjects;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Control;


/**
 *  button that can be manipulated in size and position
 *
 *  @author  Andrew
 *  @version May 8, 2019
 *  @author  Period: 1
 *  @author  Assignment: Cookie IDE
 *
 *  @author  Sources: TODO
 */
public class guiButton extends Button implements guiObject
{

    private double x = 0;

    private double y = 0;

    private boolean resize = false;

    private boolean activeDrag = false;

    private String name = "";


    public guiButton()
    {
        this( "" );
    }


    public guiButton( String label )
    {
        super( label );
        name = label;
        this.setOnMouseMoved( e -> {
            Control src = ( (Control)e.getSource() );

            if ( ( e.getX() > src.getWidth() - 6
                && e.getY() > src.getHeight() - 6 ) )
            {
                resize = true;
                this.getScene().setCursor( Cursor.NW_RESIZE );
            }
            else
            {
                resize = false;
                this.getScene().setCursor( Cursor.DEFAULT );
            }
        } );
        this.setOnMouseExited( e -> {
            if ( activeDrag == false )
            {
                this.getScene().setCursor( Cursor.DEFAULT );
            }
        } );
        this.setOnMousePressed( e -> {
            
            x = e.getX();
            y = e.getY();
            if ( resize == false )
            {
                this.getScene().setCursor( Cursor.MOVE );
            }
            activeDrag = true;
        } );
        this.setOnMouseDragged( e -> {
            Control src = ( (Control)e.getSource() );
            src.setDisable( true );
            if ( resize == false )
            {
                double moveX = src.getTranslateX() + e.getX() - x;
                double moveY = src.getTranslateY() + e.getY() - y;
                if (name.equals( "Mexican" ))
                {
                    src.setTranslateX( moveX  );
                    src.setTranslateY( moveY  );
                }
                else
                {
                    src.setTranslateX( Math.max( 0, moveX ) );
                    src.setTranslateY( Math.max( 0, moveY ) );
                }
            }
            else
            {
                src.setPrefSize( e.getX(), e.getY() );
            }
        } );
        this.setOnMouseReleased( e -> {
            this.getScene().setCursor( Cursor.DEFAULT );
            resize = false;
            activeDrag = false;
            this.setDisable( false );
        } );
    }


    public String getName()
    {
        return name;
    }
    
    public void setName( String n )
    {
        name = n;
    }

}
