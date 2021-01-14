package com.av.game.states;


import com.av.game.entities.BodySnake;
import com.av.game.entities.GameObject;
import com.av.game.entities.Player;
import com.av.game.handlers.AVImgButton;
import com.av.game.handlers.AVTextButton;
import com.av.game.main.Direction;
import com.av.game.main.GameConfig;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;

import static com.av.game.main.MyGdxGame.SCALE;


public class PlayState extends GameState {
    private AVImgButton exitS;
    private AVImgButton rightB;
    private AVImgButton leftB;
    private AVImgButton upB;
    private AVImgButton downB;
    private AVImgButton vol;
    public static int scoreS;
    private BitmapFont font;

    private TiledMap tileMap;
    public static int level = 0;
    public static float tileMapWidth;
    public static float tileMapHeight;
    public static float tileSize;
    private TiledMapRenderer tmRenderer;
    private GameObject bunny;
    private Player snake;
    private boolean bunnyLost;
    private boolean volB;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        vol = new AVImgButton("music", GameConfig.GWIDTH - GameConfig.GWIDTH / 15 * 1.2f * 2, GameConfig.GHEIGHT - GameConfig.GWIDTH / 15 * 1.2f, GameConfig.GWIDTH / 15, GameConfig.GWIDTH / 15, cam);
        exitS = new AVImgButton("exit_btn", GameConfig.GWIDTH - GameConfig.GWIDTH / 15 * 1.2f, GameConfig.GHEIGHT - GameConfig.GWIDTH / 15 * 1.2f, GameConfig.GWIDTH / 15, GameConfig.GWIDTH / 15, cam);
        leftB = new AVImgButton("left", GameConfig.GWIDTH / 30, GameConfig.GWIDTH / 30 * 2 + GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, cam);
        rightB = new AVImgButton("right", GameConfig.GWIDTH / 30 * 4 + GameConfig.GWIDTH / 30, GameConfig.GWIDTH / 30 * 2 + GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, cam);
        upB = new AVImgButton("up", GameConfig.GWIDTH / 30 * 2 + GameConfig.GWIDTH / 30, GameConfig.GWIDTH / 30 * 4 + GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, cam);
        downB = new AVImgButton("down", GameConfig.GWIDTH / 30 * 2 + GameConfig.GWIDTH / 30, GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, 2 * GameConfig.GWIDTH / 30, cam);
//        score = new AVTextButton(10, GameConfig.GHEIGHT-SCALE-10, SCALE, SCALE, cam);
        scoreS = 0;


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AB.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;

