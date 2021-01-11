package com.av.game.entities;

import com.av.game.main.Direction;

import com.av.game.main.GameConfig;
import com.av.game.main.MyGdxGame;
import com.av.game.states.PlayState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.LinkedList;
import java.util.Stack;

import static com.av.game.main.MyGdxGame.SCALE;

public class Player {
    private static final int INITIAL_BODY_COUNT = 2;
    private LinkedList<BodySnake> snakeBody;
    private Stack<GameObject> lives;
    private TextureAtlas atlas;
    private Direction dir;
    private BodySnake head;
    private BodySnake tail;
    private boolean die;
    float timeState;
    public Player(TextureAtlas atlas, float x, float y) {
        die = false;
        dir = Direction.RIGHT;
        this.atlas = atlas;
        lives = new Stack<GameObject>();
        snakeBody = new LinkedList<BodySnake>();
        //restoreHealth();
        init();
    }
    public void init(){
        snakeBody.clear();
        for (int i = INITIAL_BODY_COUNT; i >= 0; i--) {
            BodySnake body = new BodySnake(atlas.findRegion(getBodyType(i)), SCALE * i, 0,Direction.RIGHT);
            snakeBody.add(body);
        }
        head = snakeBody.getFirst().originCenter();
        tail = snakeBody.getLast().originCenter();
    }
    public void update(float dt){
        //headMovie();
        wrapScreen();
        //setShow();
        timeState += dt;

        if (timeState >= .2f) {
            moveBody();
            timeState = 0;
        }
        //die();

//        if(dir==Direction.RIGHT){
//            setX(getX()+2f);
//
//        }
//        if(dir==Direction.LEFT){
//            setX(getX()-2f);
//
//        }
//        if(dir==Direction.UP){
//            setY(getY()+2f);
//            setRotation(90);
//        }
//        if(dir==Direction.DOWN){
//            setY(getY()-2f);
//            setRotation(270);
//        }

    }
    private String getBodyType(int index) {
        if (index == INITIAL_BODY_COUNT) return "head";
        if (index == 0) return "tail";
        else return "horizontal";
    }
    public void render(SpriteBatch batch) {
        for (BodySnake body : snakeBody) {
            body.draw(batch);
        }
//        for (GameObject life : lives) {
//            life.draw(batch);
//        }
    }
    public void moveBody() {
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            //BodySnake nextBody = snakeBody.get(i - 1);
           // BodySnake body = snakeBody.get(i);
           // body.setPosition(nextBody.getX(), nextBody.getY());
            snakeBody.get(i).setPosition(snakeBody.get(i - 1).getX(),snakeBody.get(i - 1).getY());
            snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
//            if(snakeBody.get(i).getDirection()!=snakeBody.get(i-1).getDirection()){
//                snakeBody.get(i).setSprite(atlas.findRegion("rightup"));
//            }
        }
        head.setDirection(dir);
        headMovie();
       // wrapScreen();
    }
    public void setShow() {
        for (int i = snakeBody.size()-1 ; i > 0; i--) {
            //BodySnake nextBody = snakeBody.get(i - 1);
             //BodySnake body = snakeBody.get(i);
            // body.setPosition(nextBody.getX(), nextBody.getY());

            if(snakeBody.get(i).getDirection()!=snakeBody.get(i-1).getDirection()){
                if(snakeBody.get(i).getDirection()==Direction.RIGHT&& snakeBody.get(i-1).getDirection()==Direction.UP){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("rightup"));
                    //snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
                if(snakeBody.get(i).getDirection()==Direction.RIGHT&& snakeBody.get(i-1).getDirection()==Direction.DOWN){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("rightdown"));
                    //snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
                if(snakeBody.get(i).getDirection()==Direction.LEFT&& snakeBody.get(i-1).getDirection()==Direction.UP){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("leftup"));
                   // snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
                if(snakeBody.get(i).getDirection()==Direction.LEFT&& snakeBody.get(i-1).getDirection()==Direction.DOWN){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("leftdown"));
                   // snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
            } else {
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("horizontal"));
            }
        }

        // wrapScreen();
    }
    public void wrapScreen(){
//        if (head.getX()> 832){head.setX(0);}
        if (head.getX()> PlayState.tileMapWidth-SCALE){head.setX(0);}
        if (head.getX()< 0){head.setX(PlayState.tileMapWidth-SCALE);}
//        if (head.getX()< 0){head.setX(832);}
//        if (head.getY()> 640){head.setY(0);}
        if (head.getY()> PlayState.tileMapHeight-SCALE){head.setY(0);}
        if (head.getY()< 0){head.setY(PlayState.tileMapHeight-SCALE);}
       // if (head.getY()< 0){head.setY(640);}
    }
    public void setDir(Direction dir){
        this.dir= dir;
    }
    public Direction getDir(){
       return dir;
    }
    public boolean checkCol(GameObject bunny){
        return head.isCollide(bunny);
    }
    public void grow() {
        snakeBody.getLast().sprite.setRegion(atlas.findRegion("horizontal"));
        BodySnake body = new BodySnake(atlas.findRegion("tail"), tail.getX(), tail.getY(),tail.getDirection());
        snakeBody.add(body);
        tail = snakeBody.getLast().originCenter();
        System.out.println(snakeBody.size());
    }
    public boolean die(){
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            if(head.isCollide(snakeBody.get(i))==true){
                return die=true;
            }
          //  snakeBody.get(i).setPosition(snakeBody.get(i - 1).getX(),snakeBody.get(i - 1).getY());

            }
        return die=false;
    }
    public void headMovie(){
        if (dir == Direction.UP) {
            head.setPosition(head.getX(), head.getY() + SCALE);
        } else if (dir == Direction.DOWN) {
            head.setPosition(head.getX(), head.getY() - SCALE);
        } else if (dir == Direction.LEFT) {
            head.setPosition(head.getX() - SCALE, head.getY());
        } else if (dir == Direction.RIGHT) {
            head.setPosition(head.getX() + SCALE, head.getY());
        }
    }

}
