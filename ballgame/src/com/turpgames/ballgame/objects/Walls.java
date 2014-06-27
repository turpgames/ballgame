package com.turpgames.ballgame.objects;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rectangle;
import com.turpgames.framework.v0.util.ShapeDrawer;

public class Walls implements IDrawable {
	private static final float x = 5f;
	private static final float y = 5f;
	private static final float w = Game.getVirtualWidth() - 2 * x;
	private static final float h = Game.getVirtualHeight() - 2 * y;
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
	
	public boolean hasCollision(Ball ball) {
		float x = ball.getLocation().x;
		float y = ball.getLocation().y;
		float s = ball.getSize();

		if (x < rect.x) {
			ball.getLocation().x = rect.x;
			return true;
		}
		if (y < rect.y) {
			ball.getLocation().y = rect.y;
			return true;
		}
		if (rect.x + rect.width < x + s) {
			ball.getLocation().x = rect.x + rect.width - s;
			return true;
		}
		if (rect.y + rect.height < y + s) {
			ball.getLocation().y = rect.y + rect.height - s;
			return true;
		}
		return false;
	}

	private static class WallsObject extends GameObject {
		public void draw() {
			ShapeDrawer.drawRect(this, false);
		}
	}
}
