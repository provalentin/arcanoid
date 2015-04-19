package com.arcanoid.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by hh on 17.04.2015.
 */
public class GamePole {
    Block blocks[][];

    MyCircle myCircle;
    ArrayList<MyCircle> circles;

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


    int sumOfFlyingBalls(){
        int sum = 0;
        for (int i = 0; i < circles.size(); i++) {
            if(circles.get(i).isFlying) sum++;
        }

        return sum;
    }
    public void move(float delta){
        player.x += playerVel * (screenWidth / (3 * 60));
        if(player.x < 0) player.x = 0;
        if(player.x > screenWidth - player.width) player.x = screenWidth - player.width;


        if(sumOfFlyingBalls() == 1){
            for (int i = 0; i < circles.size(); i++) {
                if(circles.get(i).isFlying){
                    circles.set(0, circles.get(i));
                    break;
                }
            }
            for (int i = 1; i < circles.size(); i++) {
                circles.remove(1);
            }

            circles.get(0).x = player.x + player.width / 2;
            circles.get(0).y = player.y + player.height + circles.get(0).radius;

        }else {
            for (int i = 0; i < circles.size(); i++) {
                circles.get(i).move(this);
            }
        }
    }

    GamePole(int level){
        FileHandle file = Gdx.files.internal("levels/" + String.valueOf(level) + ".level");
        String l = file.readString();
        String Stroke[] = l.split("\r\n");
        int W = Stroke[0].split(" ").length;
        int H = Stroke.length;
        blocks = new Block[W][H];
        this.W = W;
        this.H = H;
        blockWidth = screenWidth / W;
        blockHeight = 9 * blockWidth / 16;

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                blocks[i][j] = new Block(i * blockWidth, screenHeight - j * blockHeight - blockHeight, blockWidth, blockHeight, 0);
            }
        }
        for (int i = 0; i < Stroke.length; i++) {
            String values[] = Stroke[i].split(" ");
            for (int j = 0; j < values.length; j++) {
                if(values[j].equals("1")) blocks[j][i].setPower(1);
                if(values[j].equals("2")) blocks[j][i].setPower(2);
                if(values[j].equals("3")) blocks[j][i].setPower(3);

            }
        }


        player = new Player(screenWidth / 2 - playerWidth / 2, screenHeight / 8, playerWidth, playerHeight);

        myCircle = new MyCircle(player.x + player.width / 2 - circleRadius, player.y + player.height + circleRadius, circleRadius, new Vector2(0,0));

        circles = new ArrayList<MyCircle>();
        circles.add(new MyCircle(player.x + player.width / 2 - circleRadius, player.y + player.height + circleRadius, circleRadius, new Vector2(0,0)));
    }

    GamePole(int W, int H){
        blocks = new Block[W][H];

        this.W = W;
        this.H = H;
        blockWidth = screenWidth / W;
        blockHeight = 9 * blockWidth / 16;
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                blocks[i][j] = new Block(i * blockWidth, screenHeight - j * blockHeight - blockHeight, blockWidth, blockHeight, 0);
            }
        }
        player = new Player(screenWidth / 2 - playerWidth / 2, screenHeight / 8, playerWidth, playerHeight);

        myCircle = new MyCircle(player.x + player.width / 2 - circleRadius, player.y + player.height + circleRadius, circleRadius, new Vector2(0,0));

        circles = new ArrayList<MyCircle>();
        circles.add(new MyCircle(player.x + player.width / 2 - circleRadius, player.y + player.height + circleRadius, circleRadius, new Vector2(0,0)));
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

