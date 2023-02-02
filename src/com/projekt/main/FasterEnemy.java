package com.projekt.main;

import javax.swing.*;
import java.awt.*;

public class FasterEnemy extends Objects
{
    private Image fasterEnemyImage;

    public FasterEnemy(int x, int y, IDs id)
    {
        super(x, y, id);
        loadImage();

        speedX = 8;
        speedY = 8;
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("src/sprites/fasterEnemy.png");
        fasterEnemyImage = ii.getImage();
    }


    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        if(x <= 0 || x >= Game.WIDTH - 40)
            speedX *= -1;

        if(y <= 0 || y >= Game.HEIGHT - 64)
            speedY *= -1;
    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(Color.cyan);
//        graphics.fillRect(x, y, 24, 24);
        graphics.drawImage(fasterEnemyImage, x, y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 24, 24);
    }

    @Override
    public void shoot(int x, int y) {

    }
}
