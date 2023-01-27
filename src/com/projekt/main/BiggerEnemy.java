package com.projekt.main;

import javax.swing.*;
import java.awt.*;

public class BiggerEnemy extends Objects
{
    private Image biggerEnemyImage;

    public BiggerEnemy(int x, int y, IDs id)
    {
        super(x, y, id);
        loadImage();

        speedX = (int) 3.5;
        speedY = (int) 3.5;
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("src/sprites/biggerEnemy.png");
        biggerEnemyImage = ii.getImage();
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        if(y <= 0 || y >= Game.HEIGHT - 100)
            speedY *= -1;

        if(x <= 0 || x >= Game.WIDTH - 82)
            speedX *= -1;
    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(Color.green);
//        graphics.fillRect(x, y, 64, 64);
        graphics.drawImage(biggerEnemyImage, x, y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }

    @Override
    public void shoot(int x, int y)
    {

    }
}
