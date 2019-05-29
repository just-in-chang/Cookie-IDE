package guiObjects;

import Miscellaneous.Manipulate;

public interface guiObject
{
    public String getName();


    public void setName( String n );


    public boolean isThisSelected();


    public void setSelection( boolean b );
    
    public double getWidth();
    
    public double getHeight();
    
    public final Manipulate m = new Manipulate();
}
