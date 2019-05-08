import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// git add .
public class Test extends Application
{
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

    Stack<Node> test = new Stack<Node>();


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


    public HBox controlPanel( Pane workspace )
    {
        HBox controlBox = new HBox();
        GridPane controlPanel = new GridPane();
        controlBox.getChildren()
            .addAll( controlPanel, new Separator( Orientation.VERTICAL ) );
        controlPanel.setHgap( 5 );
        controlPanel.setVgap( 5 );
        controlPanel.add( new controlButton("Button", workspace, guiButton.class, test), 0, 0 );
        controlPanel.add( new controlButton("Label", workspace, guiLabel.class, test), 0, 1 );

        controlPanel.setPadding( new Insets( 10, 10, 10, 10 ) );
        return controlBox;
    }

}