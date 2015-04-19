package com.arcanoid.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hh on 17.04.2015.
 */
public class Player extends Rectangle{

    int life;
    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);
        life = 3;
    }
}
