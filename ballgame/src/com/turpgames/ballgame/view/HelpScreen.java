package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.Toolbar;
import com.turpgames.ballgame.controller.AutoPlayGameController;
import com.turpgames.ballgame.utils.R;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Game;

public class HelpScreen extends Screen implements IScreenView {
	private AutoPlayGameController controller;

	public void init() {
		super.init();
		controller = new AutoPlayGameController(this);
		registerDrawable(Toolbar.getInstance(), Game.LAYER_INFO);
	}

	@Override
	protected void onAfterActivate() {
		controller.activate();	
		Toolbar.getInstance().enable();
		Toolbar.getInstance().setListener(new com.turpgames.framework.v0.component.Toolbar.IToolbarListener() {
			@Override
			public void onToolbarBack() {
				onBack();
			}
		});
	}

	@Override
	protected boolean onBeforeDeactivate() {
		controller.deactivate();
		Toolbar.getInstance().disable();
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
