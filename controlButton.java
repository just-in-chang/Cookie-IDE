import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

public class controlButton extends Button
{
//    public controlButton()
//    {
//        super();
//    }
    int count = 1;
    
    public controlButton( String text, Pane workspace, Control control, int x, int y )
    {
        super(text);
        this.setOnMouseClicked( e -> {
            workspace.getChildren().add(  );
        });
    }
}
