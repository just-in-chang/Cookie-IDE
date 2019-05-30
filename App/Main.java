package App;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

import Miscellaneous.Notification;
import Miscellaneous.SelectableGroup;
import guiObjects.controlButton;
import guiObjects.guiButton;
import guiObjects.guiCheckBox;
import guiObjects.guiImageView;
import guiObjects.guiLabel;
import guiObjects.guiObject;
import guiObjects.guiRadioButton;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    private Scene scene;

    private VBox menu;

    private WorkspacePane workspace;

    private HBox status;

    private HBox controlPanel;

    private HBox editPanel;

    private Label editPanelLabel;

    private CoordinatePane coordPane;

    private guiObject selectedNode = null;

    private String serverIP;


    /*
     * (non-Javadoc)
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
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


    /**
     * Creates the menu bar.
     * 
     * @param stage
     *            application stage
     * @param workspace
     *            pane to gather node information to compile into code
     * @return
     */
    public VBox menu( Stage stage, Pane workspace )
    {
        VBox menuBar = new VBox();
        MenuBar menu = new MenuBar();
        Menu file = new Menu( "File" );
        MenuItem save = new MenuItem( "Save" );
        MenuItem open = new MenuItem( "Save Backup" );
        file.getItems().addAll( save, open );
        menu.getMenus().addAll( file );
        menuBar.getChildren().addAll( menu );

        save.setOnAction( e -> {
            Compiler compile = new Compiler( serverIP );
            LinkedList<Node> nodeList = new LinkedList<Node>();
            for ( Node n : this.workspace.getChildren() )
            {
                System.out.println( ( (guiObject)n ).getName() );
                nodeList.add( n );
            }
            compile.send( nodeList, workspace, stage );
        } );

        open.setOnAction( e -> {
            Compiler compile = new Compiler( serverIP );
            compile.open( stage );
        } );

        return menuBar;
    }


    /**
     * creates the status bar (at bottom) containing current mouse coordinates
     * and the currently selected node
     * 
     * @param workspace
     *            pane containing nodes to select
     * @return HBox containing labels that indicate mouse x,y and selected node
     */
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

                            mouseX.setText( "X: "
                                + ( (WorkspacePane)workspace ).getMouseX() );
                            mouseY.setText( "Y: "
                                + ( (WorkspacePane)workspace ).getMouseY() );

                            SelectableGroup tGroup = ( (WorkspacePane)workspace )
                                .getSelectableGroup();
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
                                        tGroup.getSelected().getWidth() ) );
                                coordPane.getHeightLabel()
                                    .setText( Double.toString(
                                        tGroup.getSelected().getHeight() ) );
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


    /**
     * left hand side of the application containing buttons to create new nodes
     * in the workspace
     * 
     * @param stage
     *            application stage
     * @param workspace
     *            pane to add nodes to
     * @return an HBox containing all the buttons that add nodes
     */
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

            stage ), 0, 0 );
        controlPanel.add( new controlButton( "Label",
            workspace,
            guiLabel.class,

            stage ), 0, 1 );
        controlPanel.add( new controlButton( "TextField",
            workspace,
            guiTextField.class,

            stage ), 0, 2 );
        controlPanel.add( new controlButton( "ImageView",
            workspace,
            guiImageView.class,

            stage ), 0, 4 );
        controlPanel.add( new controlButton( "RadioButton",
            workspace,
            guiRadioButton.class,

            stage ), 0, 5 );
        controlPanel.add( new controlButton( "CheckBox",
            workspace,
            guiCheckBox.class,

            stage ), 0, 6 );

        controlPanel.add( new Separator( Orientation.HORIZONTAL ), 0, 3 );

        for ( Node ass : controlPanel.getChildren() )
        {
            if ( ass instanceof controlButton )
                ( (controlButton)ass ).setMinWidth( 80 );
        }

        controlPanel.setPadding( new Insets( 25, 25, 25, 25 ) );
        return controlBox;
    }


    /**
     * right hand side of the application which displays currently selected node
     * and properties that can be changed (label text, image, etc.)
     * 
     * @param workspace
     *            pane which contains nodes
     * @return an HBox containing all elements that make up and display node
     *         information
     */
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

        guiObject selected = ( (WorkspacePane)workspace ).getSelectableGroup()
            .getSelected();

        if ( selected instanceof Labeled )
        {
            LabeledPane labelPane = new LabeledPane( "Text: " );
            ePane.getChildren().addAll( labelPane );

            labelPane.getApplyButton().setOnAction( e -> {
                ( (Labeled)selectedNode )
                    .setText( labelPane.getTextField().getText() );
            } );
        }
        else if ( selected instanceof ImageView )
        {
            LabeledPane labelPane = new LabeledPane( "URL: " );
            ePane.getChildren().addAll( labelPane );

            labelPane.getTextField()
                .setText( ( (guiImageView)selected ).getURL() );

            labelPane.getApplyButton().setOnAction( e -> {
                ( (guiImageView)selectedNode )
                    .setURL( labelPane.getTextField().getText() );
            } );
        }

        return rePane;
    }


    /**
     * sets the size of the workspace pane (set during the starting of the
     * application); also sets server ip to connect to in order to save and
     * compile
     * 
     * @param stage
     *            application stage
     * @param workspace
     *            pane where nodes are to be added and modified
     */
    private void setSize( Stage stage, WorkspacePane workspace )
    {
        final Stage popup = new Stage();
        popup.setResizable( false );
        popup.initModality( Modality.APPLICATION_MODAL );
        popup.initOwner( stage );
        VBox vbix = new VBox();
        vbix.setSpacing( 10 );
        vbix.setPadding( new Insets( 10, 10, 10, 10 ) );
        GridPane griddy = new GridPane();
        griddy.setVgap( 10 );
        griddy.setHgap( 10 );
        griddy.add( new Label( "Width: " ), 0, 0 );
        griddy.add( new Label( "Height: " ), 0, 1 );
        griddy.add( new Label( "IP of Server: " ), 0, 2 );
        TextField widthField = new TextField( "500" );
        TextField heightField = new TextField( "500" );

        griddy.add( widthField, 1, 0 );
        griddy.add( heightField, 1, 1 );
        try
        {
            InetAddress IP = InetAddress.getLocalHost();
            serverIP = IP.getHostAddress();
        }
        catch ( UnknownHostException e1 )
        {
            e1.printStackTrace();
        }
        TextField ipField = new TextField( serverIP );
        griddy.add( ipField, 1, 2 );
        Button butt = new Button( "Submit" );
        butt.setMinWidth( griddy.getBoundsInParent().getWidth() );
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

                if ( ipValidation( ipField.getText() ) )
                {
                    serverIP = ipField.getText();
                }

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


    /**
     * validates the given ip to make sure it can be connected to
     * 
     * @param ip
     *            to validate
     * @return true or false indicating if the ip is validated or not
     */
    private static boolean ipValidation( String ip )
    {
        try
        {
            if ( ip == null || ip.isEmpty() )
            {
                return false;
            }

            String[] str = ip.split( "\\." );
            if ( str.length != 4 )
            {
                return false;
            }

            for ( String s : str )
            {
                int i = Integer.parseInt( s );
                if ( ( i < 0 ) || ( i > 255 ) )
                {
                    return false;
                }
            }
            if ( ip.endsWith( "." ) )
            {
                return false;
            }

            return true;
        }
        catch ( Exception ex )
        {
            return false;
        }
    }

}