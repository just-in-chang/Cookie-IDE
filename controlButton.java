import java.util.Stack;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.*;


/**
 *  creates a button to add an instance of a given class to a given javafx Pane
 *
 *  @author  Andrew Chen
 *  @version May 7, 2019
 *  @author  Period: 1
 *  @author  Assignment: Cookie IDE
 *
 *  @author  Sources: TODO
 */
public class controlButton extends Button
{
    
    private int count = 1;

    /**
     * constructs a button to add an instance of given class to a given workspace
     * @param text to display on the button
     * @param workspace to add an instance of the given class
     * @param c class to create a new instance of
     * @param s stack to add instance to in order to be able to save the configuration
     */
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
