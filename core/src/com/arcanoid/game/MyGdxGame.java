package com.arcanoid.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {


    GameScreen gameScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}