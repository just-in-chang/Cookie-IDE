package App;

import java.util.Stack;

import Miscellaneous.Notification;
import guiObjects.SelectableGroup;
import guiObjects.WorkspacePane;
import guiObjects.controlButton;
import guiObjects.guiButton;
import guiObjects.guiLabel;
import guiObjects.guiObject;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// git add .
public class Main extends Application
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

    int selected = 0;

    Scene scene;

    VBox menu;

    WorkspacePane workspace;

    HBox status;

    HBox controlPanel;

    HBox editPanel;


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "lmao" );
        BorderPane window = new BorderPane();
        scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );
        workspace = new WorkspacePane();
        menu = menu( stage, workspace );
        status = status( workspace );
        controlPanel = controlPanel( stage, workspace );
        editPanel = editPanel( workspace );

        window.setTop( menu );
        window.setCenter( workspace );
        window.setBottom( status );
        window.setLeft( controlPanel );
        window.setRight( editPanel );

        stage.show();

    }


    public VBox menu( Stage stage, Pane workspace )
    {
        VBox menuBar = new VBox();
        MenuBar menu = new MenuBar();
        Menu file = new Menu( "File" );
        MenuItem save = new MenuItem( "Save" );
        file.getItems().addAll( save );
        menu.getMenus().addAll( file );
        menuBar.getChildren().addAll( menu );

        save.setOnAction( e -> {
            System.out.println( "================\n" );
            for ( Node n : test )
            {
                Bounds boundsInScene = n.localToParent( n.getLayoutBounds() );
                System.out.println( ( (guiObject)n ).getName() + "\n("
                    + boundsInScene.getMinX() + ", " + boundsInScene.getMinY()
                    + ")" + "\nWidth: " + boundsInScene.getWidth()
                    + "\nHeight: " + boundsInScene.getHeight() + "\n" );
            }
            System.out.println( "================" );
            Notification saveNoti = new Notification();
        } );

        return menuBar;
    }


    public HBox status( Pane workspace )
    {
        HBox statusBar = new HBox( 10 );
        Label mouseX = new Label( "lmao" );
        Label mouseY = new Label( "lmao" );
        Label selected = new Label( "Selected:  " );
        statusBar.getChildren().addAll( mouseX, mouseY, selected );
        workspace.setOnMouseMoved( e -> {
            mouseX.setText( "X: " + e.getX() );
            mouseY.setText( "Y: " + e.getY() );
            SelectableGroup tGroup = ( (WorkspacePane)workspace )
                .getToggleGroup();
            if ( workspace.getChildren().size() > 0 )
                selected.setText(
                    "Selected:  '" + tGroup.getSelected().getName() + "'" );
        } );

        return statusBar;
    }


    public HBox controlPanel( Stage stage, Pane workspace )
    {
        HBox controlBox = new HBox();
        GridPane controlPanel = new GridPane();
        controlBox.getChildren()
            .addAll( controlPanel, new Separator( Orientation.VERTICAL ) );
        controlPanel.setVgap( 10 );
        controlPanel.add( new controlButton( "Button",
            this,
            workspace,
            guiButton.class,
            test,
            stage ), 0, 0 );
        controlPanel.add( new controlButton( "Label",
            this,
            workspace,
            guiLabel.class,
            test,
            stage ), 0, 1 );

        for ( Node ass : controlPanel.getChildren() )
        {
            ( (controlButton)ass ).setMinWidth( 69 );

        }

        controlPanel.setPadding( new Insets( 25, 25, 25, 25 ) );

        return controlBox;
    }


    public HBox editPanel( Pane workspace )
    {
        HBox ePane = new HBox();
        VBox eItems = new VBox();
        ePane.getChildren().add( eItems );
        ePane.setMinWidth( 250 );
        ePane.getChildren().add( new Separator( Orientation.VERTICAL ) );
        Label label = new Label();
        TextField x = new TextField();
        TextField y = new TextField();
        eItems.getChildren().addAll( label, x, y );

        return ePane;
    }


    public void setSelected( int i )
    {
        selected = i;
    }


    public Scene getScene()
    {
        return scene;
    }

}