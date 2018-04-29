package com.spaceInvaders;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Holds some constants for the game in a single location for easy
 * access and modification.
 */
public final class GameData {

    /** Width of a laser fired by player's ship. */
    public static final int LASER_WIDTH = 2;
    /** Height of a laser fired by player's ship. */
    public static final int LASER_HEIGHT = 16;
    /** How far a laser moves in a single step. */
    public static final int LASER_SPEED = 20;

    /** Width of a missile fired by an alien. */
    public static final int MISSILE_WIDTH = 5;
    /** Height of a missile fired by an alien. */
    public static final int MISSILE_HEIGHT = 20;
    /** How far a missile moves in a single step. */
    public static final int MISSILE_SPEED = 8;
    
    public static final int SHIP_WIDTH = 40;
    public static final int SHIP_HEIGHT = 64;
    
    public static final int ALIEN_WIDTH = 32;
    public static final int ALIEN_HEIGHT = 24;
    public static final int NUM_ALIENS_X = 11;
    public static final int NUM_ALIENS_Y = 5;
    
    public static final int ALIEN_POINTS = 10;
    public static final int LEVEL_POINTS = 100;

    /** Width of game area. */
    public static final int GAME_BOARD_WIDTH = 500;
    /** Height of game area. */
    public static final int GAME_BOARD_HEIGHT = 600;
    
    public static final Image getSprite(String name) {
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(GameData.class.getResource("/assets/sprites/" + name + ".png"));
        } catch (Exception e) {
            System.err.println("Could not find sprite: " + name + ".png");
            //Draw white box with red question mark to indicate missing sprite
            sprite = new BufferedImage(8, 8, BufferedImage.TYPE_3BYTE_BGR);
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    if(x == 0 || x == 7 || y == 0 || y == 7) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 2 && y == 2) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 3 && y == 1) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 4 && y == 1) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 5 && y == 2) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 5 && y == 3) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 4 && y == 4) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else if (x == 4 && y == 6) {
                        sprite.setRGB(x, y, Color.RED.getRGB());
                    } else {
                        sprite.setRGB(x, y, Color.WHITE.getRGB());
                    }  
                }
            } 
        }
        return sprite;
    }

}
