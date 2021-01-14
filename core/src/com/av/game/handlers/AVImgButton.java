package com.av.game.handlers;

import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class AVImgButton {
    private float x;
    private float y;
    private float width;
    private float height;
    private TextureRegion reg;

    private String s;

    Vector3 vec;
    private OrthographicCamera cam;

    private boolean clicked;


    public AVImgButton(String s,float x, float y, int width, int height, OrthographicCamera cam) {
        this.width= width;
        this.height= height;
        this.x = x;
        this.y = y;
        this.cam = cam;
        this.s = s;
        vec = new Vector3();
        reg = MyGdxGame.content.getAtlas("btns").findRegion(s);

    }

    public boolean isClicked() { return clicked; }
    public void update(float dt) {

        vec.set(AVInput.x, AVInput.y, 0);
        cam.unproject(vec);
        if(AVInput.isPressed() &&
                vec.x > x  && vec.x < x + width &&
                vec.y > y   && vec.y < y+height ) {
            clicked = true;

        }
        else {
            clicked = false;
        }

    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void render(SpriteBatch sb) {
        sb.draw(reg,x,y,width,height);
    }

}