        // font.setColor(Color.BLACK);
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);

        tileMap = new TiledMap();
        loadMap();
        bunnyLost = false;
        snake = new Player(MyGdxGame.content.getAtlas("snakeset"));
        // snake.setRotation(90); // quay lên
        bunny = new GameObject(MyGdxGame.content.getAtlas("snakeset").findRegion("bunny"), foodRandX(), foodRandY());
        System.out.println(bunny.getX() + "  " + bunny.getY());
    }

    @Override
    public void handleInput() {
        if (exitS.isClicked() == true) {
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.MENU);
        }
        if (vol.isClicked() == true) {
            MyGdxGame.content.getSound("direct").play();
            if (volB == false) {
                MyGdxGame.content.getMusic("bbsong").setVolume(0);
                volB = true;
            } else {
                MyGdxGame.content.getMusic("bbsong").setVolume(0.5f);
                volB = false;
            }
        }
        if (leftB.isClicked() == true && snake.getDir() != Direction.RIGHT) {
            MyGdxGame.content.getSound("direct").play();
            // snake.setRotation(270);
            snake.setDir(Direction.LEFT);
            // gsm.pushState(GameStateManager.MENU);
        }
        if (rightB.isClicked() == true && snake.getDir() != Direction.LEFT) {
            MyGdxGame.content.getSound("direct").play();
            snake.setDir(Direction.RIGHT);
            //snake.setRotation(90);
            //gsm.pushState(GameStateManager.MENU);
        }
        if (upB.isClicked() == true && snake.getDir() != Direction.DOWN) {
            MyGdxGame.content.getSound("direct").play();
            snake.setDir(Direction.UP);
            // snake.setRotation(180);
            // gsm.pushState(GameStateManager.MENU);
        }
        if (downB.isClicked() == true && snake.getDir() != Direction.UP) {
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
        vol.update(dt);
        randomBunny();
        while (checkColMap(0, bunny) == true) {
            bunnyLost = true;
            randomBunny();
        }
        snake.update(dt);
        if (checkColMap(0, snake.getHead()) == true) {
            snake.setDie(true);
        }


        if (snake.checkCol(bunny) == true) {
            MyGdxGame.content.getSound("levelselect").play();
            scoreS++;
            bunnyLost = true;
            snake.grow();

            System.out.println(scoreS);
        }
        if (snake.isDie() == true || snake.die() == true) {
            MyGdxGame.content.getSound("gameover").play();
            FileHandle f = Gdx.files.local("data.txt");
            String s = f.readString();
            String[] scores = s.split("\n");
            if (scores != null && scoreS > Integer.parseInt(scores[0])) {
                //String[] tmp=scores;
                f.writeString(String.valueOf(scoreS) + "\n", false);
            }
//            if(scores.length >5  ){
//                for(int i=0;i<scores.length;i++){
//                    if(scoreS>Integer.parseInt(scores[i])){
//                        tmp[i]= String.valueOf(scoreS);
//                        for(int j=i+1;j<tmp.length;j++){
//                            tmp[j]= scores[j-1];
//                            //break;
//                        }
//
//
//                    }
//                }
//                f.writeString("",false);
//                for (int i=0;i<tmp.length;i++){
//                    f.writeString(tmp[i]+"\n",true);
//                }
//            }

            gsm.pushState(GameStateManager.OVER);
        }
    }

    @Override
    public void render() {

        sb.begin();

        tmRenderer.setView(cam);
        tmRenderer.render();


        snake.render(sb);
        exitS.render(sb);
        vol.render(sb);

        rightB.render(sb);
        leftB.render(sb);
        upB.render(sb);
        downB.render(sb);
        if (bunnyLost == false) {
            bunny.draw(sb);
        }
//        score.render(sb, "score");
        font.draw(sb, "Score: " + String.valueOf(scoreS), 2 * SCALE, GameConfig.GHEIGHT - 2 * SCALE);
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

    public void loadMap() {
        // load tile map and map renderer
        try {
            tileMap = new TmxMapLoader().load("maps/levell" + level + ".tmx");
        } catch (Exception e) {
            System.out.println("Cannot find file: maps/level" + level + ".tmx");
            // Gdx.app.exit();
        }
        MapProperties prop = tileMap.getProperties();
        tileSize = (int) tileMap.getProperties().get("tilewidth");
        //tileMapWidth = (int) prop.get("width", Integer.class)*tileSize;
        tileMapWidth = tileMap.getProperties().get("width", Integer.class) * tileSize;
        //tileMapWidth = 18*tileSize;
        tileMapHeight = (int) prop.get("height", Integer.class) * tileSize;
        //tileMapHeight = 10*tileSize;


        tmRenderer = new OrthogonalTiledMapRenderer(tileMap);


    }

    private float foodRandX() {
        return MathUtils.random(1, 40) * tileSize;
    }

    private float foodRandY() {
        return MathUtils.random(1, 22) * tileSize;
    }

    private void randomBunny() {
        if (bunnyLost == true) {
            bunny = new GameObject(MyGdxGame.content.getAtlas("snakeset").findRegion("bunny"), foodRandX(), foodRandY());
            bunnyLost = false;
        }
    }

    public boolean checkColMap(int objectLayerId, GameObject gameObject) {
//        int objectLayerId = 1;
        TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer) tileMap.getLayers().get(objectLayerId);
        for (int i = 0; i < tileMap.getProperties().get("width", Integer.class); i++) {
            for (int j = 0; j < tileMap.getProperties().get("height", Integer.class); j++) {
                TiledMapTileLayer.Cell cell = collisionObjectLayer.getCell(i, j);
                if (cell != null) {
                    float cellX = i * tileSize;
                    float cellY = j * tileSize;
                    if (((gameObject.getX() < cellX + tileSize) && (gameObject.getX() + gameObject.getWidth() > cellX) && (gameObject.getY() < cellY + tileSize) && (gameObject.getY() + gameObject.getHeight() > cellY)) == true) {
                        System.out.println("da cham map");
                        return true;

                    }

                }
            }
        }


// there are several other types, Rectangle is probably the most common one
//        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
//
//            Rectangle rectangle = rectangleObject.getRectangle();
//            if (Intersector.overlaps(rectangle, new Rectangle(x+SCALE,y,SCALE,SCALE))) {
//                // collision happened
//                System.out.println("lozzzzz");
//            }
//        }
        return false;
    }


}
