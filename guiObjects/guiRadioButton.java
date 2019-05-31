package guiObjects;

import javafx.scene.control.RadioButton;


/**
 *  radiobutton that can be moved around
 *
 *  @author  Andrew Chen
 */
public class guiRadioButton extends RadioButton implements guiObject
{
    private String name;

    private boolean select;


    /**
     * Constructs guiRadioButton with given name and sets its manipulability
     * @param label
     */
    public guiRadioButton( String label )
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
