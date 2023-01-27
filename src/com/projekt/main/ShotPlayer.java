package com.projekt.main;

import java.awt.*;
import java.util.Random;

public class ShotPlayer extends Objects
{
    private Handler handler;

    public ShotPlayer(int x, int y, IDs id, Handler handler)
    {
        super(x, y, id);

        speedX = 0;
        speedY = -7;

        this.handler = handler;
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, 8, 16);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 8, 16);
    }

    @Override
    public void shoot(int x, int y) {

    }
}
