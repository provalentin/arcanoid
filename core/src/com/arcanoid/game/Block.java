package com.arcanoid.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hh on 17.04.2015.
 */
public class Block extends Rectangle{
    //hui

    private int power;

    enum Type{
        simple, health, new_balls
    }

    Type type;

    public int getPower() {
        return power;
    }

    public void setType(Type type, int power){
        this.power = power;
        this.type = type;
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

    public void intersectListener(GamePole gamePole){
        switch (type){
            case health:
                gamePole.player.life++;
                break;

            case new_balls:
                gamePole.circles.add(new MyCircle(gamePole.circles.get(0).x, gamePole.circles.get(0).y,
                                                    gamePole.circles.get(0).radius,
                                                    new Vector2(-gamePole.circles.get(0).velocity.x,
                                                                -gamePole.circles.get(0).velocity.y)));
                gamePole.circles.get(gamePole.circles.size() - 1).isFlying = true;
                break;

        }
        decPow();
    }
}
