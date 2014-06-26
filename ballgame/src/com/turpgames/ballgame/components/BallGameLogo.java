package com.turpgames.ballgame.components;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.Text;

public class BallGameLogo implements IDrawable {
	private final Text title;
	
	public BallGameLogo() {
		title = new Text();
		title.setFontScale(1.25f);
		title.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		title.setPadY(75f);
		title.setText("Ball Game");
	}
	
	@Override
	public void draw() {
		title.draw();
	}
}
