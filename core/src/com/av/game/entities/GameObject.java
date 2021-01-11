package com.av.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.av.game.main.MyGdxGame.SCALE;

public class GameObject {
    protected TextureRegion textureRegion;
    protected Sprite sprite;
    protected float x;
    protected float y;

    public GameObject(TextureRegion textureRegion, float x, float y) {
        this.textureRegion = textureRegion;
        sprite= new Sprite(textureRegion);
        setPosition(x, y);
        setSize(SCALE,SCALE);
    }
    public GameObject(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        //setSize(64, 64);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setPosition(x,y);
    }
    public void draw(SpriteBatch batch) {
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }
    public void setRotation(float degree) {
        sprite.setRotation(degree);
    }

    public void setSize(float width, float height) {
        sprite.setSize(width, height);
    }

    public boolean isCollide(GameObject object) {
        return x < object.x + object.getWidth() &&
                x + getWidth() > object.x &&
                y < object.y + object.getHeight() &&
                y + getHeight() > object.y;
    }


    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public float getY() {
        return y;
    }
    public void setY(float y){this.y = y; }
    public float getX() {
        return x;
    }
    public void setX(float x){this.x = x; }
    public void setRotation(int rotation) {
        sprite.setRotation(rotation);
    }
}
