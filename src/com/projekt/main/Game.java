package com.projekt.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 1024, HEIGHT = 768;
    public static boolean paused = false;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;
    private Help help;
    private Settings settings;

    public enum STATE
    {
        Menu,
        Help,
        Settings,
        GameOver,
        ClassSelection,
        Game,
        Wave2,
        Wave3
    }

    public STATE gameState = STATE.Menu;

    public Game()
    {
        hud = new HUD();
        handler = new Handler();
        spawn = new Spawn(handler, hud);
        menu = new Menu(this, handler, hud, spawn);
        help = new Help(this);
        settings = new Settings(this);
        this.addKeyListener(new KeyInput(handler, this, menu));
        this.addMouseListener(menu);
        this.addMouseListener(help);
        this.addMouseListener(settings);

        new Frame(WIDTH, HEIGHT, "Title", this);

        this.requestFocus();
    }

    public synchronized void start()
    {
        thread = new Thread(this); // Init this as new thread
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        if (gameState == STATE.Game)
        {
            if(!paused)
            {
                handler.tick();
                hud.tick();
                spawn.tick();

                if (HUD.bossHP <= 0 || hud.getScore() >= 5000)
                {
                    handler.obj.remove(1);
                    hud.setScore(5000);
                    hud.setLevel(21);
                    gameState = STATE.Wave2;
                }

                if (HUD.HP <= 0)
                {
                    HUD.HP = 100;
                    gameState = STATE.GameOver;
                    handler.obj.clear();
                }
            }
        }

        else if(gameState == STATE.Wave2)
        {
            if(!paused)
            {
                handler.tick();
                hud.tick();
                spawn.tick();

                if (HUD.boss2HP <= 0 || hud.getScore() >= 10000)
                {
                    handler.clearObjs();
                    hud.setScore(10000);
                    hud.setLevel(41);
                    gameState = STATE.Wave3;
                }

                if (HUD.HP <= 0)
                {
                    HUD.HP = 100;
                    gameState = STATE.GameOver;
                    handler.obj.clear();
                }
            }
        }

        else if(gameState == STATE.Wave3)
        {
            if(!paused)
            {
                handler.tick();
                hud.tick();
                spawn.tick();

                if(HUD.HP <= 0)
                {
                    HUD.HP = 100;
                    gameState = STATE.GameOver;
                    handler.obj.clear();
                }
            }

        }

        else if (gameState == STATE.Menu || gameState == STATE.GameOver)
        {
            menu.tick();
        }

        else if (gameState == STATE.Help)
        {
            help.tick();
        }

        else if (gameState == STATE.Settings)
        {
            settings.tick();
        }
    }


    private void render()
    {
        // represents the mechanism with which to organize complex memory on a particular Canvas or Window
        BufferStrategy buffer = this.getBufferStrategy();
        if(buffer == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = buffer.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);

        if(paused)
        {
            graphics.setColor(Color.white);
            graphics.drawString("PAUSED", Game.WIDTH / 2 - 25, 200);
        }

        if(gameState == STATE.Game || gameState == STATE.Wave2 || gameState == STATE.Wave3)
        {
            hud.render(graphics);
        }
        else if(gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.ClassSelection)
        {
            menu.render(graphics);
        }
        else if(gameState == STATE.Help)
        {
            help.render(graphics);
        }
        else if(gameState == STATE.Settings)
        {
            settings.render(graphics);
        }

        graphics.dispose();
        buffer.show();
    }

    public static int limit(int val, int min, int max)
    {
        if(val >= max)
            return val = max;
        else if(val <= min)
            return val = min;
        else
            return val;
    }

    public static void main(String args[])
    {
        new Game();
    }
}
