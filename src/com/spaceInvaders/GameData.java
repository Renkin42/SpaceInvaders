package com.spaceInvaders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Holds some constants for the game in a single location for easy access and
 * modification.
 */
public final class GameData {

    /** Width of a laser fired by player's ship. */
    public static final int LASER_WIDTH = 4;
    /** Height of a laser fired by player's ship. */
    public static final int LASER_HEIGHT = 16;
    /** How far a laser moves in a single step. */
    public static final int LASER_SPEED = 15;

    /** Width of a missile fired by an alien. */
    public static final int MISSILE_WIDTH = 12;
    /** Height of a missile fired by an alien. */
    public static final int MISSILE_HEIGHT = 32;
    /** How far a missile moves in a single step. */
    public static final int MISSILE_SPEED = 2;

    public static final int SHIP_WIDTH = 40;
    public static final int SHIP_HEIGHT = 64;
    public static final int SHIP_SPEED = 4;

    public static final int ALIEN_WIDTH = 32;
    public static final int ALIEN_HEIGHT = 24;
    public static final int NUM_ALIENS_X = 11;
    public static final int NUM_ALIENS_Y = 5;
    public static final int ALIEN_FIRE_RATE = 8;
    
    public static final int EXPLOSION_SIZE = 32;
    public static final int EXPLOSION_DURATION = 20;

    public static final int ALIEN_POINTS = 10;
    public static final int LEVEL_POINTS = 100;
    
    public static final int START_LIVES = 5;

    /** Width of game area. */
    public static final int GAME_BOARD_WIDTH = 600;
    /** Height of game area. */
    public static final int GAME_BOARD_HEIGHT = 600;

    public static final Image getSprite(String name) {
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(GameData.class.getResource("/assets/sprites/" + name + ".png"));
        } catch (Exception e) {
            System.err.println("Could not find sprite: " + name + ".png");
            // Draw white box with red question mark to indicate missing sprite
            int spriteSize = 16;
            sprite = new BufferedImage(spriteSize, spriteSize, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = sprite.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, spriteSize, spriteSize);
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(spriteSize / 16));
            g.drawRect(spriteSize / 32, spriteSize / 32, spriteSize * 15 / 16, spriteSize * 15 / 16);
            g.setFont(new Font(Font.SERIF, Font.BOLD, spriteSize * 5 / 4));
            g.drawString("?", spriteSize * 3 / 16, spriteSize * 7 / 8);
        }
        return sprite;
    }
    
    public static final Font getFont(String name, int style, int size) {
        InputStream fontStream = GameData.class.getResourceAsStream("/assets/fonts/" + name + ".ttf");
        Font newFont;
        try {
            newFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            newFont = newFont.deriveFont(style, size);
        } catch (Exception e) {
            System.err.println("Could not find font: " + name + ".ttf");
            newFont = new Font(Font.SANS_SERIF, style, size);
        }
        return newFont;
    }
    
    public static Clip getSound(String name, float volume) {
        Clip soundClip = null;
        try {
            URL soundURL = GameData.class.getResource("/assets/sounds/" + name + ".wav");
            AudioInputStream soundStream = AudioSystem.getAudioInputStream(soundURL);
            soundClip = AudioSystem.getClip();
            soundClip.open(soundStream);
            FloatControl volumeControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        } catch (Exception e) {
            System.err.println("Audio file not found: " + name + ".wav");
        }
        return soundClip;
    }

}
