package com.spaceInvaders;

import javax.swing.JFrame;

public class SpaceInvaders {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders!");
        GameController controller = new GameController();
        frame.add(controller.getGameArea());
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
