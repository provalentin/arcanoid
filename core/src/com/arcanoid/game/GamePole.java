package com.arcanoid.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hh on 17.04.2015.
 */
public class GamePole {
    Block blocks[][];

    MyCircle myCircle;
    Player player;

    final int screenHeight = Gdx.graphics.getHeight();
    final int screenWidth = Gdx.graphics.getWidth();
    final float playerWidth = screenWidth / 6;
    final float playerHeight = screenHeight / 48;
    final float circleRadius = screenWidth / 32;
    float blockWidth;
    float blockHeight;
    int playerVel = 0;
    private int W, H;

    public void move(float delta){
        player.x += playerVel * (screenWidth / (3 * 60));
        if(player.x < 0) player.x = 0;
        if(player.x > screenWidth - player.width) player.x = screenWidth - player.width;

        if(myCircle.isStand){
            myCircle.x = player.x + player.width / 2;
            myCircle.y = player.y + player.height + myCircle.radius;
        }else
            myCircle.move(this);
    }


    GamePole(int W, int H){
        blocks = new Block[W][H];

        this.W = W;
        this.H = H;
        blockWidth = screenWidth / W;
        blockHeight = 9 * blockWidth / 16;
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                blocks[i][j] = new Block(i * blockWidth, screenHeight - j * blockHeight - blockHeight, blockWidth, blockHeight, 3);
            }
        }
        player = new Player(screenWidth / 2 - playerWidth / 2, screenHeight / 8, playerWidth, playerHeight);

        myCircle = new MyCircle(player.x + player.width / 2 - circleRadius, player.y + player.height + circleRadius, circleRadius, new Vector2(0,0));
    }

    public void setBrickieSize(Sprite[] brick){
        for (int i = 0; i < brick.length; i++) {
            brick[i].setSize(blockWidth, blockHeight);
        }
    }


    public void drawBlocks(SpriteBatch batch, Sprite[] brick){
        batch.begin();
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if(blocks[i][j].getPower() != 0) {
                    brick[blocks[i][j].getPower() - 1].setPosition(blocks[i][j].x, blocks[i][j].y);
                    brick[blocks[i][j].getPower() - 1].draw(batch);
                }
            }
        }
        batch.end();
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }
}

