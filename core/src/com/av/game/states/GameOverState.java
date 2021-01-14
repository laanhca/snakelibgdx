package com.av.game.states;

import com.av.game.entities.GameObject;
import com.av.game.entities.Player;
import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import static com.av.game.main.MyGdxGame.SCALE;

public class GameOverState extends GameState {
    private AVImgButton exitS;
    private AVTextButton overGame;
    private AVTextButton score;

    private AVImgButton home;
    private BitmapFont font;
    private TiledMap tileMap;
    public GameOverState(GameStateManager gsm) {
        super(gsm);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AB.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 192;
        font= generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        overGame = new AVTextButton((GameConfig.GWIDTH-GameConfig.GWIDTH/20*9)/2-GameConfig.GWIDTH/20, GameConfig.GHEIGHT-GameConfig.GWIDTH/20*2, GameConfig.GWIDTH/20, GameConfig.GWIDTH/20, cam);
        score = new AVTextButton((GameConfig.GWIDTH-GameConfig.GWIDTH/25*10)/2-GameConfig.GWIDTH/25, GameConfig.GHEIGHT-GameConfig.GWIDTH/20*4, GameConfig.GWIDTH/25, GameConfig.GWIDTH/25, cam);
        exitS = new AVImgButton("home", GameConfig.GWIDTH-GameConfig.GWIDTH/10*1.1f, GameConfig.GHEIGHT-GameConfig.GWIDTH/10*1.1f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
        home = new AVImgButton("restart", (GameConfig.GWIDTH-GameConfig.GWIDTH/10*3)/2+GameConfig.GWIDTH/10, GameConfig.GHEIGHT/15f, GameConfig.GWIDTH/10, GameConfig.GWIDTH/10, cam);
    }

    @Override
    public void handleInput() {
        if(exitS.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.MENU);

        }if(home.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.PLAY);

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        exitS.update(dt);
        home.update(dt);
    }

    @Override
    public void render() {
        sb.begin();
        exitS.render(sb);
        home.render(sb);
        overGame.render(sb,"game over");
        score.render(sb,"your score");
        font.draw(sb,String.valueOf(PlayState.scoreS),(GameConfig.GWIDTH-198*5),GameConfig.GHEIGHT-GameConfig.GWIDTH/20*5);
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
