package com.turpgames.ballgame.utils;

import com.turpgames.framework.v0.client.ConnectionManager;
import com.turpgames.framework.v0.util.Debug;
import com.turpgames.framework.v0.util.Game;

public class BallGameAds {
	private final static long interval = 2 * 60 * 1000;
	private static long lastShown;
	
	public static void showAd() {
		Debug.println("BallGameAds.showAd");
		if (!ConnectionManager.hasConnection()) {
			Debug.println("No connection!");
		} else {
			long now = System.currentTimeMillis();
			if (now - lastShown > interval) {
				lastShown = now;
				Debug.println("calling Game.showPopUpAd");
				Game.showPopUpAd();
				return;
			}
		}
	}

}