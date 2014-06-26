package com.turpgames.ballgame.view;

import com.turpgames.ballgame.controller.GameController;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Game;

public class GameScreen extends Screen implements IScreenView {

	private GameController controller;
	private boolean isFirstActivate;

	public void init() {
		super.init();
		isFirstActivate = true;
		controller = new GameController(this);
	}

	protected void onAfterActivate() {
		if (isFirstActivate) {
			isFirstActivate = false;
			TurpClient.init();
			
			if (Settings.getInteger("game-installed", 0) == 0) {
				TurpClient.sendStat(StatActions.GameInstalled);
				Settings.putInteger("game-installed", 1);
			}
			
			TurpClient.sendStat(StatActions.StartGame);
		}
		controller.activate();
		TurpClient.sendStat(StatActions.EnterGameScreen);
	}



	@Override
	protected boolean onBack() {
		TurpClient.sendStat(StatActions.ExitGame);
		Game.exit();
		return true;
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
