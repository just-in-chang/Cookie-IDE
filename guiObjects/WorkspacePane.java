package guiObjects;

import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class WorkspacePane extends Pane
{
    private double x;
    private double y;
    
    SelectableGroup group;
    public WorkspacePane()
    {
        super();
        group = new SelectableGroup();
        this.setOnMouseMoved( e -> {
            x = e.getX();
            y = e.getY();
        });
    }
    
    public void addSelection( guiObject obj )
    {
        group.add( obj );
        ((Node)obj).setOnMouseClicked( e -> {
            group.setSelected( obj );
        });
    }
    
    public SelectableGroup getToggleGroup()
    {
        return group;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setX( double x )
    {
        this.x = x;
    }
    
    public void setY( double y )
    {
        this.y = y;
    }
}
