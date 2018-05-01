package com.spaceInvaders.entities;
import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Explosion extends GameObject {
    
    private int tickCounter = 0;

    public Explosion(int startX, int startY) {
        super(startX, startY, GameData.EXPLOSION_SIZE, GameData.EXPLOSION_SIZE, GameData.getSprite("explosion"));
    }

    @Override
    public void tickBehavior(GameController controller) {
        tickCounter++;
        if(tickCounter == GameData.EXPLOSION_DURATION) {
            controller.getEntities().remove(this);
        }

    }

}
