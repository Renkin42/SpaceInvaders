package com.spaceInvaders.entities;
import com.spaceInvaders.GameController;
import com.spaceInvaders.GameData;

public class Alien extends GameObject implements Shooter<Missile> {

    public Alien(int startX, int startY) {
        super(startX, startY, GameData.ALIEN_WIDTH, GameData.ALIEN_HEIGHT, GameData.getSprite("alien"));
    }

    @Override
    public Missile fire() {
        int missileX = this.getX() + this.getWidth() / 2 - GameData.MISSILE_WIDTH / 2;
        int missileY = this.getY() + this.getHeight();
        return new Missile(missileX , missileY);
    }

    @Override
    public void tickBehavior(GameController controller) {
        // TODO Auto-generated method stub
        
    }

}
