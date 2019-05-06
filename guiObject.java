import java.awt.Color;


/**
 * Wrapper class for a GUI object from JavaFX.
 *
 * @author Justin Chang
 * @version May 3, 2019
 * @author Period: 1
 * @author Assignment: Cookie IDE
 *
 * @author Sources: TODO
 */
public class guiObject implements guiComponent
{
    private String type;

    private Color color;

    private double size;

    private double startingX;

    private double startingY;


    /**
     * Constructor.
     * 
     * @param t
     *            Type.
     * @param c
     *            Color.
     * @param s
     *            Size.
     * @param x
     *            Starting x.
     * @param y
     *            Starting y.
     */
    public guiObject( String t, Color c, double s, double x, double y )
    {
        type = t;
        color = c;
        size = s;
        startingX = x;
        startingY = y;
    }


    public String getType()
    {
        return type;
    }


    public void setType( String type )
    {
        this.type = type;
    }


    public Color getColor()
    {
        return color;
    }


    public void setColor( Color color )
    {
        this.color = color;
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
