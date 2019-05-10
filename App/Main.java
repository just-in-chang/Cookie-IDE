package App;

import java.util.Stack;


import guiObjects.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import javafx.scene.layout.ColumnConstraints;
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
    
    BorderPane window;

    VBox menu;

    WorkspacePane workspace;

    HBox status;

    HBox controlPanel;

    HBox editPanel;


    @Override
    public void start( Stage stage )
    {
        window = new BorderPane();
        scene = new Scene( window, 1280, 720 );
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

        stage.setTitle( "lmao" );
        stage.setScene( scene );
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
            // Notification saveNoti = new Notification();
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
        Thread statusUpdate = new Thread( new Runnable() {

            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch ( Exception e )
                    {
                        System.out.println( e );
                    }
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run()
                        {
                            mouseX.setText( "X: " + ((WorkspacePane)workspace).getX() );
                            mouseY.setText( "Y: " + ((WorkspacePane)workspace).getY() );
                            SelectableGroup tGroup = ( (WorkspacePane)workspace )
                              .getToggleGroup();
                            if ( workspace.getChildren().size() > 0 )
                              selected.setText( "Selected:  '"
                                  + ( (guiObject)tGroup.getSelected() ).getName() + "'" );
                            
                        }
                        
                    });
                }
            }
            
        });
        statusUpdate.setDaemon( true );
        statusUpdate.start();

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
            workspace,
            guiButton.class,
            test,
            stage ), 0, 0 );
        controlPanel.add( new controlButton( "Label",
            workspace,
            guiLabel.class,
            test,
            stage ), 0, 1 );
        controlPanel.add( new controlButton( "TextField",
            workspace,
            guiTextField.class,
            test,
            stage ), 0, 2 );

        for ( Node ass : controlPanel.getChildren() )
        {
            ( (controlButton)ass ).setMinWidth( 80 );
        }

        controlPanel.setPadding( new Insets( 25, 25, 25, 25 ) );

        return controlBox;
    }


    public HBox editPanel( Pane workspace )
    {
        HBox editBox = new HBox();
        VBox editItems = new VBox();
        GridPane editLabels = new GridPane();
        GridPane editFields = new GridPane();
        editLabels.setPadding( new Insets( 10 ) );
        editFields.setPadding( new Insets( 10 ) );
        editLabels.setVgap( 5 );
        editLabels.setHgap( 50 );
        editFields.setHgap( 10 );
        editBox.setMinWidth( 250 );
        editBox.getChildren().add( new Separator( Orientation.VERTICAL ) );
        editBox.getChildren().add( editItems );
        editItems.getChildren().add( editLabels );
        editItems.getChildren().add( editFields );

        return editBox;
    }

}