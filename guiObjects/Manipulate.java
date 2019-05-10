package guiObjects;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Control;

public class Manipulate
{
    private double x;

    private double y;

    private boolean resize;

    private boolean activeDrag;
    
    public Manipulate()
    {
        x = -1;
        y = -1;
        resize = false;
        activeDrag = false;
    }
    
    public void setManipulate( Node node, Boolean resizeable )
    {
        if ( resizeable )
        {
            node.setOnMouseMoved( e -> {
                Control src = ( (Control)e.getSource() );
                
                ((WorkspacePane)src.getParent()).setX( src.getTranslateX() + e.getX() );
                ((WorkspacePane)src.getParent()).setY( src.getTranslateY() + e.getY() );
                
                if ( ( e.getX() > src.getWidth() - 6
                    && e.getY() > src.getHeight() - 6 ) )
                {
                    resize = true;
                    node.getScene().setCursor( Cursor.NW_RESIZE );
                }
                else
                {
                    resize = false;
                    node.getScene().setCursor( Cursor.DEFAULT );
                }
            } );
            node.setOnMouseExited( e -> {
                Control src = ( (Control)e.getSource() );
                if ( activeDrag == false )
                {
                    node.getScene().setCursor( Cursor.DEFAULT );
                }
                ((WorkspacePane)src.getParent()).setX( -1 );
                ((WorkspacePane)src.getParent()).setY( -1 );
            } );
            node.setOnMousePressed( e -> {
                
                x = e.getX();
                y = e.getY();
                if ( resize == false )
                {
                    node.getScene().setCursor( Cursor.MOVE );
                }
                activeDrag = true;
            } );
            node.setOnMouseDragged( e -> {
                Control src = ( (Control)e.getSource() );
//                src.setDisable( true );
                if ( resize == false )
                {
                    double moveX = src.getTranslateX() + e.getX() - x;
                    double moveY = src.getTranslateY() + e.getY() - y;
                    if (((guiObject)node).getName().equals( "Mexican" ))
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
            node.setOnMouseReleased( e -> {
                node.getScene().setCursor( Cursor.DEFAULT );
                resize = false;
                activeDrag = false;
//                node.setDisable( false );
            } );
        }
        else
        {
            node.setOnMouseExited( e -> {
                if ( activeDrag == false )
                {
                    node.getScene().setCursor( Cursor.DEFAULT );
                }
            } );
            node.setOnMousePressed( e -> {
                x = e.getX();
                y = e.getY();
                node.getScene().setCursor( Cursor.MOVE );
                activeDrag = true;
            } );
            node.setOnMouseDragged( e -> {
                Control src = ( (Control)e.getSource() );
                src.setDisable( true );
                double moveX = src.getTranslateX() + e.getX() - x;
                double moveY = src.getTranslateY() + e.getY() - y;
                src.setTranslateX( Math.max( 0, moveX ) );
                src.setTranslateY( Math.max( 0, moveY ) );
            } );
            node.setOnMouseReleased( e -> {
                node.getScene().setCursor( Cursor.DEFAULT );
                activeDrag = false;
                node.setDisable( false );
            } );
        }
        
    }
}
