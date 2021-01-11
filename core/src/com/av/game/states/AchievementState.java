package com.av.game.states;

import com.av.game.handlers.AVImgButton;
import com.av.game.main.GameStateManager;
import com.av.game.main.MyGdxGame;

public class AchievementState extends GameState{
    AVImgButton exitS;
    public AchievementState(GameStateManager gsm) {
        super(gsm);
        exitS = new AVImgButton("Close", 1050, 550, 50, 50, cam);
    }

    @Override
    public void handleInput() {
        if(exitS.isClicked()==true){
            MyGdxGame.content.getSound("direct").play();
            gsm.pushState(GameStateManager.MENU);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        exitS.update(dt);

    }

    @Override
    public void render() {
        sb.begin();
        exitS.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
