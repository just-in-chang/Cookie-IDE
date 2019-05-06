import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// git add .
public class Test extends Application
{
    private int bCount = 1;


    /**
     * main class lmao
     * 
     * @param args
     *            command line arguments USED!
     */
    public static void main( String[] args )
    {
        launch( args );
    }

    // public void setManipulative( Control...controls )
    // {
    // for ( Control control : controls )
    // {
    // control.setOnMouseMoved( e -> {
    // Control src = ((Control)e.getSource());
    //
    // if ( ( e.getX() > src.getWidth() - 6 && e.getY() > src.getHeight() - 6 )
    // )
    // {
    // resize = true;
    // control.getScene().setCursor( Cursor.NW_RESIZE );
    // }
    // else
    // {
    // resize = false;
    // control.getScene().setCursor( Cursor.DEFAULT );
    // }
    // });
    // control.setOnMouseExited( e -> {
    // if ( activeDrag == false )
    // {
    // control.getScene().setCursor( Cursor.DEFAULT );
    // }
    // });
    // control.setOnMousePressed( e -> {
    // x = e.getX();
    // y = e.getY();
    // if (resize == false )
    // {
    // control.getScene().setCursor( Cursor.MOVE );
    // }
    // activeDrag = true;
    // });
    // control.setOnMouseDragged( e -> {
    // Control src = ((Control)e.getSource());
    // src.setDisable( true );
    // if ( resize == false )
    // {
    // src.setTranslateX( src.getTranslateX() + e.getX() - x );
    // src.setTranslateY( src.getTranslateY() + e.getY() - y );
    // }
    // else
    // {
    // src.setPrefSize( e.getX(), e.getY() );
    // }
    // });
    // control.setOnMouseReleased( e -> {
    // control.getScene().setCursor( Cursor.DEFAULT );
    // resize = false;
    // activeDrag = false;
    // control.setDisable( false );
    // });
    // }
    // }

    Stack<Node> test = new Stack<Node>();


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "lmao" );
        BorderPane window = new BorderPane();
        Scene scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );

        VBox menuBar = new VBox();
        MenuBar menu = new MenuBar();
        Menu file = new Menu( "File" );
        menu.getMenus().addAll( file );
        menuBar.getChildren().addAll( menu );

        HBox statusBar = new HBox( 10 );
        Label mouseX = new Label( "lmao" );
        Label mouseY = new Label( "lmao" );
        statusBar.getChildren().addAll( mouseX, mouseY );

        Pane workspace = new Pane();
        workspace.setOnMouseMoved( e -> {
            mouseX.setText( "X: " + e.getX() );
            mouseY.setText( "Y: " + e.getY() );
        } );

        GridPane controlPanel = new GridPane();
        controlPanel.setHgap( 5 );
        controlPanel.setVgap( 5 );
        Button add = new Button( "Button" );
        add.setOnAction( e -> {
            guiButton meme = new guiButton( "Button" + bCount );
            workspace.getChildren().add( meme );
            bCount++;
            test.push( meme );
        } );

        Button save = new Button( "Save" );
        save.setOnAction( e -> {
            System.out.println( "================" );
            for ( Node n : test )
            {
                Bounds boundsInScene = n.localToScene( n.getLayoutBounds() );
                System.out.println( "\n\n" + n.toString() + "("
                    + boundsInScene.getMaxX() + ", " + boundsInScene.getMaxY()
                    + ")" + "\nWidth: " + boundsInScene.getWidth()
                    + "\nHeight: " + boundsInScene.getHeight() + "\n" );
            }
            System.out.println( "================" );
        } );

        controlPanel.setPadding( new Insets( 10, 10, 10, 10 ) );
        controlPanel.add( add, 0, 0 );
        controlPanel.add( save, 0, 1 );

        window.setTop( menuBar );
        window.setCenter( workspace );
        window.setBottom( statusBar );
        window.setLeft( controlPanel );

        stage.show();

    }

}
