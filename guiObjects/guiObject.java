package guiObjects;

import Miscellaneous.Manipulate;


/**
 * interface for all nodes to be added during runtime; specifies which methods
 * need to be defined in each
 *
 * @author Andrew Chen
 * @version May 29, 2019
 * @author Period: 1
 * @author Assignment: Cookie IDE
 *
 * @author Sources: TODO
 */
public interface guiObject
{
    /**
     * returns the name generated when added to the workspace
     * 
     * @return
     */
    public String getName();


    /**
     * sets the name
     * 
     * @param n
     */
    public void setName( String n );


    /**
     * checks whether the node is selected or not
     * 
     * @return true if yes, false if no
     */
    public boolean isThisSelected();


    /**
     * sets if the current node is to be the one selected or not
     * 
     * @param b
     *            true if yes, false if no
     */
    public void setSelection( boolean b );


    /**
     * returns the width of the node
     * 
     * @return double value indicating width
     */
    public double getWidth();


    /**
     * returns the height of the node
     * 
     * @return double value indicating height
     */
    public double getHeight();

    public final Manipulate m = new Manipulate();
}
