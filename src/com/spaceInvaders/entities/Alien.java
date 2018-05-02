package com.spaceInvaders.entities;

import java.awt.Rectangle;

import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Alien extends GameObject implements Shooter<Missile> {

    private Rectangle moveBounds;
    private Rectangle fireLine;
    private int speed = GameData.ALIEN_WIDTH / 4;
    private int tickCounter = 0;
    private int moveCounter = 0;
    private int moveFrequency;
    private int level;

    public Alien(int startX, int startY, int level) {
        super(startX, startY, GameData.ALIEN_WIDTH, GameData.ALIEN_HEIGHT, GameData.getSprite("alien"));
        moveBounds = new Rectangle(startX, 0, GameData.GAME_BOARD_WIDTH
                - (GameData.NUM_ALIENS_X - 1) * GameData.ALIEN_WIDTH * 5 / 4 - GameData.ALIEN_WIDTH / 2,
                GameData.GAME_BOARD_HEIGHT);
        this.level = level;
        moveFrequency = GameData.NUM_ALIENS_X * GameData.NUM_ALIENS_Y / 4 + 20 / level;
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
        if (tickCounter == moveFrequency) {
            tickCounter = 0;

            moveFrequency = controller.getNumAliens() / 4 + 20 / level;
            this.moveX(speed);
            if (isOutOfBounds()) {
                speed = -speed;
                this.moveX(speed);
                this.moveY(GameData.ALIEN_HEIGHT / 2);
                if(isOutOfBounds()) {
                    controller.gameOver();
                }

            }
            moveCounter++;
            if (moveCounter == GameData.ALIEN_FIRE_RATE) {
                moveCounter = 0;
                boolean frontClear = true;
                boolean shipInFront = false;
                fireLine = new Rectangle(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight(), 1,
                        GameData.GAME_BOARD_HEIGHT - this.getY() - this.getHeight());
                for (GameObject entity : controller.getEntities()) {
                    if (fireLine.intersects(entity.getBoundingRectangle())) {
                        if (entity instanceof Ship) {
                            shipInFront = true;
                        } else if (entity instanceof Alien) {
                            frontClear = false;
                        }
                    }
                    if (this.intersects(entity) && entity instanceof Ship) {
                        controller.gameOver();
                    }
                }
                if (frontClear && shipInFront) {
                    controller.playSound("rocket", -20);
                    controller.getEntities().add(this.fire());
                }

            }
        }

    }

    @Override
    public boolean isOutOfBounds() {
        if (super.isOutOfBounds()) {
            return true;
        } else {
            return !moveBounds.contains(getBoundingRectangle());
        }

    }

}
