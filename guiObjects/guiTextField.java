package guiObjects;

import Miscellaneous.Manipulate;
import javafx.scene.control.TextField;


public class guiTextField extends TextField implements guiObject
{
    private String name;

    private boolean select;


    public guiTextField()
    {
        this( "" );
    }


    public guiTextField( String text )
    {
        super( text );
        name = text;
        select = false;
        Manipulate m = new Manipulate();
        m.setManipulate( this, false );
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
    public boolean isSelected()
    {
        return select;
    }


    @Override
    public void setSelected( boolean b )
    {
        select = b;
    }

}
