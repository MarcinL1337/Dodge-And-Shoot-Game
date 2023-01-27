package com.projekt.main;

import javax.swing.*;
import java.awt.*;

public class NormalEnemy extends Objects
{
    private Image normalEnemyImage;

    public NormalEnemy(int x, int y, IDs id)
    {
        super(x, y, id);
        loadImage();

        speedX = 5;
        speedY = 5;
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("src/sprites/normalEnemy.png");
        normalEnemyImage = ii.getImage();
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        if(y <= 0 || y >= Game.HEIGHT - 74)
            speedY *= -1;

        if(x <= 0 || x >= Game.WIDTH - 52)
            speedX *= -1;
    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(Color.red);
//        graphics.fillRect(x, y, 32, 32);
        graphics.drawImage(normalEnemyImage, x, y, null);
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
