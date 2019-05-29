package Miscellaneous;

import App.WorkspacePane;
import guiObjects.guiObject;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 *  used to modify mouse behavior of given node to implement position and resizing modification
 *
 *  @author  Andrew Chen
 *  @version May 29, 2019
 *  @author  Period: 1
 *  @author  Assignment: Cookie IDE
 *
 *  @author  Sources: TODO
 */
public class Manipulate
{
    private double x;

    private double y;

    private boolean resize;

    private boolean activeDrag;


    /**
     * creates a Manipulate object with default values
     */
    public Manipulate()
    {
        x = 0;
        y = 0;
        resize = false;
        activeDrag = false;
    }


    /**
     * method for general javafx control nodes to set mouse behavior; defines mouse drag to move the node or resize the node
     * @param node node to set behavior
     * @param resizeable checks if given node is able to be resized or not
     */
    public void setManipulate( Node node, Boolean resizeable )
    {
        node.setOnMouseMoved( e -> {
            Control src = ( (Control)e.getSource() );

            ( (WorkspacePane)src.getParent() )
                .setMouseX( src.getTranslateX() + e.getX() );
            ( (WorkspacePane)src.getParent() )
                .setMouseY( src.getTranslateY() + e.getY() );

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
                .getSelectableGroup()
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
                    .setMouseX( src.getTranslateX() + x );
                ( (WorkspacePane)src.getParent() )
                    .setMouseY( src.getTranslateY() + y );
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
            ( (Pane)node.getParent() ).getChildren().remove( node );
        } );

        node.setOnContextMenuRequested( e -> {
            cMenu.show( node, e.getScreenX(), e.getScreenY() );
        } );

    }


    /**
     * special method for ImageView class, as it has differing methods than regular javafx control nodes
     * @param node
     */
    public void imageManipulate( ImageView node )
    {
        node.setOnMouseMoved( e -> {
            Node src = ( (Node)e.getSource() );

            ( (WorkspacePane)src.getParent() )
                .setMouseX( src.getTranslateX() + e.getX() );
            ( (WorkspacePane)src.getParent() )
                .setMouseY( src.getTranslateY() + e.getY() );
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
            ( (WorkspacePane)( (Node)e.getSource() ).getParent() )
                .getSelectableGroup()
                .setSelected( (guiObject)node );
        } );
        node.setOnMouseDragged( e -> {
            ImageView src = ( (ImageView)e.getSource() );
            WorkspacePane parent = ( (WorkspacePane)src.getParent() );
            src.setDisable( true );
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
                    parent.getMaxWidth() - src.getImage().getWidth() ) );
                src.setTranslateY( Math.min( Math.max( 0, moveY ),
                    parent.getMaxHeight() - src.getImage().getHeight() ) );
            }
            ( (WorkspacePane)src.getParent() ).setMouseX( src.getTranslateX() + x );
            ( (WorkspacePane)src.getParent() ).setMouseY( src.getTranslateY() + y );
        } );
        node.setOnMouseReleased( e -> {
            node.getScene().setCursor( Cursor.DEFAULT );
            activeDrag = false;
            node.setDisable( false );
        } );

        ContextMenu cMenu = new ContextMenu();
        MenuItem delete = new MenuItem( "Delete" );
        cMenu.getItems().add( delete );

        delete.setOnAction( e -> {
            ( (Pane)node.getParent() ).getChildren().remove( node );
        } );

        node.setOnContextMenuRequested( e -> {
            cMenu.show( node, e.getScreenX(), e.getScreenY() );
        } );
    }


    public void textFieldManipulate( TextField node )
    {
        node.setOnMouseMoved( e -> {
            Control src = ( (Control)e.getSource() );

            ( (WorkspacePane)src.getParent() )
                .setMouseX( src.getTranslateX() + e.getX() );
            ( (WorkspacePane)src.getParent() )
                .setMouseY( src.getTranslateY() + e.getY() );
            if ( ( e.getX() > src.getWidth() - 6 ) )
            {
                resize = true;
                node.setCursor( Cursor.E_RESIZE );
            }
            else
            {
                resize = false;
                node.setCursor( Cursor.TEXT );
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
                .getSelectableGroup()
                .setSelected( (guiObject)node );
        } );
        node.setOnMouseDragged( e -> {
            WorkspacePane parent = ( (WorkspacePane)node.getParent() );
            node.setDisable( true );
            if ( resize == false )
            {
                double moveX = node.getTranslateX() + e.getX() - x;
                double moveY = node.getTranslateY() + e.getY() - y;

                node.setTranslateX( Math.min( Math.max( 0, moveX ),
                    parent.getMaxWidth() - node.getWidth() ) );
                node.setTranslateY( Math.min( Math.max( 0, moveY ),
                    parent.getMaxHeight() - node.getHeight() ) );
                ( (WorkspacePane)node.getParent() )
                    .setMouseX( node.getTranslateX() + x );
                ( (WorkspacePane)node.getParent() )
                    .setMouseY( node.getTranslateY() + y );
            }
            else
            {
                node.setPrefWidth(
                    Math.min( parent.getWidth() - node.getTranslateX(),
                        e.getX() ) );
            }
        } );
        node.setOnMouseReleased( e -> {
            node.getScene().setCursor( Cursor.DEFAULT );
            resize = false;
            activeDrag = false;
            node.setDisable( false );
        } );

        ContextMenu cMenu = new ContextMenu();
        MenuItem delete = new MenuItem( "Delete node" );
        cMenu.getItems().add( delete );

        delete.setOnAction( e -> {
            ( (Pane)node.getParent() ).getChildren().remove( node );
            
        } );

        node.setOnContextMenuRequested( e -> {
            cMenu.show( node, e.getScreenX(), e.getScreenY() );

        } );
    }
}
