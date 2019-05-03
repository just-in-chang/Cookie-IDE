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

    public void addMouseDragEvent( Control...controls )
    {
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle( MouseEvent e )
            {
                Control src = ((Control)e.getSource());
                src.setTranslateX( e.getSceneX() - src.getWidth()/2 - src.getLayoutX());
                src.setTranslateY( e.getSceneY() - src.getHeight() * 1.5 - src.getLayoutY());
            }
        };
        for ( Control control : controls )
        {
            control.addEventFilter( MouseEvent.MOUSE_DRAGGED ,event);
        }
    }
    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "lmao" );
        BorderPane window = new BorderPane();
        Scene scene = new Scene( window, 1280, 720 );
        stage.setScene( scene );
        
        MenuBar menu = new MenuBar();
        
        
        StackPane v = new StackPane();
        Button b1 = new Button( "one" );
        Button b2 = new Button( "two" );
        Label l1 = new Label("owo");

        addMouseDragEvent(b1,b2,l1);
        
        v.getChildren().addAll( b1, b2, l1 );
        window.setCenter( v );
        
        stage.show();
    }

}
