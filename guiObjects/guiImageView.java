package guiObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class guiImageView extends ImageView implements guiObject
{

    private String name;

    private boolean select;
    
    private String URL;


    public guiImageView( String label )
    {
        super( new Image( "https://i.redd.it/i2q0r20gyeo21.png" ) );
        URL = "https://i.redd.it/i2q0r20gyeo21.png";
        m.imageManipulate( this );
        name = label;
        select = false;
    }
    
    public void setURL(String url)
    {
        Image i = new Image(url);
        this.setImage( i );
        URL = url;
    }

    public String getURL()
    {
        return URL;
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


    public double getWidth()
    {
        return this.getImage().getWidth();
    }


    public double getHeight()
    {
        return this.getImage().getHeight();
    }
}
