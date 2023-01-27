package com.projekt.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter
{
    private Game game;
    private Handler handler;
    private Random rand = new Random();
    private HUD hud;
    private Spawn spawn;

    private String playerClass;

    public String getPlayerClass()
    {
        return playerClass;
    }

    public void setPlayerClass(String playerClass)
    {
        this.playerClass = playerClass;
    }

    public Menu(Game game, Handler handler, HUD hud, Spawn spawn)
    {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.spawn = spawn;
    }

    public void mousePressed(MouseEvent e)
    {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(game.gameState == Game.STATE.Menu)
        {
            //play button
            if(isMouseOn(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300 ,100))
            {
                game.gameState = Game.STATE.ClassSelection;
            }

            //help button
            if(isMouseOn(mouseX, mouseY, Game.WIDTH / 2 - 150, 300, 300 ,100))
            {
                game.gameState = Game.STATE.Help;
            }

            //settings button
            if(isMouseOn(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300 ,100))
            {
                game.gameState = Game.STATE.Settings;
            }

            //quit button
            if(isMouseOn(mouseX, mouseY, Game.WIDTH / 2 - 150, 600, 300, 100))
            {
                System.exit(1);
            }
        }

        if(game.gameState == Game.STATE.GameOver)
        {
            //Try again button
            if(isMouseOn(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300 ,100))
            {
                HUD.setBossHP(300);
                HUD.setBoss2HP(200);
                handler.clearObjs();
                spawn.setScoreKeeper(0);
                hud.setLevel(1);
                hud.setScore(0);
                game.gameState = Game.STATE.ClassSelection;
            }

            //Main menu button
            if(isMouseOn(mouseX, mouseY, Game.WIDTH / 2 - 150, 600, 300 ,100))
            {
                HUD.setBossHP(300);
                HUD.setBoss2HP(200);
                game.gameState = Game.STATE.Menu;
                spawn.setScoreKeeper(0);
                hud.setLevel(1);
                hud.setScore(0);
                handler.clearObjs();
            }
        }

        if(game.gameState == Game.STATE.ClassSelection)
        {
            if(isMouseOn (mouseX, mouseY, 100, 120, 350, 250))
            {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                spawn.setScoreKeeper(0);
                setPlayerClass("Normal");
                handler.pushObj(new Player(Game.WIDTH / 2 - 24, Game.HEIGHT / 2 - 24, IDs.Player, handler, game, this));
                handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }
            else if(isMouseOn (mouseX, mouseY, 100, 460, 350, 250))
            {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                spawn.setScoreKeeper(0);
                HUD.setHP(140);
                setPlayerClass("Tank");
                handler.pushObj(new Player(Game.WIDTH / 2 - 20, Game.HEIGHT / 2 - 20, IDs.Player, handler, game, this));
                handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }
            else if(isMouseOn (mouseX, mouseY, 574, 120, 350, 250))
            {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                spawn.setScoreKeeper(0);
                HUD.setHP(80);
                setPlayerClass("Quick");
                handler.pushObj(new Player(Game.WIDTH / 2 - 20, Game.HEIGHT / 2 - 20, IDs.Player, handler, game, this));
                handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }
            else if(isMouseOn (mouseX, mouseY, 574, 460, 350, 250))
            {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                spawn.setScoreKeeper(0);
                HUD.setHP(90);
                setPlayerClass("Strong");
                hud.setStrong(true);
                handler.pushObj(new Player(Game.WIDTH / 2 - 20, Game.HEIGHT / 2 - 20, IDs.Player, handler, game, this));
                handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }
            //Back button
            else if(isMouseOn (mouseX, mouseY, 50, 20, 100, 50))
            {
                game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    // Checks if mouse is on any of the Menu options
    private boolean isMouseOn(int mouseX, int mouseY, int x, int y, int width, int height)
    {
        if(mouseX > x && mouseX < x + width)
        {
            return mouseY > y && mouseY < y + height;
        }
        else
        {
            return false;
        }
    }

    public void tick()
    {

    }

    public void render(Graphics graphics)
    {
        if(game.gameState == Game.STATE.Menu)
        {
            Font font = new Font("longhaul", 1, 75);
            Font font2 = new Font("longhaul", 1, 40);

            graphics.setColor(Color.white);

            graphics.setFont(font);
            graphics.drawString("Menu", Game.WIDTH / 2 - 100, 75);

            graphics.setFont(font2);
            graphics.drawString("Play", Game.WIDTH / 2 - 45, 210);

            graphics.drawRect(Game.WIDTH / 2 - 150, 150, 300, 100);

            graphics.setFont(font2);
            graphics.drawString("Help", Game.WIDTH / 2 - 45, 360);

            graphics.drawRect(Game.WIDTH / 2 - 150, 300, 300, 100);

            graphics.setFont(font2);
            graphics.drawString("Settings", Game.WIDTH / 2 - 75, 510);

            graphics.drawRect(Game.WIDTH / 2 - 150, 450, 300, 100);

            graphics.setFont(font2);
            graphics.drawString("Quit", Game.WIDTH / 2 - 45, 660);

            graphics.drawRect(Game.WIDTH / 2 - 150, 600, 300, 100);
        }
        else if(game.gameState == Game.STATE.GameOver)
        {
            Font font = new Font("longhaul", 1, 75);
            Font font2 = new Font("longhaul", 1, 40);

            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Game Over", Game.WIDTH / 2 - 200, 250);

            graphics.setFont(font2);
            graphics.setColor(Color.white);
            graphics.drawString("Your Score is: " + hud.getScore(), 335, 350);

            graphics.setColor(Color.white);
            graphics.drawRect(Game.WIDTH / 2 - 150, 450, 300, 100);

            graphics.setFont(font2);
            graphics.setColor(Color.white);
            graphics.drawString("Try Again", Game.WIDTH / 2 - 90, 510);

            graphics.setColor(Color.white);
            graphics.drawRect(Game.WIDTH / 2 - 150, 600, 300, 100);

            graphics.setFont(font2);
            graphics.setColor(Color.white);
            graphics.drawString("Main Menu", Game.WIDTH / 2 - 100, 660);
        }

        else if(game.gameState == Game.STATE.ClassSelection)
        {
            Font font = new Font("longhaul", 1, 50);
            Font font2 = new Font("longhaul", 1, 30);
            Font font3 = new Font("longhaul", 1, 25);

            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Choose preferred class", Game.WIDTH / 2 - 285, 50);

            graphics.setFont(font2);
            graphics.drawString("Normal", 220, 100);
            graphics.drawRect(100, 120, 350, 250);
            ImageIcon i1 = new ImageIcon("src/sprites/normalPlayer.png");
            graphics.drawImage(i1.getImage(), 251, 140, null);

            graphics.setFont(font2);
            graphics.drawString("Quick", 707, 100);
            graphics.drawRect(574, 120, 350, 250);
            ImageIcon i2 = new ImageIcon("src/sprites/quickPlayer.png");
            graphics.drawImage(i2.getImage(), 725, 140, null);

            graphics.setFont(font2);
            graphics.drawString("Tank", 240, 440);
            graphics.drawRect(100, 460, 350, 250);
            ImageIcon i3 = new ImageIcon("src/sprites/tankPlayer.png");
            graphics.drawImage(i3.getImage(), 251, 480, null);

            graphics.setFont(font2);
            graphics.drawRect(574, 460, 350, 250);
            graphics.drawString("Strong", 698, 440);
            ImageIcon i4 = new ImageIcon("src/sprites/strongPlayer.png");
            graphics.drawImage(i4.getImage(), 725, 480, null);


            graphics.setFont(font3);
            graphics.setColor(Color.white);

            graphics.drawString("HP:            100", 120, 250);
            graphics.drawString("SPEED:        5", 120, 290);
            graphics.drawString("DAMAGE:   10", 120, 330);

            graphics.drawString("HP:              80", 594, 250);
            graphics.drawString("SPEED:        6", 594, 290);
            graphics.drawString("DAMAGE:   10", 594, 330);

            graphics.drawString("HP:            140", 120, 590);
            graphics.drawString("SPEED:        4", 120, 630);
            graphics.drawString("DAMAGE:   10", 120, 670);

            graphics.drawString("HP:              90", 594, 590);
            graphics.drawString("SPEED:        5", 594, 630);
            graphics.drawString("DAMAGE:   12", 594, 670);

            graphics.setFont(font3);
            graphics.setColor(Color.white);
            graphics.drawString("Back", 70, 53);
            graphics.drawRect(50, 20, 100, 50);
        }
    }
}
