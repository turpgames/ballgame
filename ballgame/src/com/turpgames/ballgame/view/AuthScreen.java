package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class AuthScreen extends Screen {

	private boolean isFirstActivate;

	private TextButton facebookButton;
	private TextButton anonymousButton;
	private TextButton offlineButton;
	
	@Override
	public void init() {
		super.init();

		isFirstActivate = true;
		
		facebookButton = initButton("Login With Facebook", 450, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				loginWithFacebook();
			}
		});
		
		anonymousButton = initButton("Play As Guest", 350, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				playAsGuestAnonymous();
			}
		});
		
		offlineButton = initButton("Play Offline", 250, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				playOffline();
			}
		});
		
		registerDrawable(new BallGameLogo(), Game.LAYER_GAME);
	}
	
	private void loginWithFacebook() {
		
	}
	
	private void playAsGuestAnonymous() {
		
	}
	
	private void playOffline() {
		
	}
	
	private void loginGuest() {
		
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

			if (TurpClient.canLoginWithFacebook())
				loginWithFacebook();
			else if (TurpClient.canLoginGuest())
				loginGuest();
			
			if (Settings.getInteger("game-installed", 0) == 0) {
				TurpClient.sendStat(StatActions.GameInstalled, Game.getPhysicalScreenSize().toString());
				Settings.putInteger("game-installed", 1);
			}
			
			TurpClient.sendStat(StatActions.StartGame);
		}
		
		
		facebookButton.activate();
		anonymousButton.activate();
		offlineButton.activate();
	}
	
	@Override
	protected boolean onBeforeDeactivate() {
		facebookButton.deactivate();
		anonymousButton.deactivate();
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
