package com.turpgames.ballgame.utils;

import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Game;

public class Sounds
{
	static
	{
		Settings.putBoolean("sound", true);
	}

	public static final ISound gameover = Game.getResourceManager().getSound("gameover");
	public static final ISound ground = Game.getResourceManager().getSound("1");
	public static final ISound hit = Game.getResourceManager().getSound("noscore");
	public static final ISound large = Game.getResourceManager().getSound("8");
	public static final ISound medium = Game.getResourceManager().getSound("16");
	public static final ISound small = Game.getResourceManager().getSound("32");
}
