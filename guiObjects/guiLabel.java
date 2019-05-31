package guiObjects;

import javafx.scene.control.Label;


/**
 * label that can be manipulated in size and position
 *
 * @author Andrew Chen
 */
public class guiLabel extends Label implements guiObject
{
    private String name;

    private boolean select;


    /**s
     * Constructs a guiLabel with a given name and sets its manipulability
     * @param label name to set
     */
    public guiLabel( String label )
    {
        super( label );
        name = label;
        select = false;
        m.setManipulate( this, false );
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#getName()
     */
    public String getName()
    {
        return name;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#setName(java.lang.String)
     */
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
