package com.spaceInvaders.entities;
import java.util.ArrayList;
import java.util.List;

import com.spaceInvaders.AssetManager;
import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Laser extends GameObject {

    public Laser(int startX, int startY) {
        super(startX, startY, GameData.LASER_WIDTH, GameData.LASER_HEIGHT, AssetManager.getSprite("laser"));
    }

    @Override
    public void tickBehavior(GameController controller) {
        this.moveY(-GameData.LASER_SPEED);
        List<GameObject> entities = new ArrayList<GameObject>(controller.getEntities());
        if (this.isOutOfBounds()) {
            controller.getEntities().remove(this);
        } else {
            for (GameObject entity : entities) {
                if (entity instanceof Alien && this.intersects(entity)) {
                    controller.getEntities().remove(entity);
                    controller.getEntities().remove(this);
                    controller.alienHit();
                }
            }
        }
    }

}
