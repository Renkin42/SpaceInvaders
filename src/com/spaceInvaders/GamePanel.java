package com.spaceInvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
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
    private Font titleFont = GameData.getFont("sofachrome", Font.PLAIN, 28);
    private Font subTitleFont = GameData.getFont("xirod", Font.PLAIN, 16);
    private Font dataFont = GameData.getFont("neutronium", Font.PLAIN, 28);
    private Image backGroundImg = GameData.getSprite("space");
    private Image failureImage = GameData.getSprite("sloppy");

    public GamePanel(GameController controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(GameData.GAME_BOARD_WIDTH, GameData.GAME_BOARD_HEIGHT));
        this.addKeyListener(controller);
        timer = new Timer();
        timer.scheduleAtFixedRate(controller, 0, 16);
        
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.requestFocus();
        GameData.loopSound("twilight", -20);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backGroundImg, 0, 0, this.getWidth(), this.getHeight(), null);

        if (controller.getGameState() == GameState.START) {
            g.setColor(Color.WHITE);
            g.setFont(titleFont);
            g.drawString("SPACE INVADERS!", GameData.GAME_BOARD_WIDTH / 2 - 225, GameData.GAME_BOARD_HEIGHT / 2);
            g.setFont(subTitleFont);
            g.drawString("Press space to begin", GameData.GAME_BOARD_WIDTH / 2 - 150,
                    GameData.GAME_BOARD_HEIGHT / 2 + 35);
        } else {
            List<GameObject> entities = new ArrayList<GameObject>(controller.getEntities());
            for (GameObject entity : entities) {
                entity.drawSprite(g);
            }

            if (controller.getGameState() == GameState.PAUSED) {
                g.setColor(new Color(0, 0, 0, 128));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.WHITE);
                g.setFont(titleFont);
                g.drawString("PAUSED", GameData.GAME_BOARD_WIDTH / 2 - 100, GameData.GAME_BOARD_HEIGHT / 2);
                g.setFont(subTitleFont);
                g.drawString("Press Space to continue.", GameData.GAME_BOARD_WIDTH / 2 - 175,
                        GameData.GAME_BOARD_HEIGHT / 2 + 35);
            } else if (controller.getGameState() == GameState.GAME_OVER) {
                g.setColor(new Color(0, 0, 0, 128));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.WHITE);
                g.setFont(titleFont);
                g.drawString("GAME OVER", GameData.GAME_BOARD_WIDTH / 2 - 150, GameData.GAME_BOARD_HEIGHT / 2 - 150);
                g.drawImage(failureImage, GameData.GAME_BOARD_WIDTH / 2 - 128, GameData.GAME_BOARD_HEIGHT / 2 - 128, 
                        256, 256, null);
                g.setFont(subTitleFont);
                g.drawString("Press Space to start new game.", GameData.GAME_BOARD_WIDTH / 2 - 225,
                        GameData.GAME_BOARD_HEIGHT / 2 + 150);
            }
            String gameState = String.format("Lives: %d | Level: %02d | Score: %03d", controller.getLives(),
                    controller.getLevel(), controller.getScore());
            g.setColor(Color.WHITE);
            g.setFont(dataFont);
            g.drawString(gameState, GameData.GAME_BOARD_WIDTH / 2 - 225, 20);
        }

    }

}
