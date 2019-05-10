package guiObjects;

import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class WorkspacePane extends Pane
{
    SelectableGroup group;
    public WorkspacePane()
    {
        super();
        group = new SelectableGroup();
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
}
