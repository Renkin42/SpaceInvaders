package com.spaceInvaders.entities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;


public abstract class GameObject implements Object2D {
    
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image sprite;
    
    public GameObject(int startX, int startY, int width, int height, Image sprite) {
        this.posX = startX;
        this.posY = startY;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    @Override
    public int getX() {
        return posX;
    }
    
    public void moveX(int move) {
        this.posX += move;
    }

    @Override
    public int getY() {
        return posY;
    }
    
    public void moveY(int move) {
        this.posY += move;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return new Rectangle(posX, posY, width, height);
    }

    @Override
    public boolean intersects(Object2D other) {
        return other.getBoundingRectangle().intersects(getBoundingRectangle());
    }

    @Override
    public boolean isOutOfBounds() {
        Rectangle gameArea = new Rectangle(GameData.GAME_BOARD_WIDTH, GameData.GAME_BOARD_HEIGHT);
        return !gameArea.contains(getBoundingRectangle());
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + " at (" + posX + ", " + posY + ")";
    }
    
    public void updateSprite(Image newSprite) {
        this.sprite = newSprite;
    }
    
    public Image getSprite() {
        return this.sprite;
    }
    
    public void drawSprite(Graphics g) {
        g.drawImage(sprite, posX, posY, width, height, null);
    }

    public abstract void tickBehavior(GameController controller);

}
