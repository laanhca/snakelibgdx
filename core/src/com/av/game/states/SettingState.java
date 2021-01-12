package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;

public class SettingState extends GameState{
    AVImgButton exitS;
    public SettingState(GameStateManager gsm) {
        super(gsm);
        exitS = new AVImgButton("Close", GameConfig.GWIDTH-GameConfig.GWIDTH/10*1.1f, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*1.1f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
    }

    @Override
    public void handleInput() {
        if(exitS.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.MENU);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        exitS.update(dt);

    }

    @Override
    public void render() {
        sb.begin();
        exitS.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
