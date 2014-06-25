package com.turpgames.ballgame.objects;

import com.turpgames.ballgame.utils.Textures;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rectangle;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.framework.v0.util.TextureDrawer;

public class Walls implements IDrawable {
	private static final float w = Game.getVirtualWidth() - 10F;
	private static final float h = Game.getVirtualHeight() - 10F;
	private static final float x = 5F;
	private static final float y = 5F;
	private static final Rectangle rect = new Rectangle(x, y, w, h);
	private static final Color wallColor = Color.white();
	private WallsObject walls;

	public Walls() {
		walls = new WallsObject();
		walls.getLocation().set(5F, 5F);
		walls.setWidth(w);
		walls.setHeight(h);
		walls.getColor().set(wallColor);
	}

	public void draw() {
		walls.draw();
	}

	public Rectangle getRect() {
		return rect;
	}

	private static class WallsObject extends GameObject {
		public void draw() {
			TextureDrawer.draw(Textures.bg, this);
			ShapeDrawer.drawRect(this, false);
		}
	}
}
