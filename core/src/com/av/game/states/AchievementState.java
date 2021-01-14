package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
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

import sun.tools.jstat.Scale;

public class AchievementState extends GameState{
    private AVImgButton exitS;
    private BitmapFont font;
    private String topScore;
    private AVTextButton topScoreB;

    public AchievementState(GameStateManager gsm) {
        super(gsm);
        topScoreB = new AVTextButton((GameConfig.GWIDTH-GameConfig.GWIDTH/20*10)/2-GameConfig.GWIDTH/20, GameConfig.GHEIGHT/1.5f, GameConfig.GWIDTH/20, GameConfig.GWIDTH/20, cam);
        exitS = new AVImgButton("exit_btn", GameConfig.GWIDTH-GameConfig.GWIDTH/10*1.1f, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*1.2f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AB.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 192;
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
        topScoreB.render(sb,"high score ");
        font.draw(sb, topScore, GameConfig.GWIDTH / 2.2f, GameConfig.GHEIGHT /2f);
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
        FileHandle f = Gdx.files.local("data.txt");
        topScore= f.readString();

    }
}
