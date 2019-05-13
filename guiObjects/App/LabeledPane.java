package App;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class LabeledPane extends GridPane
{
    private TextField textField;

    private Button applyButton;


    public LabeledPane()
    {
        this.setVgap( 10 );
        this.setHgap( 10 );
        this.setAlignment( Pos.TOP_LEFT );

        textField = new TextField();

        this.addRow( 0, new Label( "Text: " ), textField );

        applyButton = new Button( "Apply" );
        this.addRow( 1, applyButton );
    }


    public TextField getTextField()
    {
        return textField;
    }


    public Button getApplyButton()
    {
        return applyButton;
    }
}
