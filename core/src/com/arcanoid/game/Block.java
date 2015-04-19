package com.arcanoid.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hh on 17.04.2015.
 */
public class Block extends Rectangle{

    private int power;

    public int getPower() {
        return power;
    }

    public Block(float x, float y, float width, float height, int power) {
        super(x, y, width, height);
        this.power = power;
    }

    public void decPow(){
        power--;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
