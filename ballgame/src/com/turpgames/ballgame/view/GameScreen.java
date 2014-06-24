package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.controller.GameController;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.util.Game;

public class GameScreen extends Screen implements IScreenView {

    private GameController controller;
    private boolean isFirstActivate;

    public GameScreen() {
        isFirstActivate = true;
    }

    public void init() {
        super.init();
        controller = new GameController(this);
        registerDrawable(new BallGameLogo(), 100);
    }

    protected void onAfterActivate() {
        if (isFirstActivate) {
            isFirstActivate = false;
            TurpClient.init();
        }
        controller.activate();
    }

    protected boolean onBack() {
        Game.exit();
        return false;
    }

    protected boolean onBeforeDeactivate() {
        controller.deactivate();
        return super.onBeforeDeactivate();
    }

    public void update() {
        super.update();
        controller.update();
    }
}
