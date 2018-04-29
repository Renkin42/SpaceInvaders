package com.spaceInvaders;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JPanel;

import com.spaceInvaders.entities.GameObject;

public class GamePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private GameController controller;
    private Timer timer;

    public GamePanel(GameController controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(GameData.GAME_BOARD_WIDTH, GameData.GAME_BOARD_HEIGHT));
        this.addKeyListener(controller);
        timer = new Timer();
        timer.scheduleAtFixedRate(controller, 0, 50);
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        this.requestFocus();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,  0,  this.getWidth(),  this.getHeight());
        for (GameObject entity : controller.getEntities()) {
            entity.drawSprite(g);
        }
        
        if(controller.getGameState() == GameState.PAUSED) {
            g.setColor(new Color(0, 0, 0, 128));
            g.fillRect(0,  0, this.getWidth(), this.getHeight());
            g.setColor(Color.WHITE);
            g.drawString("PAUSED", GameData.GAME_BOARD_WIDTH / 2 - 100, GameData.GAME_BOARD_HEIGHT / 2);
            g.drawString("Press Space to continue.", GameData.GAME_BOARD_WIDTH / 2 - 100, GameData.GAME_BOARD_HEIGHT / 2 + 20);
        }
        if(controller.getGameState() == GameState.START) {
            g.setColor(Color.WHITE);
            g.drawString("SPACE INVADERS!", GameData.GAME_BOARD_WIDTH / 2 - 100, GameData.GAME_BOARD_HEIGHT / 2);
            g.drawString("Press space to begin", GameData.GAME_BOARD_WIDTH / 2 - 100, GameData.GAME_BOARD_HEIGHT / 2 + 20);
        } else {
            String gameState = String.format("Lives: %d | Level: %d | Score: %d", controller.getLives(),
                    controller.getLevel(), controller.getScore());
            g.setColor(Color.WHITE);
            g.drawString(gameState, 10, 20);
        }
        
    }

}
