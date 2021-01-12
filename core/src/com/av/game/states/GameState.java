package com.av.game.states;

import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {
    protected GameStateManager gsm;
    protected MyGdxGame game;
    protected OrthographicCamera cam;
    protected SpriteBatch sb;
    public GameState(GameStateManager gsm) {

        this.gsm = gsm;
        this.game= gsm.game();
        this.cam = game.getCamera();
        this.sb= game.getSpriteBatch();
    }

    public abstract void handleInput();

    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose() ;
    public abstract void pause();
    public abstract void resume() ;


}
