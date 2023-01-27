package com.projekt.main;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class HUD
{
    public static int HP = 100;
    public static int bossHP = 300;
    public static int boss2HP = 200;

    private int score;
    private int level;
    public boolean isStrong = false;

    public boolean isStrong()
    {
        return isStrong;
    }

    public void setStrong(boolean strong)
    {
        isStrong = strong;
    }

    public void tick()
    {
        HP = Game.limit(HP, 0, 140);
        bossHP = Game.limit(bossHP, 0, 300);
        boss2HP = Game.limit(boss2HP, 0, 300);

        //bossHP --; // test
        score++;
    }

    public void render(Graphics graphics)
    {
        // player health
        graphics.setColor(Color.gray);
        graphics.fillRect(Game.WIDTH / 2 - 150, 20, 300, 50);
        graphics.setColor(Color.green);
        graphics.fillRect(Game.WIDTH / 2 - 150, 20, (int)((HP * 3)/1.4), 50); // draft
        graphics.setColor(Color.white);
        graphics.drawRect(Game.WIDTH / 2 - 150, 20, 300, 50);

        Font font = new Font("longhaul", 1, 40);
        AttributedString atString= new AttributedString(String.valueOf(HP));
        atString.addAttribute(TextAttribute.FONT, font);
        graphics.drawString(atString.getIterator(), Game.WIDTH / 2 - 140, 60);

        // boss1 health
        if(level >= 15 && level <= 21 && bossHP >= 0)
        {
            graphics.drawRect(Game.WIDTH / 2 - 300, 678, 600, 30);
            graphics.setColor(Color.red);
            graphics.fillRect(Game.WIDTH / 2 - 300, 678, bossHP * 2, 30);
            graphics.setColor(Color.white);
            graphics.drawRect(Game.WIDTH / 2 - 300, 678, 600, 30);
        }

        // boss2 health
        if(level >= 33 && level <= 40 && boss2HP >= 0)
        {
            graphics.drawRect(Game.WIDTH / 2 - 300, 678, 600, 30);
            graphics.setColor(Color.red);
            graphics.fillRect(Game.WIDTH / 2 - 300, 678, boss2HP * 3, 30);
            graphics.setColor(Color.white);
            graphics.drawRect(Game.WIDTH / 2 - 300, 678, 600, 30);
        }

        graphics.drawString("Score: " + score, 10, 700);
        graphics.drawString("Level: " + level, 10, 716);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int getBossHP()
    {
        return bossHP;
    }

    public static void setBossHP(int bossHP)
    {
        HUD.bossHP = bossHP;
    }

    public static int getBoss2HP()
    {
        return boss2HP;
    }

    public static void setBoss2HP(int boss2HP)
    {
        HUD.boss2HP = boss2HP;
    }

    public static int getHP()
    {
        return HP;
    }

    public static void setHP(int HP)
    {
        HUD.HP = HP;
    }
}
