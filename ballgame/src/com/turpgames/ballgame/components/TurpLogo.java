// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.turpgames.ballgame.components;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;

public class TurpLogo extends GameObject
{
	private static float logoSize;
	private ITexture logo;

	public TurpLogo()
	{
		logo = Game.getResourceManager().getTexture("turp_logo");
		setLogoSize(500F);
		getLocation().set(0.0F, 50F + (Game.getVirtualHeight() - logoSize) / 2.0F);
	}

	public void draw()
	{
		TextureDrawer.draw(logo, this);
	}

	public void setLogoSize(float f)
	{
		logoSize = f;
		setWidth(logoSize);
		setHeight(logoSize);
	}
}
