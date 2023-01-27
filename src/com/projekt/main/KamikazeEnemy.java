package com.projekt.main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class KamikazeEnemy extends Objects
{
    Random rand = new Random();
    private Handler handler;

    private Image kamikazeEnemyImage;

    public KamikazeEnemy(int x, int y, IDs id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;

        speedX = (rand.nextInt(2 - -2) + -2);
        speedY = 6;

        loadImage();
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("src/sprites/kamikazeEnemy.png");
        kamikazeEnemyImage = ii.getImage();
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        if(y >= Game.HEIGHT)
            handler.popObj(this);

        if(x <= 0 || x >= Game.WIDTH)
            handler.popObj(this);
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.drawImage(kamikazeEnemyImage, x, y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void shoot(int x, int y)
    {

    }
}
