package com.turpgames.ballgame.utils;

import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.social.ICallback;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TurpToast;

public class BallGameAuth {
	private final static ICallback loginCallback = new ICallback() {
		@Override
		public void onSuccess() {
			Game.unblockUI();
			onLogin();
		}

		@Override
		public void onFail(Throwable t) {
			Game.unblockUI();
			TurpToast.showError("Login Failed!");
		}
	};

	private static boolean changeGuestName() {
		return Settings.getBoolean("change-guest-name", true);
	}

	private static void changeGuestName(boolean changed) {
		Settings.putBoolean("change-guest-name", changed);
	}

	private static void onLogin() {
		changeGuestName(false);
		switchToGameScreen();
	}

	private static void switchToGameScreen() {
		Game.enqueueRunnable(new Runnable() {
			@Override
			public void run() {
				ScreenManager.instance.switchTo(R.screens.game, false);
			}
		});
	}

	public static void init() {
		if (TurpClient.canLoginGuest())
			doGuestLogin();
	}

	public static void doGuestLogin() {
		Game.blockUI("Registering guest...");
		if (changeGuestName())
			TurpClient.registerGuestWithNewName(loginCallback);
		else
			TurpClient.loginGuest(loginCallback);
	}
}
