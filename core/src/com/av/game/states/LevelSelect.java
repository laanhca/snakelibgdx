package com.av.game.states;

import com.av.game.entities.Player;
import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;

public class LevelSelect extends GameState{
    AVImgButton exitS;
    AVTextButton easy;
    AVTextButton medium;
    AVTextButton hard;
    public LevelSelect(GameStateManager gsm) {
        super(gsm);
        exitS = new AVImgButton("exit_btn", GameConfig.GWIDTH-GameConfig.GWIDTH/10*1.2f, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*1.2f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        easy = new AVTextButton( (GameConfig.GWIDTH-GameConfig.GWIDTH/10*4)/2-GameConfig.GWIDTH/10, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*2, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        medium = new AVTextButton( (GameConfig.GWIDTH-GameConfig.GWIDTH/10*6)/2-GameConfig.GWIDTH/10, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*3-GameConfig.GWIDTH/20, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        hard = new AVTextButton( (GameConfig.GWIDTH-GameConfig.GWIDTH/10*4)/2-GameConfig.GWIDTH/10, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*4-GameConfig.GWIDTH/20*2, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
    }

    @Override
    public void handleInput() {
        if(exitS.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();

            gsm.pushState(GameStateManager.MENU);
        }
        if(easy.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            Player.setTimeStateDK(0.2f);
            gsm.pushState(GameStateManager.MENU);
        }
        if(medium.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            Player.setTimeStateDK(0.15f);
            gsm.pushState(GameStateManager.MENU);
        }
        if(hard.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            Player.setTimeStateDK(0.09f);
            gsm.pushState(GameStateManager.MENU);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        exitS.update(dt);
        easy.update(dt);
        medium.update(dt);
        hard.update(dt);
    }

    @Override
    public void render() {
        sb.begin();
        exitS.render(sb);
        easy.render(sb,"easy");
        medium.render(sb,"medium");
        hard.render(sb,"hard");
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
