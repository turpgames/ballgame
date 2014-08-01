package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.utils.BallGameAuth;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class AuthScreen extends Screen {

	private TextButton guestButton;
	private TextButton offlineButton;
	
	private boolean isFirstActivate;
	
	@Override
	public void init() {
		super.init();
		
		guestButton = initButton("Play As Guest", 250, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				playAsGuest();
			}
		});
		
		offlineButton = initButton("Play Offline", 400, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.game, false);
			}
		});
		
		registerDrawable(new BallGameLogo(), Game.LAYER_GAME);
		
		if (Settings.getInteger("game-installed", 0) == 0) {
			TurpClient.sendStat(StatActions.GameInstalled, Game.getPhysicalScreenSize().toString());
			Settings.putInteger("game-installed", 1);
		}
		
		TurpClient.sendStat(StatActions.StartGame);
		
		isFirstActivate = true;
	}
	
	private void initAuth() {
		BallGameAuth.init();
	}
	
	private void playAsGuest() {
		BallGameAuth.doGuestLogin();
	}
	
	private TextButton initButton(String text, float y, IButtonListener listener) {
		TextButton btn = new TextButton(Color.white(), R.colors.yellow);
		
		btn.setText(text);
		btn.getLocation().set((Game.getVirtualWidth() - btn.getWidth()) / 2, y);
		btn.setListener(listener);
		
		registerDrawable(btn, Game.LAYER_GAME);
		
		return btn;
	}
	
	@Override
	protected void onAfterActivate() {
		if (isFirstActivate) {
			isFirstActivate = false;
			initAuth();
		}
		guestButton.activate();
		offlineButton.activate();
	}
	
	@Override
	protected boolean onBeforeDeactivate() {
		guestButton.deactivate();
		offlineButton.deactivate();
		return super.onBeforeDeactivate();
	}
	
	@Override
	protected boolean onBack() {
		TurpClient.sendStat(StatActions.ExitGame);
		Game.exit();
		return true;
	}
}
