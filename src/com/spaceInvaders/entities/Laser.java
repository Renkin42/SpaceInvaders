package com.spaceInvaders.entities;
import java.util.ArrayList;
import java.util.List;

import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Laser extends GameObject {
    
    public Laser(int startX, int startY) {
        super(startX, startY, GameData.LASER_WIDTH, GameData.LASER_HEIGHT, GameData.getSprite("laser"));
    }

    @Override
    public void tickBehavior(GameController controller) {
        this.moveY(-GameData.LASER_SPEED);
        List<GameObject> entities = new ArrayList<GameObject>(controller.getEntities());
        if (this.isOutOfBounds()) {
            controller.playSound("sploosh",  -20);
            controller.getEntities().remove(this);
            controller.setLaserOnScreen(false);
        } else {
            for (GameObject entity : entities) {
                if (this.intersects(entity)) {
                    if (entity instanceof Alien) {
                        controller.createExplosion(getX() + getWidth() / 2, getY());
                        entity.destroy(controller);
                        this.destroy(controller);
                        controller.alienHit();
                    } else if (entity instanceof Missile) {
                        controller.createExplosion(getX() + getWidth() / 2, getY());;
                        controller.setLaserOnScreen(false);
                        controller.addPoints(5);
                        entity.destroy(controller);
                        this.destroy(controller);
                    }
                }
            }
        }
    }
    
    @Override
    public void destroy(GameController controller) {
        controller.setLaserOnScreen(false);
        super.destroy(controller);
    }
}
