package Miscellaneous;

import App.WorkspacePane;
import guiObjects.guiObject;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;


public class Manipulate
{
    private double x;

    private double y;

    private boolean resize;

    private boolean activeDrag;


    public Manipulate()
    {
        x = 0;
        y = 0;
        resize = false;
        activeDrag = false;
    }


    public void setManipulate( Node node, Boolean resizeable )
    {
        node.setOnMouseMoved( e -> {
            Control src = ( (Control)e.getSource() );

            ( (WorkspacePane)src.getParent() )
                .setX( src.getTranslateX() + e.getX() );
            ( (WorkspacePane)src.getParent() )
                .setY( src.getTranslateY() + e.getY() );

            if ( resizeable )
            {
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
            }
        } );
        node.setOnMouseExited( e -> {
            if ( activeDrag == false )
            {
                node.getScene().setCursor( Cursor.DEFAULT );
            }
        } );
        node.setOnMousePressed( e -> {

            x = e.getX();
            y = e.getY();
            if ( resize == false )
            {
                node.getScene().setCursor( Cursor.MOVE );
            }
            activeDrag = true;
            ( (WorkspacePane)( (Control)e.getSource() ).getParent() )
                .getToggleGroup()
                .setSelected( (guiObject)node );
        } );
        node.setOnMouseDragged( e -> {
            Control src = ( (Control)e.getSource() );
            WorkspacePane parent = ( (WorkspacePane)src.getParent() );
            src.setDisable( true );
            if ( resize == false )
            {
                double moveX = src.getTranslateX() + e.getX() - x;
                double moveY = src.getTranslateY() + e.getY() - y;

                if ( ( (guiObject)node ).getName().equals( "Mexican" ) )
                {
                    src.setTranslateX( moveX );
                    src.setTranslateY( moveY );
                }
                else
                {
                    src.setTranslateX( Math.min( Math.max( 0, moveX ),
                        parent.getMaxWidth() - src.getWidth() ) );
                    src.setTranslateY( Math.min( Math.max( 0, moveY ),
                        parent.getMaxHeight() - src.getHeight() ) );
                }
                ( (WorkspacePane)src.getParent() )
                    .setX( src.getTranslateX() + x );
                ( (WorkspacePane)src.getParent() )
                    .setY( src.getTranslateY() + y );
            }
            else
            {
                src.setPrefSize(
                    Math.min( parent.getWidth() - src.getTranslateX(),
                        e.getX() ),
                    Math.min( parent.getHeight() - src.getTranslateY(),
                        e.getY() ) );
            }
        } );
        node.setOnMouseReleased( e -> {
            node.getScene().setCursor( Cursor.DEFAULT );
            resize = false;
            activeDrag = false;
            node.setDisable( false );
        } );

        ContextMenu cMenu = new ContextMenu();
        MenuItem delete = new MenuItem( "Delete" );
        cMenu.getItems().add( delete );

        delete.setOnAction( e -> {
            node.setVisible( false );
        } );

        node.setOnContextMenuRequested( e -> {
            cMenu.show( node, Side.BOTTOM, 0, 0 );
        } );

    }
}
