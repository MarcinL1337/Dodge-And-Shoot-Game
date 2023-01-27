package com.projekt.main;

import javax.swing.*;
import java.awt.*;

public class Buffs extends Objects
{
    private Handler handler;
    private HUD hud;

    private int time;

    private Image buffImage;
    private String path;

    public Buffs(int x, int y, IDs id, Handler handler, HUD hud)
    {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        time = 0;

        if(this.getId() == IDs.HealthBuff)
            path = "src/sprites/healthBuff.png";
        else if(this.getId() == IDs.SpeedBuff)
            path = "";
        else if(this.getId() == IDs.FreezeBuff)
            path = "";

        loadImage(path);
    }

    private void loadImage(String path)
    {
        ImageIcon ii = new ImageIcon(path);
        buffImage = ii.getImage();
    }

    @Override
    public void tick()
    {
        if(time >= 250) handler.popObj(this);
        else time++;
    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(color);
//        graphics.fillRect(x, y, 24, 24);
        graphics.drawImage(buffImage, x, y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 24, 24);
    }

    @Override
    public void shoot(int x, int y)
    {

    }
}
