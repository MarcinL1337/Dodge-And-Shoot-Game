package com.projekt.main;

import java.awt.*;
import java.util.LinkedList;

// Loops through all the objects in our game, then individually updates them and renders to the screen
public class Handler
{
    LinkedList<Objects> obj = new LinkedList<Objects>();

    // Updates all of the game's objects
    public void tick()
    {
        for(int i = 0; i < obj.size(); i++)
        {
            Objects temp = obj.get(i); // sets a temp object to an i-th object we're currently at

            temp.tick();
        }
    }

    // Renders all of the game's objects
    public void render(Graphics graphics)
    {
        for(int i = 0; i < obj.size(); i++)
        {
            Objects temp = obj.get(i);

            temp.render(graphics);
        }
    }

    public void clearObjs()
    {
        for(int i = 0; i < obj.size(); i++)
        {
            Objects temp = obj.get(i);

            if(temp.getId() != IDs.Player)
            {
                this.popObj(temp);
                i--;
            }
        }
    }

    public void pushObj(Objects object)
    {
        this.obj.add(object);
    }

    public void popObj(Objects object)
    {
        this.obj.remove(object);
    }
}
