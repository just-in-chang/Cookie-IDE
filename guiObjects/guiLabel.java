package guiObjects;

import javafx.scene.control.Label;


/**
 * label that can be manipulated in size and position
 *
 * @author Andrew
 * @version May 8, 2019
 * @author Period: 1
 * @author Assignment: Cookie IDE
 *
 * @author Sources: TODO
 */
public class guiLabel extends Label implements guiObject
{
    private String name;

    private boolean select;


    public guiLabel()
    {
        this( "" );
    }


    public guiLabel( String label )
    {
        super( label );
        name = label;
        select = false;
        m.setManipulate( this, false );
    }


    public String getName()
    {
        return name;
    }


    public void setName( String n )
    {
        name = n;
    }


    @Override
    public boolean isThisSelected()
    {
        return select;
    }


    @Override
    public void setSelection( boolean b )
    {
        select = b;
    }

}
