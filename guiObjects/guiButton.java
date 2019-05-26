package guiObjects;

import javafx.scene.control.Button;


/**
 * button that can be manipulated in size and position
 *
 * @author Andrew
 * @version May 8, 2019
 * @author Period: 1
 * @author Assignment: Cookie IDE
 *
 * @author Sources: TODO
 */
public class guiButton extends Button implements guiObject
{

    private String name;

    private boolean select;


    public guiButton()
    {
        this( "" );
    }


    public guiButton( String label )
    {
        super( label );
        name = label;
        select = false;
        m.setManipulate( this, true );
    }


    @Override
    public String getName()
    {
        return name;
    }


    @Override
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
