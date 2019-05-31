package guiObjects;

import javafx.scene.control.TextField;


/**
 *  textfield that can be moved around and resized (in width)
 *
 *  @author  Andrew Chen
 */
public class guiTextField extends TextField implements guiObject
{
    private String name;

    private boolean select;


    /**
     * Constructs a guiTextField with given text/name and sets its manipulability
     * @param text
     */
    public guiTextField( String text )
    {
        super( text );
        name = text;
        select = false;
        m.textFieldManipulate( this );
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
    public void setName( String name )
    {
        this.name = name;

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
