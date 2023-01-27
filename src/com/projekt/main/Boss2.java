package com.projekt.main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Boss2 extends Objects
{
    private Random rand = new Random();
    private Handler handler;
    private HUD hud;

    private Image boss2Image;

    private double bonusDMG = 0.0;
    //private int timer1 = 100;

    public Boss2(int x, int y, IDs id, Handler handler, HUD hud)
    {
        super(x, y, id);

        this.handler = handler;
        this.hud = hud;
        loadImage();

        speedX = 7;
        speedY = 7;

        if(hud.isStrong())
            bonusDMG = 0.03;
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("src/sprites/boss2.png");
        boss2Image = ii.getImage();
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        collision();

        if(x <= 0 || x >= Game.WIDTH - 114)
        {
            speedX *= -1;
        }

        if(y <= 0 || y >= Game.HEIGHT - 132)
        {
            speedY *= -1;
        }

    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(Color.red);
//        graphics.fillRect(x, y, 96, 96);
        graphics.drawImage(boss2Image, x, y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 96, 96);
    }

    @Override
    public void shoot(int x, int y)
    {

    }

    public void collision()
    {
        for (int i = 0; i < handler.obj.size(); i++) {

            Objects temp = handler.obj.get(i);

            if (temp.getId() == IDs.ShotPlayer)
            {
                if (getBounds().intersects(temp.getBounds()))
                {
                    HUD.boss2HP -= 0.1 + bonusDMG;
                }
            }
        }
    }
}
