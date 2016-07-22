package com.arcanoid.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by hh on 17.04.2015.
 */
public class InputListener implements InputProcessor{

    GamePole gamePole;
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.A){
            gamePole.playerVel = -1;

        }else
        if(keycode == Input.Keys.D){
            gamePole.playerVel = 1;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(screenX < gamePole.screenWidth / 2){
            gamePole.playerVel = 0;

        }else{
            gamePole.playerVel = 0;
        }
        gamePole.player.x = screenX;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gamePole.playerVel = 0;
        if(gamePole.sumOfFlyingBalls() == 0){
            gamePole.circles.get(0).isFlying = true;
            gamePole.circles.get(0).setVel(10, 7);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(screenX < gamePole.screenWidth / 2){
            gamePole.playerVel = 0;

        }else{
            gamePole.playerVel = 0;
        }
        gamePole.player.x = screenX;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public InputListener(GamePole gamePole) {
        this.gamePole = gamePole;
    }
}
