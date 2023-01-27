package com.projekt.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Help extends MouseAdapter
{
    private Game game;

    public Help(Game game)
    {
        this.game = game;
    }

    public void mousePressed(MouseEvent e)
    {
        if(game.gameState == Game.STATE.Help)
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if(isMouseOn (mouseX, mouseY, 50, 20, 100, 50))
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
        graphics.drawString("Help", Game.WIDTH / 2 - 100, 75);

        graphics.setFont(font2);
        graphics.drawString("Key Bindings:", 50, 150);

        graphics.setFont(font2);
        graphics.drawString("W - Move up", 350, 150);

        graphics.setFont(font2);
        graphics.drawString("S - Move down", 350, 225);

        graphics.setFont(font2);
        graphics.drawString("A - Move left", 350, 300);

        graphics.setFont(font2);
        graphics.drawString("D - Move right", 350, 375);

        graphics.setFont(font2);
        graphics.drawString("Space - Shoot", 350, 450);

        graphics.setFont(font2);
        graphics.drawString("P - Pause the game", 350, 525);

        graphics.setFont(font2);
        graphics.drawString("Esc - Quit the game", 350, 600);

        graphics.setFont(font2);
        graphics.drawString("Backspace - Return to main menu", 350, 675);

        graphics.setFont(font3);
        graphics.drawString("Back", 70, 53);
        graphics.drawRect(50, 20, 100, 50);
    }
}
