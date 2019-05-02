import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


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


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "lmao" );
        BorderPane b = new BorderPane();
        // <--------------menubar--------------->
        VBox h = new VBox();
        MenuBar menuBar = new MenuBar();
        Menu menuF = new Menu( "File" );
        MenuItem lol = new MenuItem( "lol" );
        menuF.getItems().add( lol );
        Menu menuE = new Menu( "Edit" );
        Menu menuV = new Menu( "View" );

        menuBar.getMenus().addAll( menuF, menuE, menuV );
        // <------------------------------------>
        VBox v = new VBox();
        Button b1 = new Button( "one" );
        Button b2 = new Button( "two" );

        EventHandler<MouseEvent> yeet = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle( MouseEvent e )
            {
                ((Button)e.getSource()).setTranslateX( e.getSceneX() - ((Button)e.getSource()).getWidth()/2 - ((Button)e.getSource()).getLayoutX());
                ((Button)e.getSource()).setTranslateY( e.getSceneY() - ((Button)e.getSource()).getHeight() * 1.5 - ((Button)e.getSource()).getLayoutY());
            }
        };
        b1.addEventFilter( MouseEvent.MOUSE_DRAGGED, yeet );
        b2.addEventFilter( MouseEvent.MOUSE_DRAGGED, yeet );
        v.getChildren().addAll( b1, b2 );
        b.setCenter( v );
        h.getChildren().add( menuBar );
        b.setTop( h );
        Scene scene = new Scene( b, 400, 300 );
        stage.setScene( scene );
        stage.show();

    }

}
