package com.arcanoid.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hh on 17.04.2015.
 */
public class MyCircle extends Circle {

    Vector2 velocity;
    boolean isStand = true;

    public MyCircle(float x, float y, float radius, Vector2 velocity) {
        super(x, y, radius);
        this.velocity = velocity;
    }

    public void move(GamePole gamePole){
        if(!isStand) {
            x += velocity.x;
            y += velocity.y;
            if(x < radius){
                x = radius;
                velocity.x = -velocity.x;
            }

            if(y < radius){
                if(gamePole.numberOfStandCircle() == 1) {
                    gamePole.player.life--;

                }
                this.isStand = true;
            }

            if(x + radius > gamePole.screenWidth){
                velocity.x = -velocity.x;
                x = gamePole.screenWidth - radius;
            }

            if(y + radius > gamePole.screenHeight){
                velocity.y = -velocity.y;
                y = gamePole.screenHeight - radius;
            }

            if(!setReactionOnRect(gamePole.player, this)){
                for (int i = 0; i < gamePole.getW(); i++) {
                    for (int j = 0; j < gamePole.getH(); j++) {
                        if(gamePole.blocks[i][j].getPower() != 0 && setReactionOnRect(gamePole.blocks[i][j], this)){
                            gamePole.blocks[i][j].decPow();
                            break;
                        }
                    }
                }
            }

        }
    }

    private boolean setReactionOnRect(Rectangle rectangle, Circle myCircle){
        if(Intersector.overlaps(myCircle, rectangle)){
            Rectangle topSide = new Rectangle(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.width, 1);

            if(Intersector.overlaps(myCircle, topSide)){
                velocity.y = -velocity.y;
                y = topSide.y + topSide.height + radius;
                return true;
            }
            Rectangle leftSide = new Rectangle(rectangle.x, rectangle.y, 1, rectangle.height);

            if(Intersector.overlaps(myCircle, leftSide)){
                velocity.x = -velocity.x;
                x = leftSide.x - radius;
                return true;
            }
            Rectangle rightSide = new Rectangle(rectangle.x + rectangle.width - 1, rectangle.y, 1, rectangle.height);

            if(Intersector.overlaps(myCircle, rightSide)){
                velocity.x = -velocity.x;
                x = rightSide.x + rightSide.width + radius;
                return true;
            }

            Rectangle bottomSide = new Rectangle(rectangle.x, rectangle.y, rectangle.width, 1);


            if(Intersector.overlaps(myCircle, bottomSide)){
                velocity.y = -velocity.y;
                y = bottomSide.y - radius;
                return true;
            }
        }

        return false;
    }

    public void setVel(float velX, float velY){
        velocity.x = velX;
        velocity.y = velY;
    }
}
