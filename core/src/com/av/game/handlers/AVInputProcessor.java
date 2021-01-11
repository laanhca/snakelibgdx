package com.av.game.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class AVInputProcessor extends InputAdapter {

    public boolean mouseMoved(int x, int y) {
        AVInput.x = x;
        AVInput.y = y;
        return true;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        AVInput.x = x;
        AVInput.y = y;
        AVInput.down = true;
        return true;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        AVInput.x = x;
        AVInput.y = y;
        AVInput.down = true;
        return true;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        AVInput.x = x;
        AVInput.y = y;
        AVInput.down = false;
        return true;
    }

    public boolean keyDown(int k) {
        if(k == Input.Keys.Z) AVInput.setKey(AVInput.BUTTON1, true);
        if(k == Input.Keys.X) AVInput.setKey(AVInput.BUTTON2, true);
        return true;
    }

    public boolean keyUp(int k) {
        if(k == Input.Keys.Z) AVInput.setKey(AVInput.BUTTON1, false);
        if(k == Input.Keys.X) AVInput.setKey(AVInput.BUTTON2, false);
        return true;
    }
}
