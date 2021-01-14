package com.av.game.handlers;

import com.av.game.main.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class AVTextButton {
    private float x;
    private float y;
    private float width;
    private float height;
    private int sizeS;
    private TextureRegion reg;

    Vector3 vec;
    private OrthographicCamera cam;

    private boolean clicked;

    private TextureRegion[] textTextures;
//    private TextureRegion[] textRG;

    public AVTextButton(float x, float y, int width, int height, OrthographicCamera cam) {
        this.width= width;
        this.height= height;
        this.x = x;
        this.y = y;
        this.cam = cam;

        vec = new Vector3();
       reg = MyGdxGame.content.getAtlas("btns").findRegion("Text");
       loadText();
    }

    public boolean isClicked() { return clicked; }
   // public void setText(String s) { text = s; }

    public void update(float dt) {

        vec.set(AVInput.x, AVInput.y, 0);
        cam.unproject(vec);
        if(AVInput.isPressed() &&
                vec.x > x+width  && vec.x < x + width*(sizeS+1) &&
                vec.y > y   && vec.y < y+ height ) {
            clicked = true;

        }
        else {
            clicked = false;
        }

    }

    public void render(SpriteBatch sb,String s) {



        for(int i=0;i<drawString(s).length;i++){
                sb.draw(drawString(s)[i],width*(i+1) +x,y,width,height);

    }
    }

    private TextureRegion[] drawString(String s) {
        sizeS= s.length();
        TextureRegion[]  textRG= new TextureRegion[sizeS];
        for (int i = 0; i < sizeS; i++) {
            if (s.charAt(i) == 32) {
                textRG[i] = textTextures[27];
            }else {int c= s.charAt(i)-97;
                textRG[i]=textTextures[c];}

        }
        return textRG;
    }
    public void loadText(){
        int a=0;
        int cols= reg.getRegionWidth()/8;
        int rows = reg.getRegionHeight()/10;
        textTextures = new TextureRegion[rows * cols];
        TextureRegion[][] tmp = new TextureRegion[rows][cols];
        for(int i=0;i<rows;i++){
            for (int j =0;j<cols;j++){

                tmp[i][j] = new TextureRegion(reg,j*8,i*10, 8,10);
                textTextures[a]=tmp[i][j];
                a++;
            }

        }
    }
}
