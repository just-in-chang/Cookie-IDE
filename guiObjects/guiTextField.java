package guiObjects;

import javafx.scene.control.TextField;


public class guiTextField extends TextField implements guiObject
{
    private String name;

    private boolean select;


    public guiTextField( String text )
    {
        super( text );
        name = text;
        select = false;
        m.textFieldManipulate( this );
    }


    @Override
    public String getName()
    {
        return name;
    }


    @Override
    public void setName( String name )
    {
        this.name = name;

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
