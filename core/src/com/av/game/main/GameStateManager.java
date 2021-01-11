package com.av.game.main;

import com.av.game.states.GameState;
import com.av.game.states.LevelSelect;
import com.av.game.states.MenuState;
import com.av.game.states.PlayState;
import com.av.game.states.SettingState;

import java.util.Stack;

public class GameStateManager {
    private MyGdxGame game;

    private Stack<GameState> gameStates;
 //   public  static final int SCALE = 64;
    public static final int MENU = 83774392;
    public static final int PLAY = 388031654;
    public static final int LEVEL_SELECT = -9238732;
    public static final int SETTING = -1212333;
    public static final int ACHIVEMENT = -1212333;

    public GameStateManager(MyGdxGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(MENU);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();
    }

    public MyGdxGame game() { return game; }

    private GameState getState(int state) {
        if(state == MENU) return new MenuState(this);
        if(state == PLAY) return new PlayState(this);
        if(state == LEVEL_SELECT) return new LevelSelect(this);
        if(state == SETTING) return new SettingState(this);
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
}
