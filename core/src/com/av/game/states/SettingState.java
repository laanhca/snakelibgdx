package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static com.av.game.main.MyGdxGame.SCALE;

public class SettingState extends GameState{
    AVImgButton exitS;
    AVImgButton vol;
    AVImgButton upB;
    AVImgButton downB;
    private  BitmapFont font;
    public SettingState(GameStateManager gsm) {
        super(gsm);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AB.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 128;
        font= generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        exitS = new AVImgButton("Close", GameConfig.GWIDTH-GameConfig.GWIDTH/10*1.1f, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*1.1f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        vol = new AVImgButton("Volume", (GameConfig.GWIDTH-GameConfig.GWIDTH/10)/2-GameConfig.GWIDTH/10, GameConfig.GHEIGHT/2, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        upB = new AVImgButton("upVol", (GameConfig.GWIDTH-GameConfig.GWIDTH/10)/2 +GameConfig.GWIDTH/10, GameConfig.GHEIGHT/2, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        downB = new AVImgButton("downVol", (GameConfig.GWIDTH-GameConfig.GWIDTH/10)/2 +GameConfig.GWIDTH/10*3, GameConfig.GHEIGHT/2, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
    }

    @Override
    public void handleInput() {
        if(exitS.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.MENU);
        }if(upB.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            MyGdxGame.content.getMusic("bbsong").setVolume(1);
        }if(downB.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            MyGdxGame.content.getMusic("bbsong").setVolume(0);
        }
        if(vol.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            MyGdxGame.content.getMusic("bbsong").setVolume(0.5f);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        exitS.update(dt);
        vol.update(dt);
        upB.update(dt);
        downB.update(dt);

    }

    @Override
    public void render() {
        sb.begin();
        font.draw(sb,"Set volume:  ",GameConfig.GWIDTH/10,GameConfig.GHEIGHT/2 + GameConfig.GWIDTH/12);
        exitS.render(sb);
        vol.render(sb);
        upB.render(sb);
        downB.render(sb);
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
