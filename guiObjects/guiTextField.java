package guiObjects;

import Miscellaneous.Manipulate;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


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
