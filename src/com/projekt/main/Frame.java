package com.projekt.main;

import javax.swing.*;
import java.awt.*;

public class Frame extends Canvas
{
    public Frame(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Frame starts in the middle of the screen
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
