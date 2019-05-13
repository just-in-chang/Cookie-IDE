package App;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class CoordinatePane extends GridPane
{
    private Label xLabel, yLabel, widthLabel, heightLabel;


    public CoordinatePane()
    {
        this.setVgap( 10 );
        this.setHgap( 10 );
        this.setAlignment( Pos.TOP_LEFT );

        Label label1 = new Label( "X: " );
        Label label2 = new Label( "Y: " );
        Label label3 = new Label( "Width: " );
        Label label4 = new Label( "Height: " );

        label1.setStyle( "-fx-font-weight: bold " );
        label2.setStyle( "-fx-font-weight: bold " );
        label3.setStyle( "-fx-font-weight: bold " );
        label4.setStyle( "-fx-font-weight: bold " );

        xLabel = new Label( "0" );
        yLabel = new Label( "0" );
        widthLabel = new Label( "0" );
        heightLabel = new Label( "0" );

        xLabel.setMinWidth( 50 );
        xLabel.setMaxWidth( 50 );
        yLabel.setMinWidth( 50 );
        yLabel.setMaxWidth( 50 );
        widthLabel.setMinWidth( 50 );
        widthLabel.setMaxWidth( 50 );
        heightLabel.setMinWidth( 50 );
        heightLabel.setMaxWidth( 50 );

        this.addRow( 0, label1, xLabel, label2, yLabel );
        this.addRow( 1, label3, widthLabel, label4, heightLabel );
    }


    public Label getxLabel()
    {
        return xLabel;
    }


    public void setxLabel( Label xLabel )
    {
        this.xLabel = xLabel;
    }


    public Label getyLabel()
    {
        return yLabel;
    }


    public void setyLabel( Label yLabel )
    {
        this.yLabel = yLabel;
    }


    public Label getWidthLabel()
    {
        return widthLabel;
    }


    public void setWidthLabel( Label widthLabel )
    {
        this.widthLabel = widthLabel;
    }


    public Label getHeightLabel()
    {
        return heightLabel;
    }


    public void setHeightLabel( Label lengthLabel )
    {
        this.heightLabel = lengthLabel;
    }

}
