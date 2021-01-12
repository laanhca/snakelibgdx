package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.Gdx;

public class MenuState extends GameState{
    AVTextButton play;
    AVTextButton nameGame;
    AVImgButton achivement;
    AVImgButton levels;
    AVImgButton setting;
    //AVImgButton exit;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        nameGame = new AVTextButton((GameConfig.GWIDTH-GameConfig.GWIDTH/10*5)/2-GameConfig.GWIDTH/10, GameConfig.GHEIGHT/2, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        play = new AVTextButton((GameConfig.GWIDTH-GameConfig.GWIDTH/15*11)/2-GameConfig.GWIDTH/15,GameConfig.GHEIGHT/3.5f,GameConfig.GWIDTH/15,GameConfig.GWIDTH/15,cam);
        achivement = new AVImgButton("Achievements", (GameConfig.GWIDTH-GameConfig.GWIDTH/10*4)/2, GameConfig.GHEIGHT/15f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        levels = new AVImgButton("Levels", (GameConfig.GWIDTH-GameConfig.GWIDTH/10*3)/2+GameConfig.GWIDTH/10, GameConfig.GHEIGHT/15f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        setting = new AVImgButton("Settings", (GameConfig.GWIDTH-GameConfig.GWIDTH/10*2)/2+GameConfig.GWIDTH/10*2, GameConfig.GHEIGHT/15f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        //exit = new AVImgButton("Close", 1050, 550, 50, 50, cam);

    }

    @Override
    public void handleInput() {
        if (play.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            MyGdxGame.content.getMusic("bbsong").setVolume(0.25f);
            gsm.pushState(GameStateManager.PLAY);
        }
//        if(exit.isClicked()==true){
//            MyGdxGame.content.removeAll();
//            System.exit(-1);
//        }
        if(setting.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.SETTING);

        }
        if(achivement.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.ACHIVEMENT);

        }if(levels.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.LEVEL_SELECT);

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        play.update(dt);
        //exit.update(dt);
        setting.update(dt);
        achivement.update(dt);
        achivement.update(dt);
        levels.update(dt);
    }

    @Override
    public void render() {
        sb.begin();
        play.render(sb,"tap to play");
        nameGame.render(sb,"snake");
        achivement.render(sb);
        levels.render(sb);
        setting.render(sb);
        //exit.render(sb);
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
