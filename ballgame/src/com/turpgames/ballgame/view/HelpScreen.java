package com.turpgames.ballgame.view;

import com.turpgames.ballgame.controller.AutoPlayGameController;
import com.turpgames.ballgame.utils.R;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;

public class HelpScreen extends Screen implements IScreenView {
	private AutoPlayGameController controller;

	public void init() {
		super.init();
		controller = new AutoPlayGameController(this);
	}

	@Override
	protected void onAfterActivate() {
		controller.activate();	
	}

	@Override
	protected boolean onBeforeDeactivate() {
		controller.deactivate();
		return super.onBeforeDeactivate();
	}

	@Override
	public void update() {
		super.update();
		controller.update();
	}

	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.screens.game, true);
		return true;
	}
}
