package ServerNetworking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Test extends Application
{
    private Scene scene, scene2;


    public static void main( String[] args )
    {
        launch( args );
    }


    @Override
    public void start( Stage stage )
    {
        Button butt = new Button();
        butt.setText( "touch me" );
        butt.setOnAction( e -> stage.setScene( scene2 ) );
        butt.setOnMouseDragged( e -> {
            butt.setTranslateX( e.getSceneX() );
            butt.setTranslateY( e.getSceneY() );
        } );

        Button butt1 = new Button();
        butt1.setText( "touch me daddy" );
        butt1.setOnAction( e -> {
            stage.setScene( scene );
        } );
        // layout 1
        VBox pane = new VBox( 20 );
        pane.getChildren().addAll( butt );
        scene = new Scene( pane, 300, 250 );

        // layout2
        VBox layout2 = new VBox( 20 );
        layout2.getChildren().add( butt1 );
        scene2 = new Scene( layout2, 300, 250 );

        stage.setScene( scene );
        stage.show();
    }

}
