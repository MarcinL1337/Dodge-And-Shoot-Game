package com.projekt.main;

import java.awt.*;

// All the game objects (e.g. player object, enemies objects etc.)
public abstract class Objects
{
    protected int x, y;
    protected IDs id;
    public int speedX, speedY;
    private Handler handler = new Handler();

    public Objects(int x, int y, IDs id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
    public abstract Rectangle getBounds();
    public abstract void shoot(int x, int y);

    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public IDs getId() {
        return id;
    }

    public void setId(IDs id) {
        this.id = id;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
