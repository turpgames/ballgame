package com.turpgames.ballgame.components;

import com.turpgames.framework.v0.component.ImageButton;
import com.turpgames.framework.v0.util.Game;

public class HelpButton extends ImageButton {
	public HelpButton() {
		setWidth(24f);
		setHeight(24f);
		getLocation().set(Game.scale(10), Game.scale(10));
		setTexture("ball_blue");
		deactivate();
	}
}
