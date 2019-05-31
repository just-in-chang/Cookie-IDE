package Miscellaneous;

import java.util.ArrayList;

import guiObjects.guiObject;


/**
 *  ArrayList that holds all the nodes from the workspace pane and manages their select state
 *
 *  @author  Andrew Chen
 */
public class SelectableGroup
{
    private ArrayList<guiObject> group;


    /**
     * Constructs an ArrayList of guiObjects
     */
    public SelectableGroup()
    {
        group = new ArrayList<guiObject>();
    }


    /**
     * adds a node to the ArrayList
     * 
     * @param node
     *            node to add
     */
    public void add( guiObject node )
    {
        group.add( node );
        if ( group.size() == 1 )
        {
            node.setSelection( true );
        }
    }


    /**
     * sets the given node's selection state
     * 
     * @param toggle
     *            node to set
     */
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


    /**
     * return the currently selected node
     * 
     * @return selected node
     */
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


    /**
     * returns the index of the currently selected node
     * 
     * @return index of the selected node
     */
    public int getSelectedIndex()
    {
        return group.indexOf( getSelected() );
    }
}
