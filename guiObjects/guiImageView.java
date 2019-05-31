package guiObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *  an imageview that can be modified in position and be set its URL
 *
 *  @author  Andrew Chen
 */
public class guiImageView extends ImageView implements guiObject
{

    private String name;

    private boolean select;
    
    private String URL;


    /**
     * Constructs an guiImageView with given name and a default image
     * @param label name to set
     */
    public guiImageView( String label )
    {
        super( new Image( "https://i.redd.it/i2q0r20gyeo21.png" ) );
        URL = "https://i.redd.it/i2q0r20gyeo21.png";
        m.imageManipulate( this );
        name = label;
        select = false;
    }
    
    /**
     * sets the current image url
     * @param url to set the image
     */
    public void setURL(String url)
    {
        Image i = new Image(url);
        this.setImage( i );
        URL = url;
    }

    /**
     * gets the current image url
     * @return URL - current image url
     */
    public String getURL()
    {
        return URL;
    }

    /* (non-Javadoc)
     * @see guiObjects.guiObject#getName()
     */
    @Override
    public String getName()
    {
        return name;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#setName(java.lang.String)
     */
    @Override
    public void setName( String n )
    {
        name = n;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#isThisSelected()
     */
    @Override
    public boolean isThisSelected()
    {
        return select;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#setSelection(boolean)
     */
    @Override
    public void setSelection( boolean b )
    {
        select = b;
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#getWidth()
     */
    public double getWidth()
    {
        return this.getImage().getWidth();
    }


    /* (non-Javadoc)
     * @see guiObjects.guiObject#getHeight()
     */
    public double getHeight()
    {
        return this.getImage().getHeight();
    }
}
