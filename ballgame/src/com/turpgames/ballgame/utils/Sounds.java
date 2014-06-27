package com.turpgames.ballgame.utils;

import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.util.Game;

public class Sounds
{
	public static final ISound gameover = Game.getResourceManager().getSound("gameover");
	public static final ISound hit = Game.getResourceManager().getSound("hit");
}
