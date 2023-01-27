package com.projekt.main;

import java.util.Random;

public class Spawn
{
    private Handler handler;
    private HUD hud;
    private Random rand = new Random();
    private int random_buff;

    private int scoreKeeper;

    public Spawn(Handler handler, HUD hud)
    {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick()
    {
        scoreKeeper++;
        random_buff = rand.nextInt(1500);
        if(random_buff == 1337)
            handler.pushObj(new Buffs(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.HealthBuff, handler, hud));

        if(hud.getScore() >= 10000)
        {
            if(scoreKeeper % 15 == 0)
            {
                handler.pushObj(new KamikazeEnemy(rand.nextInt(Game.WIDTH - 100), 60, IDs.FasterEnemy, handler));
            }
        }

        if(hud.getScore() >= 5000 && hud.getScore() <= 6000)
        {
            if(scoreKeeper % 5 == 0)
            {
                handler.pushObj(new KamikazeEnemy(rand.nextInt(Game.WIDTH - 100), 60, IDs.FasterEnemy, handler));
            }
        }

        else if(hud.getScore() >= 2500 && hud.getScore() <= 3500)
        {
            if(scoreKeeper % 7 == 0)
            {
                handler.pushObj(new KamikazeEnemy(rand.nextInt(Game.WIDTH - 100), 60, IDs.FasterEnemy, handler));
            }
        }

        if (scoreKeeper >= 250)
        {
            scoreKeeper = 0;
            hud.setLevel(hud.getLevel() + 1);

            if(hud.getScore() >= 10000)
            {
                handler.pushObj(new BiggerEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }

            else if(hud.getLevel() >= 8250)
            {
                handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
                handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }

            else if(hud.getLevel() == 33)
            {
                hud.setScore(8000);
                handler.clearObjs();
                handler.pushObj(new Boss2(rand.nextInt(Game.WIDTH - 150), rand.nextInt(Game.HEIGHT - 150), IDs.Boss2, handler, hud));
            }

            else if(hud.getScore() >= 6000)
            {
                if (hud.getLevel() % 2 == 0)
                    handler.pushObj(new FasterEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.FasterEnemy));
                else
                {
                    handler.pushObj(new BiggerEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
                    handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
                }
            }

            else if(hud.getLevel() == 15)
            {
                hud.setScore(3500);
                handler.clearObjs();
                handler.pushObj(new Boss1(Game.WIDTH / 2 - 48, 0, IDs.Boss1, handler, hud));
            }

            else if(hud.getLevel() == 11)
            {
                handler.clearObjs();
                hud.setScore(2500);
            }

            else if(hud.getScore() <= 2500)
            {
                if(hud.getLevel() % 3 == 0)
                    handler.pushObj(new FasterEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.FasterEnemy));
                else if(hud.getLevel() % 2 == 0)
                    handler.pushObj(new BiggerEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
                else
                    handler.pushObj(new NormalEnemy(rand.nextInt(Game.WIDTH - 100), rand.nextInt(Game.HEIGHT - 100), IDs.NormalEnemy));
            }
        }
    }

    public int getScoreKeeper()
    {
        return scoreKeeper;
    }

    public void setScoreKeeper(int scoreKeeper)
    {
        this.scoreKeeper = scoreKeeper;
    }
}
