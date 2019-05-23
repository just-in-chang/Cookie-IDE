package guiObjects;

import Miscellaneous.Manipulate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class guiImageView extends ImageView implements guiObject
{
    
    private String name;
    
    private boolean select;
        
    public guiImageView( String label )
    {
        super(new Image("https://cdn.discordapp.com/emojis/535294218348789760.png"));
        Manipulate m = new Manipulate();
        m.imageManipulate( this );
        name = label;
        select = false;
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
    public boolean isSelected()
    {
        return select;
    }


    @Override
    public void setSelected( boolean b )
    {
        select = b;
    }
    
    public double getWidth()
    {
        return this.getImage().getWidth();
    }
    
    public double getHeight()
    {
        return this.getImage().getHeight();
    }
}
