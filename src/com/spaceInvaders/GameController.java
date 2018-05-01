package com.spaceInvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.spaceInvaders.entities.Alien;
import com.spaceInvaders.entities.Explosion;
import com.spaceInvaders.entities.GameObject;
import com.spaceInvaders.entities.Ship;

public class GameController extends TimerTask implements KeyListener {

    private List<GameObject> entities;
    private int score;
    private int level;
    private int lives;
    private int numAliens;
    private GamePanel gameArea;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean firePressed;
    private boolean laserOnScreen;
    private boolean missileOnScreen;
    private GameState state;
    private int lifeThreshHold;

    public GameController() {
        state = GameState.START;
        entities = new ArrayList<GameObject>();
        gameArea = new GamePanel(this);

    }
    
    public void setPlayField() {
        entities.clear();
        numAliens = GameData.NUM_ALIENS_X * GameData.NUM_ALIENS_Y;
        entities.add(new Ship());
        for (int y = 0; y < GameData.NUM_ALIENS_Y; y++) {
            for (int x = 0; x < GameData.NUM_ALIENS_X; x++) {
                entities.add(new Alien(GameData.ALIEN_WIDTH * 5 / 4 * x + GameData.ALIEN_WIDTH / 4,
                        GameData.ALIEN_HEIGHT * y * 9 / 8 + 30, level));
            }
        }
        state = GameState.RUNNING;
    }

    public void newGame() {
        score = 0;
        level = 1;
        lives = GameData.START_LIVES;
        lifeThreshHold = 1000;
        setPlayField();
        state = GameState.RUNNING;
    }

    public List<GameObject> getEntities() {
        return entities;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isFirePressed() {
        return firePressed;
    }

    public boolean isLaserOnScreen() {
        return laserOnScreen;
    }

    public void setLaserOnScreen(boolean laserOnScreen) {
        this.laserOnScreen = laserOnScreen;
    }

    public boolean isMissileOnScreen() {
        return missileOnScreen;
    }

    public void setMissileOnScreen(boolean missileOnScreen) {
        this.missileOnScreen = missileOnScreen;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        score += points;
        if(score >= lifeThreshHold) {
            lives++;
            lifeThreshHold += 1000;
        }
    }

    public void resetPoints() {
        score = 0;
    }

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        level++;
        addPoints(100);
        setPlayField();
    }

    public void resetLevel() {
        level = 1;
    }

    public int getLives() {
        return lives;
    }

    public void shipHit() {
        if(lives == 0) {
            gameOver();
        } else {
            lives--;
        }
    }

    public void resetLives() {
        lives = 3;
    }

    public int getNumAliens() {
        return numAliens;
    }

    public void alienHit() {
        numAliens--;
        addPoints(10);
        laserOnScreen = false;
        if (numAliens == 0) {
            nextLevel();
        }
    }

    public GamePanel getGameArea() {
        return gameArea;
    }

    public GameState getGameState() {
        return state;
    }
    
    public void createExplosion(int x, int y) {
        this.entities.add(new Explosion(x - GameData.EXPLOSION_SIZE, y - GameData.EXPLOSION_SIZE));
        GameData.playSound("kerboom", -20);
    }
    
    public void gameOver() {
        this.state = GameState.GAME_OVER;
    }

    @Override
    public void run() {
        if (state == GameState.RUNNING) {
            // Modifying the list while looping through it causes errors, so we create a
            // temp copy
            List<GameObject> tempEntities = new ArrayList<GameObject>(entities);
            for (GameObject entity : tempEntities) {
                entity.tickBehavior(this);
            }
        }
        gameArea.repaint();

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            if (state == GameState.RUNNING) {
                firePressed = true;
            } else if (state == GameState.PAUSED) {
                state = GameState.RUNNING;
            } else {
                newGame();
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (state == GameState.RUNNING) {
                state = GameState.PAUSED;
            } else {
                System.exit(0);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            firePressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

}
