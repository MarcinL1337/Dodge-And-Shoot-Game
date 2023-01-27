package com.projekt.main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Boss1 extends Objects
{
    private int timer1 = 50, timer2 = 100;
    private Handler handler;
    Random rand = new Random();
    private HUD hud;

    private double bonusDMG = 0.0;

    private Image boss1Image;

    public Boss1(int x, int y, IDs id, Handler handler, HUD hud)
    {
        super(x, y, id);

        this.handler = handler;
        this.hud = hud;
        loadImage();

        speedX = 0;
        speedY = 2;

        if(hud.isStrong())
            bonusDMG = 0.03;
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("src/sprites/boss1.png");
        boss1Image = ii.getImage();
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        if(timer1 <= 0)
            speedY = 0;
        else
            timer1--;

        if(timer1 <= 0)
            timer2--;

        if(timer2 <= 0)
        {
            if (speedX == 0) speedX = 3;
            int shot = rand.nextInt(5);
            if(shot == 0)
                handler.pushObj(new Shot(x + 40, y + 16, IDs.NormalEnemy, handler));
        }

        if(x <= 0 || x >= Game.WIDTH - 114)
            speedX *= -1;

        if(y <= 0 || y >= Game.HEIGHT - 132)
            speedY *= -1;

        if(hud.getScore() >= 3650)
            collision();
    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(Color.MAGENTA);
//        graphics.fillRect(x, y, 96, 96);
        graphics.drawImage(boss1Image, x, y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 96, 96);
    }

    @Override
    public void shoot(int x, int y) {

    }

    public void collision()
    {
        for (int i = 0; i < handler.obj.size(); i++) {

            Objects temp = handler.obj.get(i);

            if (temp.getId() == IDs.ShotPlayer)
            {
                if (getBounds().intersects(temp.getBounds()))
                {
                    HUD.bossHP -= 0.1 + bonusDMG;
                }
            }
        }
    }
}
