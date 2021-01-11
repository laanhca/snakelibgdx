package com.av.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class Content {
    private HashMap<String, TextureAtlas> atlas;
    private HashMap<String, Music> music;
    private HashMap<String, Sound> sounds;

    public Content() {
        atlas = new HashMap<String, TextureAtlas>();
        music = new HashMap<String, Music>();
        sounds = new HashMap<String, Sound>();
    }

    /***********/
    /* TextureAtlas */
    /***********/

    public void loadTextureAtlas(String path) {
        int slashIndex = path.lastIndexOf('/');
        String key;
        if(slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf('.'));
        }
        else {
            key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
        }
        loadTextureAtlas(path, key);
    }
    public void loadTextureAtlas(String path, String key) {
        TextureAtlas tex = new TextureAtlas(Gdx.files.internal(path));
        atlas.put(key, tex);
    }
    public TextureAtlas getAtlas(String key) {
        return atlas.get(key);
    }
    public void removeTextureAtlas(String key) {
        TextureAtlas tex = atlas.get(key);
        if(tex != null) {
            atlas.remove(key);
            tex.dispose();
        }
    }

    /*********/
    /* Music */
    /*********/

    public void loadMusic(String path) {
        int slashIndex = path.lastIndexOf('/');
        String key;
        if(slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf('.'));
        }
        else {
            key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
        }
        loadMusic(path, key);
    }
    public void loadMusic(String path, String key) {
        Music m = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.put(key, m);
    }
    public Music getMusic(String key) {
        return music.get(key);
    }
    public void removeMusic(String key) {
        Music m = music.get(key);
        if(m != null) {
            music.remove(key);
            m.dispose();
        }
    }

    /*******/
    /* SFX */
    /*******/

    public void loadSound(String path) {
        int slashIndex = path.lastIndexOf('/');
        String key;
        if(slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf('.'));
        }
        else {
            key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
        }
        loadSound(path, key);
    }
    public void loadSound(String path, String key) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(key, sound);
    }
    public Sound getSound(String key) {
        return sounds.get(key);
    }
    public void removeSound(String key) {
        Sound sound = sounds.get(key);
        if(sound != null) {
            sounds.remove(key);
            sound.dispose();
        }
    }

    public void removeAll() {

        for(Object o : atlas.values()) {
            Texture tex = (Texture) o;
            tex.dispose();
        }
        atlas.clear();
        for(Object o : music.values()) {
            Music music = (Music) o;
            music.dispose();
        }
        music.clear();
        for(Object o : sounds.values()) {
            Sound sound = (Sound) o;
            sound.dispose();
        }
        sounds.clear();
    }
}
