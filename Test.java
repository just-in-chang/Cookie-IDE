import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Test extends Application
{
    /**
     * main class lmao
     * @param args command line arguments USED!
     */
    public static void main( String[] args )
    {
        launch(args);
    }


    @Override
    public void start( Stage arg0 )
    {
        arg0.setTitle( "lmao" );
        Button button = new Button("ree");
        
        Pane layout = new Pane();
        layout.getChildren().add( button );
        
        
    }

}
