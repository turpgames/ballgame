package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.controller.GameController;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;

public class GameScreen extends Screen implements IScreenView {

	private GameController controller;

	public void init() {
		super.init();
		controller = new GameController(this);
		registerDrawable(new BallGameLogo(), 100);
	}

	protected void onAfterActivate() {
		controller.activate();
		TurpClient.sendStat(StatActions.EnterGameScreen);
	}

	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.screens.menu, true);
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
