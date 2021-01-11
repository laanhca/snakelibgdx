package com.av.game.main;

import com.av.game.handlers.AVInput;
import com.av.game.handlers.AVInputProcessor;
import com.av.game.handlers.Content;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch sb;
	GameStateManager gsm;
	OrthographicCamera cam;
	Viewport viewport;
	public static Content content;
	public static final float SCALE= 64;
	@Override
	public void create () {
		Gdx.input.setInputProcessor(new AVInputProcessor());

		content= new Content();
		content.loadTextureAtlas("buttons/buttons.atlas");
		content.loadTextureAtlas("buttons/snakeset.atlas");
		content.loadSound("sfx/collect.wav");
		content.loadSound("sfx/gameover.wav");
		content.loadSound("sfx/levelselect.wav");
		content.loadSound("sfx/direct.wav");


		content.loadMusic("music/bbsong.ogg");
		content.getMusic("bbsong").setLooping(true);
		content.getMusic("bbsong").setVolume(0.5f);
		content.getMusic("bbsong").play();


		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		gsm = new GameStateManager(this);
		viewport = new ExtendViewport(832, 640, cam);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		sb.setProjectionMatrix(cam.combined);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		AVInput.update();
	}
	
	@Override
	public void dispose () {
	}
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
}
