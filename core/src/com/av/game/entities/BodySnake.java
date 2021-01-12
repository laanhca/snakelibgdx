package com.av.game.entities;

import com.av.game.main.Direction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.av.game.main.MyGdxGame.SCALE;

public class BodySnake extends GameObject{
    private Direction dir;
    private boolean isBreak;
    public BodySnake(TextureRegion textureRegion, float x, float y,Direction dir) {
        super(textureRegion, x, y);
        this.dir = dir;
        isBreak=false;
        sprite.setOriginCenter();
    }
    public void setSprite(TextureRegion textureRegion){
        this.textureRegion= textureRegion;
    }
//    public void setDir(Direction dir) {
//        this.dir = dir;
//    }
//
//    public void setSprite(TextureRegion textureRegion) {
//        if(dir=)
//    }
    public void setDirection(Direction dir) {
        this.dir= dir;
        if (dir == Direction.UP) {
            sprite.setRotation(90);
            //setPosition(x, y + SCALE);
        } else if (dir == Direction.DOWN) {
            sprite.setRotation(-90);
           // setPosition(x, y - SCALE);
        } else if (dir == Direction.LEFT) {
            sprite.setRotation(180);
            //setPosition(x - SCALE, y);
        } else if (dir == Direction.RIGHT) {
            sprite.setRotation(0);
        // setPosition(x + SCALE, y);
        }
    }
//    public BodySnake originCenter() {
//        sprite.setOriginCenter();
//        return this;
//    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setBreak(boolean aBreak) {
        isBreak = aBreak;
    }
    public Direction getDirection(){
        return dir;
    }
//    public Direction setDirectionC(Direction dir){
//        return this.dir= dir;
//    }
}
