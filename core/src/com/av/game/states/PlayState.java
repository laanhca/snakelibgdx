package com.av.game.states;


import com.av.game.entities.BodySnake;
import com.av.game.entities.GameObject;
import com.av.game.entities.Player;
import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
import com.av.game.main.Direction;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;


public class PlayState extends GameState {
    private AVImgButton exitS;
    private AVImgButton rightB;
    private AVImgButton leftB;
    private AVImgButton upB;
    private AVImgButton downB;
    private AVTextButton score;
    private int scoreS;
    private BitmapFont font;

    private TiledMap tileMap;
    public static int level = 1;
    public static float tileMapWidth;
    public static float tileMapHeight;
    public static float tileSize;
    private TiledMapRenderer tmRenderer;

    private GameObject bunny;
    private Player snake;
    private boolean bunnyLost;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        exitS = new AVImgButton("Close", 1050, 550, 50, 50, cam);
        leftB = new AVImgButton("left", 64, 128, 64, 64, cam);
        rightB = new AVImgButton("right", 192, 128, 64, 64, cam);
        upB = new AVImgButton("up", 128, 192, 64, 64, cam);
        downB = new AVImgButton("down", 128, 64, 64, 64, cam);
        score = new AVTextButton(10, 576, 20, 20, cam);
        scoreS = 0;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        tileMap = new TiledMap();
        loadMap();
        bunnyLost=false;
       snake = new Player(MyGdxGame.content.getAtlas("snakeset"), 64, 128);
       // snake.setRotation(90); // quay lên
        bunny = new GameObject(MyGdxGame.content.getAtlas("snakeset").findRegion("bunny"), foodRandX(), foodRandY());
        System.out.println(bunny.getX()+"  "+bunny.getY());
    }

    @Override
    public void handleInput() {
        if (exitS.isClicked() == true) {
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.MENU);
        }
        if (leftB.isClicked() == true && snake.getDir()!=Direction.RIGHT) {
            MyGdxGame.content.getSound("direct").play();
           // snake.setRotation(270);
            snake.setDir(Direction.LEFT);
           // gsm.pushState(GameStateManager.MENU);
        }
        if (rightB.isClicked() == true&& snake.getDir()!=Direction.LEFT) {
            MyGdxGame.content.getSound("direct").play();
            snake.setDir(Direction.RIGHT);
            //snake.setRotation(90);
            //gsm.pushState(GameStateManager.MENU);
        }
        if (upB.isClicked() == true&& snake.getDir()!=Direction.DOWN) {
            MyGdxGame.content.getSound("direct").play();
            snake.setDir(Direction.UP);
           // snake.setRotation(180);
           // gsm.pushState(GameStateManager.MENU);
        }
        if (downB.isClicked() == true&& snake.getDir()!=Direction.UP) {
            MyGdxGame.content.getSound("direct").play();
            snake.setDir(Direction.DOWN);
           // snake.setRotation(0);
           // gsm.pushState(GameStateManager.MENU);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        exitS.update(dt);
        leftB.update(dt);
        rightB.update(dt);
        upB.update(dt);
        downB.update(dt);
        randomBunny();
        snake.update(dt);
        if(snake.checkCol(bunny)==true){
            scoreS++;
            bunnyLost=true;
            snake.grow();
            MyGdxGame.content.loadSound("sfx/collect.wav");
            System.out.println(scoreS);}
        if(snake.die()==true)gsm.pushState(GameStateManager.MENU);


    }

    @Override
    public void render() {

        sb.begin();
        exitS.render(sb);

        tmRenderer.setView(cam);
        tmRenderer.render();
        if(bunnyLost==false)bunny.draw(sb);

       snake.render(sb);
        rightB.render(sb);
        leftB.render(sb);
        upB.render(sb);
        downB.render(sb);
        score.render(sb, "score");
        font.draw(sb, String.valueOf(scoreS), 50, 570);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public void loadMap() {
        // load tile map and map renderer
        try {
            tileMap = new TmxMapLoader().load("maps/level" + level + ".tmx");
        } catch (Exception e) {
            System.out.println("Cannot find file: maps/level" + level + ".tmx");
            // Gdx.app.exit();
        }
        MapProperties prop = tileMap.getProperties();
        tileSize = (int) tileMap.getProperties().get("tilewidth");
        tileMapWidth = (int) prop.get("width", Integer.class)*tileSize;
        //tileMapWidth = 18*tileSize;
        tileMapHeight = (int) prop.get("height", Integer.class)*tileSize;
        //tileMapHeight = 10*tileSize;


        tmRenderer = new OrthogonalTiledMapRenderer(tileMap);


    }
    private float foodRandX() {
        return MathUtils.random(1, 12) * 64;
    }

    private float foodRandY() {
        return MathUtils.random(1, 8) * 64;
    }
    private void randomBunny(){
        if(bunnyLost==true){
            bunny = new GameObject(MyGdxGame.content.getAtlas("snakeset").findRegion("bunny"), foodRandX(), foodRandY());
            bunnyLost=false;}
    }


}
