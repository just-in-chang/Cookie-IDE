package guiObjects;

import javafx.scene.control.CheckBox;


/**
 *  checkbox that can be moved around
 *
 *  @author  Andrew Chen
 */
public class guiCheckBox extends CheckBox implements guiObject
{
    private String name;

    private boolean select;


    /**
     * Constructs guiCheckBox with given name and sets its manipulability
     * @param label name to set
     */
    public guiCheckBox( String label )
    {
        super( label );
        name = label;
        select = false;
        m.setManipulate( this, false );
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
