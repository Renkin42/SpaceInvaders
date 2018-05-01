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
            controller.playSound("sploosh", -20);
            controller.getEntities().remove(this);
            controller.setLaserOnScreen(false);
        } else {
            for (GameObject entity : entities) {
                if (this.intersects(entity)) {
                    if (entity instanceof Alien) {
                        controller.createExplosion(getX() + getWidth() / 2, getY());
                        controller.getEntities().remove(entity);
                        controller.getEntities().remove(this);
                        controller.alienHit();
                    } else if (entity instanceof Missile) {
                        controller.playSound("kerboom", -20);
                        controller.createExplosion(getX() + getWidth() / 2, getY());
                        controller.setLaserOnScreen(false);
                        controller.addPoints(5);
                        controller.getEntities().remove(entity);
                        controller.getEntities().remove(this);
                    }
                }
            }
        }
    }
}
