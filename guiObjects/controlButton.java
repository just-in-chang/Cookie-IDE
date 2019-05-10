package guiObjects;

import java.util.Stack;

import App.Main;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * creates a button to add an instance of a given class to a given javafx Pane
 *
 * @author Andrew Chen
 * @version May 7, 2019
 * @author Period: 1
 * @author Assignment: Cookie IDE
 *
 * @author Sources: TODO
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
    public controlButton(
        String text, Pane workspace,
        Class<?> c,
        Stack<Node> s,
        Stage stage )
    {
        super( text );
        this.setOnMouseClicked( e -> {
            try
            {
                Control node = (Control)c.getConstructor( String.class )
                    .newInstance( text + count );
//              editWindow( stage, workspace );
                workspace.getChildren().add( node );
                ((WorkspacePane)workspace).addSelection( (guiObject)node );
                s.push( node );
                count++;
            }
            catch ( Exception ex )
            {
                System.out.println( ex );
            }
        } );
    }


    public void editWindow( Stage stage, Pane workspace )
    {
        final Stage popup = new Stage();

        popup.initModality( Modality.APPLICATION_MODAL );
        popup.initOwner( stage );

        VBox vb = new VBox();
        TextField name = new TextField( ( (Labeled)workspace.getChildren()
            .get( workspace.getChildren().size() - 1 ) ).getText() );
        Button ass = new Button( "Submit" );

        ass.setOnAction( e -> {
            if ( e.getSource() instanceof Labeled )
            {
                Labeled poop = (Labeled)workspace.getChildren()
                    .get( workspace.getChildren().size() - 1 );
                poop.setText( name.getText() );
                ( (guiObject)poop ).setName( name.getText() );
                popup.close();
            }
        } );
        vb.getChildren().addAll( name, ass );

        Scene dialogScene = new Scene( vb, 300, 200 );
        popup.setScene( dialogScene );
        popup.show();
    }
}
