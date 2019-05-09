package Miscellaneous;
import java.util.Stack;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;


public class clickedHandler implements EventHandler<Event>
{

    Stack<Control> clicked;


    public clickedHandler( Stack<Control> stack )
    {
        clicked = stack;
    }


    @Override
    public void handle( Event e )
    {
        clicked.push( (Control)e.getSource() );
    }
}