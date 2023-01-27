package com.projekt.main;

import javax.swing.*;
import java.awt.*;

public class Player extends Objects
{
    Handler handler;
    Menu menu;
    private Game game;
    public Color color;
    private Image playerImage;

    private String path;

    public Player(int x, int y, IDs id, Handler handler, Game game, Menu menu)
    {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        this.menu = menu;

        switch (menu.getPlayerClass())
        {
            case "Normal" -> path = "src/sprites/normalPlayer.png";
            case "Quick" -> path = "src/sprites/quickPlayer.png";
            case "Tank" -> path = "src/sprites/tankPlayer.png";
            case "Strong" -> path = "src/sprites/strongPlayer.png";
        }

        loadImage(path);

        //speedX = 1; // test
    }

    private void loadImage(String path)
    {
        ImageIcon ii = new ImageIcon(path);
        playerImage = ii.getImage();
    }

    @Override
    public void tick()
    {
        x += speedX;
        y += speedY;

        x = Game.limit(x, 0, Game.WIDTH - 65);
        y = Game.limit(y, 0, Game.HEIGHT - 88);

        collision();

        if(game.gameState == Game.STATE.GameOver)
            handler.popObj(this);
    }

    @Override
    public void render(Graphics graphics)
    {
//        graphics.setColor(this.color);
//        graphics.fillRect(x, y, 48, 48);
        graphics.drawImage(playerImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 48);
    }

    @Override
    public void shoot(int x, int y)
    {
        handler.pushObj(new ShotPlayer(x, y, IDs.ShotPlayer, handler));
    }

    public void collision()
    {
        for(int i = 0; i < handler.obj.size(); i++)
        {
            Objects temp = handler.obj.get(i);

            if(getBounds().intersects(temp.getBounds()))
            {
                if(temp.getId() == IDs.NormalEnemy)
                {
                    HUD.HP -= 1;
                }

                if(temp.getId() == IDs.FasterEnemy)
                {
                    HUD.HP -= 3;
                }

                if(temp.getId() == IDs.KamikazeEnemy)
                {
                    HUD.HP -= 1.5;
                }

                if(temp.getId() == IDs.Boss1)
                {
                    HUD.HP -= 5;
                }

                if(temp.getId() == IDs.Boss2)
                {
                    HUD.HP -= 3;
                }

                if(temp.getId() == IDs.HealthBuff)
                {
                    HUD.HP += 25;
                    handler.popObj(temp);
                }
            }
        }
    }
}
