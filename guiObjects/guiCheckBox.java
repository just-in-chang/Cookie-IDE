package guiObjects;

import javafx.scene.control.CheckBox;


public class guiCheckBox extends CheckBox implements guiObject
{
    private String name;

    private boolean select;


    public guiCheckBox( String label )
    {
        super( label );
        name = label;
        select = false;
        m.setManipulate( this, false );
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
