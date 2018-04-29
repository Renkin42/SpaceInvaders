package com.spaceInvaders;
/**
 * Holds some constants for the game in a single location for easy
 * access and modification.
 */
public interface GameData {


    /** Width of a laser fired by player's ship. */
    int LASER_WIDTH = 2;
    /** Height of a laser fired by player's ship. */
    int LASER_HEIGHT = 16;
    /** How far a laser moves in a single step. */
    int LASER_SPEED = 20;

    /** Width of a missile fired by an alien. */
    int MISSILE_WIDTH = 5;
    /** Height of a missile fired by an alien. */
    int MISSILE_HEIGHT = 20;
    /** How far a missile moves in a single step. */
    int MISSILE_SPEED = 8;
    
    int SHIP_WIDTH = 40;
    int SHIP_HEIGHT = 64;
    
    int ALIEN_WIDTH = 32;
    int ALIEN_HEIGHT = 24;
    int NUM_ALIENS_X = 11;
    int NUM_ALIENS_Y = 5;
    
    int ALIEN_POINTS = 10;
    int LEVEL_POINTS = 100;

    /** Width of game area. */
    int GAME_BOARD_WIDTH = 500;
    /** Height of game area. */
    int GAME_BOARD_HEIGHT = 600;

}
