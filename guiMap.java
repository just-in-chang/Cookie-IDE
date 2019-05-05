import java.util.HashMap;


/**
 * 
 * List that holds guiComponent objects.
 *
 * @author Justin Chang
 * @version May 3, 2019
 * @author Period: 1
 * @author Assignment: Cookie IDE
 *
 * @author Sources: TODO
 */
public class guiMap extends HashMap implements guiComponent
{
    private boolean vertical;

    private double size;

    private double startingX;

    private double startingY;


    public guiMap( boolean v, double s, double x, double y )
    {
        vertical = v;
        size = s;
        startingX = x;
        startingY = y;
    }


    public boolean isVertical()
    {
        return vertical;
    }


    public void setVertical( boolean vertical )
    {
        this.vertical = vertical;
    }


    public double getSize()
    {
        return size;
    }


    public void setSize( double size )
    {
        this.size = size;
    }


    public double getStartingX()
    {
        return startingX;
    }


    public void setStartingX( double startingX )
    {
        this.startingX = startingX;
    }


    public double getStartingY()
    {
        return startingY;
    }


    public void setStartingY( double startingY )
    {
        this.startingY = startingY;
    }

}
