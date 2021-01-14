package com.av.game.entities;

import com.av.game.main.Direction;

import com.av.game.main.GameConfig;
import com.av.game.states.PlayState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


import java.util.LinkedList;
import java.util.Stack;

import static com.av.game.main.MyGdxGame.SCALE;

public class Player {
    private static final int INITIAL_BODY_COUNT = 2;
    private LinkedList<BodySnake> snakeBody;
    private TextureAtlas atlas;
    private Direction dir;
    private BodySnake head;
    private BodySnake tail;
    private boolean die;
      float timeState;
    public static float timeStateDK=0.2f;
    public Player(TextureAtlas atlas) {
        die = false;
        dir = Direction.RIGHT;
        this.atlas = atlas;
        snakeBody = new LinkedList<BodySnake>();
        //restoreHealth();
        init();
    }
    public void init(){
        snakeBody.clear();
        for (int i = INITIAL_BODY_COUNT; i >= 0; i--) {
            BodySnake body = new BodySnake(atlas.findRegion(getBodyType(i)), PlayState.tileSize * i+SCALE*3, PlayState.tileSize,Direction.RIGHT);
            snakeBody.add(body);
        }
        //timeStateDK=0.2f;
        head = snakeBody.getFirst();
        tail = snakeBody.getLast();
    }
    public void update(float dt){

        //headMovie();
        wrapScreen();

        timeState += dt;

        if (timeState >= timeStateDK) {
            //setAllDir();

           moveBody();
            //setShow();
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
        if (index == 0) return "body";
        else return "body";
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


        headMovie();
       // wrapScreen();
    }
    public void setAllDir() {
        for (int i = 0; i < snakeBody.size() - 1; i++) {

            snakeBody.get(i+1).setDirection(snakeBody.get(i).getDirection());

        }

    }
    public void setShow() {
        for (int i = 0; i < snakeBody.size() - 1; i++) {
            //BodySnake nextBody = snakeBody.get(i - 1);
             //BodySnake body = snakeBody.get(i);
            // body.setPosition(nextBody.getX(), nextBody.getY());

            if(snakeBody.get(i+1).getDirection()!=snakeBody.get(i).getDirection()){
                //Gdx.app.log("aaa","aaaaaaaaaaaaaa");
                if(snakeBody.get(i+1).getDirection()==Direction.RIGHT&& snakeBody.get(i).getDirection()==Direction.UP){
                    snakeBody.get(i+1).sprite.setRegion(atlas.findRegion("rightup"));
                    snakeBody.get(i+1).setDirection(snakeBody.get(i).getDirection());
                }
                if(snakeBody.get(i).getDirection()==Direction.RIGHT&& snakeBody.get(i+1).getDirection()==Direction.DOWN){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("rightdown"));
                    //snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
                if(snakeBody.get(i).getDirection()==Direction.LEFT&& snakeBody.get(i+1).getDirection()==Direction.UP){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("leftup"));
                   // snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
                if(snakeBody.get(i).getDirection()==Direction.LEFT&& snakeBody.get(i+1).getDirection()==Direction.DOWN){
                    snakeBody.get(i).sprite.setRegion(atlas.findRegion("leftdown"));
                   // snakeBody.get(i).setDirection(snakeBody.get(i-1).getDirection());
                }
            } else {

                   // snakeBody.get(i).sprite.setRegion(atlas.findRegion("horizontal"));
                    //snakeBody.get(i).sprite.setRegion(atlas.findRegion(getBodyType(i)));
            }
        }

        // wrapScreen();
    }
    public void wrapScreen(){
//        if (head.getX()> 832){head.setX(0);}
        if (head.getX()> GameConfig.GWIDTH -SCALE){head.setX(0);}
        if (head.getX()< 0){head.setX(GameConfig.GWIDTH-SCALE);}
//        if (head.getX()< 0){head.setX(832);}
//        if (head.getY()> 640){head.setY(0);}
        if (head.getY()> GameConfig.GHEIGHT-SCALE){head.setY(0);}
        if (head.getY()< 0){head.setY(GameConfig.GHEIGHT-SCALE);}
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
       // snakeBody.getLast().sprite.setRegion(atlas.findRegion("body"));
        BodySnake body = new BodySnake(atlas.findRegion("body"), tail.getX(), tail.getY(),tail.getDirection());
        snakeBody.add(body);
        tail = snakeBody.getLast();
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
        head.setDirection(dir);
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

    public static void setTimeStateDK(float timeStateDK) {
        Player.timeStateDK = timeStateDK;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public BodySnake getHead() {
        return head;
    }
}
