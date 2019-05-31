package App;

import Miscellaneous.SelectableGroup;
import guiObjects.guiObject;
import javafx.scene.layout.Pane;


/**
 * javafx pane with added methods needed for correct implementation
 *
 * @author Andrew Chen
 */
public class WorkspacePane extends Pane
{
    private double x;

    private double y;

    private SelectableGroup group;


    /**
     * constructs a pane with a SelectableGroup object; defines mouse behavior
     * in order to update the status bar of mouse coordinates
     */
    public WorkspacePane()
    {
        super();
        group = new SelectableGroup();
        this.setOnMouseMoved( e -> {
            x = e.getX();
            y = e.getY();
        } );

    }


    /**
     * adds given node to the SelectableGroup
     * 
     * @param obj
     *            node to be added
     */
    public void addSelection( guiObject obj )
    {
        group.add( obj );
    }


    /**
     * returns the SelectableGroup
     * 
     * @return SelectableGroup
     */
    public SelectableGroup getSelectableGroup()
    {
        return group;
    }


    /**
     * returns current mouse x coordinate in this pane
     * 
     * @return mouse x coordinate
     */
    public double getMouseX()
    {
        return x;
    }


    /**
     * returns current mouse y coordinate in this pane
     * 
     * @return mouse y coordinate
     */
    public double getMouseY()
    {
        return y;
    }


    /**
     * sets mouse x coordinate because when hovering over nodes in this pane,
     * mouse events will not fire for this pane
     * 
     * @param x
     *            coordinate to set
     */
    public void setMouseX( double x )
    {
        this.x = x;
    }


    /**
     * sets mouse y coordinate because when hovering over nodes in this pane,
     * mouse events will not fire for this pane
     * 
     * @param y
     *            coordinate to set
     */
    public void setMouseY( double y )
    {
        this.y = y;
    }
}
