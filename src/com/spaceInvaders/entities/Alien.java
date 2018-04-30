package com.spaceInvaders.entities;

import java.awt.Rectangle;

import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Alien extends GameObject implements Shooter<Missile> {

    private Rectangle moveBounds;
    private int speed = GameData.ALIEN_WIDTH / 4;
    private int tickCounter = 0;

    public Alien(int startX, int startY) {
        super(startX, startY, GameData.ALIEN_WIDTH, GameData.ALIEN_HEIGHT, GameData.getSprite("alien"));
        moveBounds = new Rectangle(startX, 0, GameData.GAME_BOARD_WIDTH
                - (GameData.NUM_ALIENS_X - 1) * GameData.ALIEN_WIDTH * 5 / 4 - GameData.ALIEN_WIDTH / 2,
                GameData.GAME_BOARD_HEIGHT);
    }

    @Override
    public Missile fire() {
        int missileX = this.getX() + this.getWidth() / 2 - GameData.MISSILE_WIDTH / 2;
        int missileY = this.getY() + this.getHeight();
        return new Missile(missileX, missileY);
    }

    @Override
    public void tickBehavior(GameController controller) {
        tickCounter++;
        if (tickCounter >= controller.getNumAliens() / 4) {
            tickCounter = 0;
            this.moveX(speed);
            if (isOutOfBounds()) {
                speed = -speed;
                this.moveX(speed);
                this.moveY(GameData.ALIEN_HEIGHT / 2);
                
            }
        }

    }

    @Override
    public boolean isOutOfBounds() {
        if(super.isOutOfBounds()) {
            //TODO: Game Over code here.
            return false;
        } else {
            return !moveBounds.contains(getBoundingRectangle());
        }
        
    }

}
