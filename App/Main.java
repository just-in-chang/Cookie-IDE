package App;

import java.util.LinkedList;

import Miscellaneous.Notification;
import Miscellaneous.SelectableGroup;
import guiObjects.controlButton;
import guiObjects.guiButton;
import guiObjects.guiImageView;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
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
import javafx.stage.Modality;
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

    private BorderPane window;

    private LinkedList<Node> nodeList = new LinkedList<Node>();

    private Scene scene;

    private VBox menu;

    private WorkspacePane workspace;

    private HBox status;

    private HBox controlPanel;

    private HBox editPanel;

    private Label editPanelLabel;

    private CoordinatePane coordPane;

    private guiObject selectedNode = null;


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "Cookie IDE" );
        window = new BorderPane();
        scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );
        stage.setResizable( false );

        workspace = new WorkspacePane();
        workspace.setStyle( "-fx-background-color: #ffffff" );
        workspace.setBorder( new Border( new BorderStroke( Color.BLACK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            BorderWidths.DEFAULT ) ) );

        menu = menu( stage, workspace );
        status = status( workspace );
        controlPanel = controlPanel( stage, workspace );
        coordPane = new CoordinatePane();
        editPanel = editPanel( workspace );

        ScrollPane scroll = new ScrollPane();
        HBox hbox = new HBox();
        hbox.setAlignment( Pos.CENTER );
        hbox.getChildren().add( workspace );
        scroll.setContent( hbox );

        window.setTop( menu );
        window.setCenter( scroll );
        window.setBottom( status );
        window.setLeft( controlPanel );
        window.setRight( editPanel );

        stage.setTitle( "Cookie IDE" );
        stage.setScene( scene );
        stage.show();

        workspace.setMinWidth( 500 );
        workspace.setMaxWidth( 500 );
        workspace.setMinHeight( 500 );
        workspace.setMaxHeight( 500 );

        setSize( stage, workspace );

        scroll.setMinWidth( scroll.getBoundsInParent().getWidth() );
        scroll.setMinHeight( scroll.getBoundsInParent().getHeight() );

        hbox.minWidthProperty().bind( scroll.minWidthProperty() );
        hbox.minHeightProperty().bind( scroll.minHeightProperty() );
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
                                        ( (guiObject)tGroup.getSelected() )
                                            .getWidth() ) );
                                coordPane.getHeightLabel()
                                    .setText( Double.toString(
                                        ( (guiObject)tGroup.getSelected() )
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
        controlPanel.add( new controlButton( "ImageView",
            workspace,
            guiImageView.class,
            nodeList,
            stage ), 0, 4 );

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


    private void setSize( Stage stage, WorkspacePane workspace )
    {
        final Stage popup = new Stage();
        popup.setResizable( false );
        popup.initModality( Modality.APPLICATION_MODAL );
        popup.initOwner( stage );
        VBox vbix = new VBox();
        vbix.setSpacing( 50 );
        vbix.setPadding( new Insets( 10, 10, 10, 10 ) );
        GridPane griddy = new GridPane();
        griddy.setVgap( 10 );
        griddy.setHgap( 10 );
        griddy.add( new Label( "Width: " ), 0, 0 );
        griddy.add( new Label( "Height: " ), 0, 1 );
        TextField widthField = new TextField();
        TextField heightField = new TextField();
        griddy.add( widthField, 1, 0 );
        griddy.add( heightField, 1, 1 );
        Button butt = new Button( "Submit" );
        butt.setOnAction( e -> {
            if ( ( widthField.getText().matches( "[0-9]+" )
                && widthField.getText().length() > 0 )
                || ( heightField.getText().matches( "[0-9]+" )
                    && heightField.getText().length() > 0 ) )
            {
                workspace.setMinWidth( Math.max(
                    ( Double.valueOf( widthField.getText() ).doubleValue() ),
                    150.0 ) );
                workspace.setMaxWidth( Math.max(
                    ( Double.valueOf( widthField.getText() ).doubleValue() ),
                    150 ) );
                workspace.setMinHeight( Math.max(
                    ( Double.valueOf( heightField.getText() ).doubleValue() ),
                    150 ) );
                workspace.setMaxHeight( Math.max(
                    ( Double.valueOf( heightField.getText() ).doubleValue() ),
                    150 ) );

                popup.close();
            }
            else
            {
                Notification meme = new Notification();
                meme.initializationFail();
            }

        } );
        vbix.getChildren().addAll( griddy, butt );
        Scene popupScene = new Scene( vbix );
        popup.setScene( popupScene );
        popup.show();
    }


    public Scene getScene()
    {
        return scene;
    }

}