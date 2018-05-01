package com.spaceInvaders.entities;
import java.util.ArrayList;
import java.util.List;

import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Missile extends GameObject {
    
    public Missile(int startX, int startY) {
        super(startX, startY, GameData.MISSILE_WIDTH, GameData.MISSILE_HEIGHT, GameData.getSprite("missile"));
    }

    @Override
    public void tickBehavior(GameController controller) {
        this.moveY(GameData.MISSILE_SPEED);
        List<GameObject> entities = new ArrayList<GameObject>(controller.getEntities());
        if(this.isOutOfBounds()) {
            controller.getEntities().remove(this);
        } else {
            for(GameObject entity : entities) {
                if(entity instanceof Ship && this.intersects(entity)) {
                    controller.shipHit();
                    controller.createExplosion(getX() + getWidth() / 2, getY() + getHeight());
                    controller.getEntities().remove(this);
                }
            }
        }
        
    }

}
