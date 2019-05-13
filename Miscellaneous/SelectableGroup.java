package Miscellaneous;

import java.util.ArrayList;

import guiObjects.guiObject;


public class SelectableGroup
{
    private ArrayList<guiObject> group;


    public SelectableGroup()
    {
        group = new ArrayList<guiObject>();
    }


    public boolean add( guiObject toggle )
    {
        group.add( toggle );
        if ( group.size() == 1 )
        {
            toggle.setSelected( true );
        }
        return true;
    }


    public void setSelected( guiObject toggle )
    {
        for ( guiObject t : group )
        {
            if ( t.equals( toggle ) )
                t.setSelected( true );
            else
                t.setSelected( false );
        }
    }


    public guiObject getSelected()
    {
        guiObject selected = null;
        for ( guiObject t : group )
        {
            if ( t.isSelected() )
                selected = t;
        }
        return selected;
    }


    public int getSelectedIndex()
    {
        return group.indexOf( getSelected() );
    }
}
