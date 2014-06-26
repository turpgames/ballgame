package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.FormScreen;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Game;

public class MenuScreen extends FormScreen {
	private boolean isFirstActivate;

	@Override
	public void init() {
		super.init();
		isFirstActivate = true;
		
		setForm("mainMenu", false);
		
		registerDrawable(new BallGameLogo(), Game.LAYER_GAME);
	}

	protected void onAfterActivate() {
		super.onAfterActivate();
		if (isFirstActivate) {
			isFirstActivate = false;
			TurpClient.init();
			
			if (Settings.getInteger("game-installed", 0) == 0) {
				TurpClient.sendStat(StatActions.GameInstalled);
				Settings.putInteger("game-installed", 1);
			}
			
			TurpClient.sendStat(StatActions.StartGame);
		}
		TurpClient.sendStat(StatActions.EnterMenuScreen);
	}

	protected boolean onBack() {
		TurpClient.sendStat(StatActions.ExitGame);
		Game.exit();
		return false;
	}
}
