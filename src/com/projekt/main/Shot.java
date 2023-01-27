package com.projekt.main;

import java.awt.*;
import java.util.Random;

public class Shot extends Objects
{
    private Handler handler;
    Random rand = new Random();

    public Shot(int x, int y, IDs id, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;

        speedX = (rand.nextInt(5 - -5) + -5);
        speedY = 7;
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        if(y >= Game.HEIGHT)
            handler.popObj(this);
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.setColor(Color.gray);
        graphics.fillRect(x, y, 16, 32);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void shoot(int x, int y) {

    }
}
