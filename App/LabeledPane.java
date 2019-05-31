package App;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * Pane for editing Labeled Nodes
 *
 * @author Justin
 */
public class LabeledPane extends GridPane
{
    private TextField textField;

    private Button applyButton;


    /**
     * Creates a Pane to edit labeled node.
     * 
     * @param labelName
     *            Name of node
     */
    public LabeledPane( String labelName )
    {
        this.setVgap( 10 );
        this.setHgap( 10 );
        this.setAlignment( Pos.TOP_LEFT );

        textField = new TextField();

        this.addRow( 0, new Label( labelName ), textField );

        applyButton = new Button( "Apply" );
        this.addRow( 1, applyButton );
    }


    /**
     * 
     * Returns textField.
     * 
     * @return textField
     */
    public TextField getTextField()
    {
        return textField;
    }


    /**
     * 
     * Returns the apply button to make edits.
     * 
     * @return Apply button.
     */
    public Button getApplyButton()
    {
        return applyButton;
    }
}
