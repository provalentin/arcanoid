package com.arcanoid.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by hh on 17.04.2015.
 */
public class GameScreen implements Screen{

    GamePole gamePole;

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    Sprite circle, player, brickie, heart, backgroud;

    Sprite brickies[];
    @Override
    public void show() {
        gamePole = new GamePole(10, 5);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        circle = new Sprite(new Texture("ball.png"));
        circle.setSize(gamePole.myCircle.radius * 2, gamePole.myCircle.radius * 2);
        player = new Sprite(new Texture("player.png"));
        player.setSize(gamePole.player.width, gamePole.player.height);

        brickie = new Sprite(new Texture("bricks/1.png"));

        brickies = new Sprite[4];
        for (int i = 0; i < 4; i++) {
            brickies[i] =new Sprite(new Texture("bricks/"+String.valueOf(i + 1)+".png"));
        }

        gamePole.setBrickieSize(brickies);

        heart = new Sprite(new Texture("heart.png"));

        heart.setSize(gamePole.screenWidth / 20, gamePole.screenWidth / 20);

        backgroud = new Sprite(new Texture("background.png"));
        backgroud.setSize(gamePole.screenWidth, gamePole.screenHeight);
        backgroud.setPosition(0, 0);

        Gdx.input.setInputProcessor(new InputListener(gamePole));
        //Gdx.input.setInputProcessor(new GestureDetector(new TEst(gamePole)));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.setPosition(gamePole.player.x, gamePole.player.y);

        batch.begin();
        backgroud.draw(batch);
        player.draw(batch);

        for (int i = 0; i < gamePole.circles.size(); i++) {
            if(gamePole.circles.size() == 1 || (!gamePole.circles.get(i).isStand)) {
                circle.setPosition(gamePole.circles.get(i).x - gamePole.circles.get(i).radius, gamePole.circles.get(i).y - gamePole.circles.get(i).radius);
                circle.draw(batch);
            }
        }

        for (int i = 0; i < gamePole.player.life; i++) {
            heart.setPosition(gamePole.screenWidth - gamePole.player.life * (heart.getHeight() * 1.5f) + heart.getHeight() * 1.5f * i,gamePole.player.y / 2 - heart.getHeight() / 2);
            heart.draw(batch);
        }
        batch.end();
        gamePole.drawBlocks(batch, brickies);
        gamePole.move(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
