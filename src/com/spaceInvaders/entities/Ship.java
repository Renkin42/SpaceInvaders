package com.spaceInvaders.entities;
import com.spaceInvaders.AssetManager;
import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Ship extends GameObject implements Shooter<Laser> {
    
    private Laser laser;

    public Ship() {
        super(GameData.GAME_BOARD_WIDTH / 2 - GameData.SHIP_WIDTH / 2,
                GameData.GAME_BOARD_HEIGHT - GameData.SHIP_HEIGHT, 
                GameData.SHIP_WIDTH, GameData.SHIP_HEIGHT, AssetManager.getSprite("ship"));
    }

    @Override
    public Laser fire() {
        int laserX = this.getX() + this.getWidth() / 2 - GameData.LASER_WIDTH / 2;
        int laserY = this.getY() - GameData.LASER_HEIGHT;
        return new Laser(laserX , laserY);
    }

    @Override
    public void tickBehavior(GameController controller) {
        if(controller.isLeftPressed()) {
            this.moveX(-10);
            if(this.isOutOfBounds()) {
                this.moveX(10);
            }
        }
        if(controller.isRightPressed()) {
            this.moveX(10);
            if(this.isOutOfBounds()) {
                this.moveX(-10);
            }
        }
        if(controller.isFirePressed() && !controller.getEntities().contains(laser)) {
            laser = fire();
            controller.getEntities().add(laser);
        }
    }

}
