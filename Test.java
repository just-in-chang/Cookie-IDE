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
import javafx.scene.control.MenuItem;
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

    private Stack<Node> test = new Stack<Node>();


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


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "lmao" );
        BorderPane window = new BorderPane();
        Scene scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );
        Pane workspace = new Pane();

        window.setTop( menu() );
        window.setCenter( workspace );
        window.setBottom( status( workspace ) );
        window.setLeft( controlPanel( workspace ) );

        stage.show();

    }


    public VBox menu()
    {
        VBox menuBar = new VBox();
        MenuBar menu = new MenuBar();
        Menu file = new Menu( "File" );
        MenuItem save = new MenuItem( "Save" );
        file.getItems().add( save );
        menu.getMenus().addAll( file );
        menuBar.getChildren().addAll( menu );

        save.setOnAction( e -> {
            System.out.println( "================\n" );
            for ( Node n : test )
            {
                Bounds boundsInScene = n.localToScene( n.getLayoutBounds() );
                System.out.println( n.toString() + "\n("
                    + boundsInScene.getMaxX() + ", " + boundsInScene.getMaxY()
                    + ")" + "\nWidth: " + boundsInScene.getWidth()
                    + "\nHeight: " + boundsInScene.getHeight() + "\n" );
            }
            System.out.println( "================" );
        } );

        return menuBar;
    }


    public HBox status( Pane workspace )
    {
        HBox statusBar = new HBox( 10 );
        Label mouseX = new Label( "lmao" );
        Label mouseY = new Label( "lmao" );
        statusBar.getChildren().addAll( mouseX, mouseY );

        workspace.setOnMouseMoved( e -> {
            mouseX.setText( "X: " + e.getX() );
            mouseY.setText( "Y: " + e.getY() );
        } );

        return statusBar;
    }


    public GridPane controlPanel( Pane workspace )
    {
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

        controlPanel.setPadding( new Insets( 10, 10, 10, 10 ) );
        controlPanel.add( add, 0, 0 );

        return controlPanel;
    }

}
