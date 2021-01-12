package com.av.game.main;

import com.av.game.states.AchievementState;
import com.av.game.states.GameState;
import com.av.game.states.LevelSelect;
import com.av.game.states.MenuState;
import com.av.game.states.ParallaxBackground;
import com.av.game.states.ParallaxLayer;
import com.av.game.states.PlayState;
import com.av.game.states.SettingState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

public class GameStateManager {
    private MyGdxGame game;

    private Stack<GameState> gameStates;

 //   public  static final int SCALE = 64;
    public static final int MENU = 83774392;
    public static final int PLAY = 388031654;
    public static final int LEVEL_SELECT = -9238732;
    public static final int SETTING = -1212333;
    public static final int ACHIVEMENT = -1002333;

    TextureRegion textureRegion;
    ParallaxBackground background;

    public GameStateManager(MyGdxGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        textureRegion= new TextureRegion(new Texture(Gdx.files.internal("bg.png")));
        background = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(textureRegion, new Vector2(1, 1), new Vector2(0, 0)),
        }, GameConfig.GWIDTH, GameConfig.GHEIGHT, new Vector2(50, 0));
        pushState(MENU);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
        background.render(dt);
    }

    public void render() {
        gameStates.peek().render();

    }

    public MyGdxGame game() { return game; }

    private GameState getState(int state) {
        if(state == MENU) return new MenuState(this);
        if(state == PLAY) {
        return new PlayState(this);}
        if(state == LEVEL_SELECT) return new LevelSelect(this);
        if(state == SETTING) return new SettingState(this);
        if(state == ACHIVEMENT) return new AchievementState(this);
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }
    public void pause(){
        pauseState();
    }
    public void pauseState(){
            gameStates.peek().pause();
    }
    public void resume(){
        gameStates.peek().resume();
    }
}
