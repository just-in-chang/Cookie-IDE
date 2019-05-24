import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class out extends Application
{
    public static void main( String[] args )
    {
        launch( args );
    }


    @Override
    public void start( Stage stage )
    {
        stage.setTitle( "" ); // TODO Put title of window here. 
        Pane pane = new Pane();
        Scene scene = new Scene( pane, 500.0, 500.0);
        pane.setStyle( "-fx-background-color: #ffffff" );
        stage.setScene( scene );


        stage.show();
    }
}
