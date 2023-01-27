package com.projekt.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;
    private Game game;
    private Menu menu;
    private boolean k[] = new boolean[4];
    

    private float speedMultiplier = 1.0F; // Was supposed to be a speed buff, didn't make it in the end

    public KeyInput(Handler handler, Game game, Menu menu)
    {
        this.handler = handler;
        this.game = game;
        this.menu = menu;

        k[0] = false;
        k[1] = false;
        k[2] = false;
        k[3] = false;
    }

    public void keyPressed(KeyEvent event)
    {
        int key = event.getKeyCode();

        if(game.gameState != Game.STATE.Game && game.gameState != Game.STATE.GameOver)
        {
            if(key == KeyEvent.VK_BACK_SPACE)
            {
                game.gameState = Game.STATE.Menu;
            }
        }

        int playerSpeed;
        switch (menu.getPlayerClass())
        {
            case "Tank" -> playerSpeed = 4;
            case "Quick" -> playerSpeed = 6;
            default -> playerSpeed = 5;
        }

        for(int i = 0; i < handler.obj.size(); i++)
        {
            Objects temp = handler.obj.get(i);

            if(temp.getId() == IDs.Player)
            {
                //key events for player

                if(key == KeyEvent.VK_W)
                {
                    temp.setSpeedY((int) -(playerSpeed * speedMultiplier));
                    k[0] = true;
                }
                if(key == KeyEvent.VK_S)
                {
                    temp.setSpeedY((int) (playerSpeed * speedMultiplier));
                    k[1] = true;
                }
                if(key == KeyEvent.VK_D)
                {
                    temp.setSpeedX((int) (playerSpeed * speedMultiplier));
                    k[2] = true;
                }
                if(key == KeyEvent.VK_A)
                {
                    temp.setSpeedX((int) -(playerSpeed * speedMultiplier));
                    k[3] = true;
                }
                if(key == KeyEvent.VK_SPACE)
                {
                    temp.shoot(temp.getX() + 20, temp.getY() - 16);
                }
            }
        }

        if(key == KeyEvent.VK_P && game.gameState == Game.STATE.Game)
            Game.paused = !Game.paused;

        if(key == KeyEvent.VK_ESCAPE && game.gameState != Game.STATE.GameOver)
            System.exit(1);
    }

    public void keyReleased(KeyEvent event)
    {
        int key = event.getKeyCode();

        for(int i = 0; i < handler.obj.size(); i++)
        {
            Objects temp = handler.obj.get(i);

            if(temp.getId() == IDs.Player)
            {
                //key events for player

                if(key == KeyEvent.VK_W)
                    k[0] = false;
                if(key == KeyEvent.VK_S)
                    k[1] = false;
                if(key == KeyEvent.VK_D)
                    k[2] = false;
                if(key == KeyEvent.VK_A)
                    k[3] = false;

                if(!k[0] && !k[1])
                    temp.setSpeedY(0);

                if(!k[2] && !k[3])
                    temp.setSpeedX(0);
            }
        }
    }
}
