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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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

    Scene scene;

    VBox menu;

    WorkspacePane workspace;

    HBox status;

    HBox controlPanel;

    HBox editPanel;

    Label editPanelLabel;

    CoordinatePane coordPane;

    guiObject selectedNode = null;


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "Cookie IDE" );
        BorderPane window = new BorderPane();
        scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );
        workspace = new WorkspacePane();
        menu = menu( stage, workspace );
        status = status( workspace );
        controlPanel = controlPanel( stage, workspace );
        coordPane = new CoordinatePane();
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
            {
                selected.setText(
                    "Selected:  '" + tGroup.getSelected().getName() + "'" );

                selectedNode = tGroup.getSelected();

                Bounds boundsInScene = ( (Node)selectedNode )
                    .localToParent( ( (Node)selectedNode ).getLayoutBounds() );

                editPanelLabel.setText( tGroup.getSelected().getName() );
                coordPane.getxLabel()
                    .setText( Double.toString( boundsInScene.getMinX() ) );
                coordPane.getyLabel()
                    .setText( Double.toString( boundsInScene.getMinY() ) );
                coordPane.getWidthLabel()
                    .setText( Double.toString(
                        ( (Region)tGroup.getSelected() ).getWidth() ) );
                coordPane.getHeightLabel()
                    .setText( Double.toString(
                        ( (Region)tGroup.getSelected() ).getHeight() ) );

            }
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
        HBox rePane = new HBox();
        rePane.setAlignment( Pos.TOP_CENTER );
        rePane.setMinWidth( 250 );

        VBox ePane = new VBox();
        ePane.setPadding( new Insets( 25, 25, 25, 25 ) );
        ePane.setAlignment( Pos.TOP_LEFT );
        ePane.setSpacing( 10 );

        GridPane grid = new GridPane();
        grid.setPadding( new Insets( 25, 25, 25, 25 ) );
        grid.setVgap( 10 );
        grid.setHgap( 10 );

        Label label = new Label( "<No Selection>" );
        label.setStyle( "-fx-font-weight: bold" );
        editPanelLabel = label;

        rePane.getChildren().add( new Separator( Orientation.VERTICAL ) );
        rePane.getChildren().addAll( ePane );
        ePane.getChildren().addAll( label, coordPane, grid );
        grid.setVisible( true );
        return rePane;
    }


    public Scene getScene()
    {
        return scene;
    }

}