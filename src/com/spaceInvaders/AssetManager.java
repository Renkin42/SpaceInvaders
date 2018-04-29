package com.spaceInvaders;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public final class AssetManager {
    
    public static Image getSprite(String name) {
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(AssetManager.class.getResource("/assets/sprites/" + name + ".png"));
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
