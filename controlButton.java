import java.util.Stack;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.*;


public class controlButton extends Button
{
    
    private int count = 1;

    public controlButton( String text, Pane workspace, Class<?> c, Stack<Node> s )
    {
        super( text );
        this.setOnMouseClicked( e -> {
            try
            {
                Control node = (Control)c.getConstructor( String.class )
                    .newInstance( text + count );
                workspace.getChildren().add( node );
                count++;
                s.push( node );
            }
            catch ( Exception ex )
            {
                System.out.println( ex );
            }
        } );
    }
}
