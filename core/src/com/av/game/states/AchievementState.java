package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

public class AchievementState extends GameState{
    private AVImgButton exitS;
    private BitmapFont font;
    private String topScore;

    public AchievementState(GameStateManager gsm) {
        super(gsm);
        exitS = new AVImgButton("Close", GameConfig.GWIDTH-GameConfig.GWIDTH/10*1.1f, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*1.1f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AB.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        font= generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        readFile();


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
        font.draw(sb, topScore, GameConfig.GWIDTH / 2.2f, GameConfig.GHEIGHT / 1.5f);
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
    public void readFile() {
        FileHandle f = Gdx.files.internal("data.txt");
        topScore= f.readString();
    }
}
