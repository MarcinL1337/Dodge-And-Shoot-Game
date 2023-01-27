package com.projekt.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings extends MouseAdapter
{
    private Game game;

    public Settings(Game game)
    {
        this.game = game;
    }

    public void mousePressed(MouseEvent e)
    {
        if(game.gameState == Game.STATE.Settings)
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if(isMouseOn(mouseX, mouseY, 50, 20, 100, 50))
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
        Font font = new Font("longhaul", 1, 75);
        Font font2 = new Font("longhaul", 1, 40);
        Font font3 = new Font("longhaul", 1, 25);

        graphics.setColor(Color.white);

        graphics.setFont(font);
        graphics.drawString("Settings", Game.WIDTH / 2 - 155, 75);

//        graphics.setFont(font2);
//        graphics.drawString("Peter Griffin makes the dab", 260, 200);

        graphics.setFont(font3);
        graphics.drawString("Back", 70, 53);

        graphics.drawRect(50, 20, 100, 50);
    }
}
