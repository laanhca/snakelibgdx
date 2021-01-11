package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
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
        nameGame = new AVTextButton(225, 350, 100, 100, cam);
        play = new AVTextButton(250,200,50,50,cam);
        achivement = new AVImgButton("Achievements", 320, 50, 100, 100, cam);
        levels = new AVImgButton("Levels", 520, 50, 100, 100, cam);
        setting = new AVImgButton("Settings", 720, 50, 100, 100, cam);
        //exit = new AVImgButton("Close", 1050, 550, 50, 50, cam);

    }

    @Override
    public void handleInput() {
        if (play.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.PLAY);
        }
//        if(exit.isClicked()==true){
//            MyGdxGame.content.removeAll();
//            System.exit(-1);
//        }
        if(setting.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.SETTING);

        }if(achivement.isClicked()==true){
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
}
