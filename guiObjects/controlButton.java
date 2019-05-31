package guiObjects;

import App.WorkspacePane;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * creates a button to add an instance of a given class to the workspace pane
 *
 * @author Andrew Chen
 */
public class controlButton extends Button
{

    private int count = 1;


    /**
     * constructs a button to add an instance of given class to a given
     * workspace
     * 
     * @param text
     *            to display on the button
     * @param workspace
     *            to add an instance of the given class
     * @param c
     *            class to create a new instance of
     * @param s
     *            stack to add instance to in order to be able to save the
     *            configuration
     */
    public controlButton( String text, Pane workspace, Class<?> c, Stage stage )
    {
        super( text );
        this.setOnMouseClicked( e -> {
            try
            {
                Node node = (Node)c.getConstructor( String.class )
                    .newInstance( text + count );
                workspace.getChildren().add( node );
                ( (WorkspacePane)workspace ).addSelection( (guiObject)node );
                count++;
            }
            catch ( Exception ex )
            {
                System.out.println( ex );
            }
        } );
    }
}
