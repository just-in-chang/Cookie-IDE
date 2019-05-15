package App;

import java.util.LinkedList;

import Miscellaneous.SelectableGroup;
import guiObjects.controlButton;
import guiObjects.guiButton;
import guiObjects.guiLabel;
import guiObjects.guiObject;
import guiObjects.guiTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    BorderPane window;

    LinkedList<Node> nodeList = new LinkedList<Node>();

    Scene scene;

    VBox menu;

    WorkspacePane workspace;

    HBox status;

    HBox controlPanel;

    HBox editPanel;

    VBox editPanelInside;

    Label editPanelLabel;

    CoordinatePane coordPane;

    guiObject selectedNode = null;


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "Cookie IDE" );
        window = new BorderPane();
        scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );

        workspace = new WorkspacePane();
        workspace.setStyle( "-fx-background-color: #ffffff" );
        workspace.setBorder( new Border( new BorderStroke( Color.BLACK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            BorderWidths.DEFAULT ) ) );
        workspace.setMinWidth( 500 );
        workspace.setMaxWidth( 500 );
        workspace.setMinHeight( 500 );
        workspace.setMaxHeight( 500 );

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

        stage.setTitle( "Cookie IDE" );
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
            Compiler compile = new Compiler();
            compile.send( nodeList, workspace, stage );
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
        Thread statusUpdate = new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                while ( true )
                {
                    try
                    {
                        Thread.sleep( 10 );
                    }
                    catch ( Exception e )
                    {
                        System.out.println( e );
                    }
                    Platform.runLater( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mouseX.setText(
                                "X: " + ( (WorkspacePane)workspace ).getX() );
                            mouseY.setText(
                                "Y: " + ( (WorkspacePane)workspace ).getY() );
                            SelectableGroup tGroup = ( (WorkspacePane)workspace )
                                .getToggleGroup();
                            if ( workspace.getChildren().size() > 0 )
                            {
                                selected.setText( "Selected:  '"
                                    + tGroup.getSelected().getName() + "'" );

                                if ( !tGroup.getSelected()
                                    .equals( selectedNode ) )
                                {
                                    window.setRight( editPanel( workspace ) );

                                }

                                selectedNode = tGroup.getSelected();

                                Bounds boundsInScene = ( (Node)selectedNode )
                                    .localToParent( ( (Node)selectedNode )
                                        .getLayoutBounds() );

                                editPanelLabel
                                    .setText( tGroup.getSelected().getName() );
                                coordPane.getxLabel()
                                    .setText( Double
                                        .toString( boundsInScene.getMinX() ) );
                                coordPane.getyLabel()
                                    .setText( Double
                                        .toString( boundsInScene.getMinY() ) );
                                coordPane.getWidthLabel()
                                    .setText( Double.toString(
                                        ( (Region)tGroup.getSelected() )
                                            .getWidth() ) );
                                coordPane.getHeightLabel()
                                    .setText( Double.toString(
                                        ( (Region)tGroup.getSelected() )
                                            .getHeight() ) );
                            }
                        }
                    } );
                }
            }
        } );
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
            nodeList,
            stage ), 0, 0 );
        controlPanel.add( new controlButton( "Label",
            workspace,
            guiLabel.class,
            nodeList,
            stage ), 0, 1 );
        controlPanel.add( new controlButton( "TextField",
            workspace,
            guiTextField.class,
            nodeList,
            stage ), 0, 2 );

        controlPanel.add( new Separator( Orientation.HORIZONTAL ), 0, 3 );

        for ( Node ass : controlPanel.getChildren() )
        {
            if ( ass instanceof controlButton )
                ( (controlButton)ass ).setMinWidth( 80 );
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
        editPanelInside = ePane;

        Label label = new Label( "<No Selection>" );
        label.setStyle( "-fx-font-weight: bold" );
        editPanelLabel = label;

        rePane.getChildren().add( new Separator( Orientation.VERTICAL ) );
        rePane.getChildren().addAll( ePane );
        ePane.getChildren().addAll( label, coordPane );

        if ( ( (WorkspacePane)workspace ).getToggleGroup()
            .getSelected() instanceof Labeled )
        {
            LabeledPane labelPane = new LabeledPane();
            ePane.getChildren().addAll( labelPane );

            labelPane.getApplyButton().setOnAction( e -> {
                ( (Labeled)selectedNode )
                    .setText( labelPane.getTextField().getText() );
            } );
        }

        return rePane;
    }


    public Scene getScene()
    {
        return scene;
    }

}