package guiObjects;

import javafx.scene.control.Button;


/**
 * button that can be manipulated in size and position
 *
 * @author Andrew Chen
 */
public class guiButton extends Button implements guiObject
{

    private String name;

    private boolean select;


    /**
     * Constructs guiButton with given name and sets its manipulability
     * @param label name to set
     */
    public guiButton( String label )
    {
        super( label );
        name = label;
        select = false;
        m.setManipulate( this, true );
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#getName()
     */
    @Override
    public String getName()
    {
        return name;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#setName(java.lang.String)
     */
    @Override
    public void setName( String n )
    {
        name = n;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#isThisSelected()
     */
    @Override
    public boolean isThisSelected()
    {
        return select;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#setSelection(boolean)
     */
    @Override
    public void setSelection( boolean b )
    {
        select = b;
    }

}
