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


    public boolean add( guiObject node )
    {
        group.add( node );
        if ( group.size() == 1 )
        {
            node.setSelection( true );
        }
        return true;
    }


    public void setSelected( guiObject toggle )
    {
        for ( guiObject t : group )
        {
            if ( t.equals( toggle ) )
                t.setSelection( true );
            else
                t.setSelection( false );
        }
    }


    public guiObject getSelected()
    {
        guiObject selected = null;
        for ( guiObject t : group )
        {
            if ( t.isThisSelected() )
                selected = t;
        }
        return selected;
    }


    public int getSelectedIndex()
    {
        return group.indexOf( getSelected() );
    }
}
