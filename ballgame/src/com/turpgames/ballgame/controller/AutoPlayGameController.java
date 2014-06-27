package com.turpgames.ballgame.controller;

import com.turpgames.ballgame.objects.Ball;
import com.turpgames.ballgame.objects.Walls;
import com.turpgames.ballgame.utils.Sounds;
import com.turpgames.ballgame.view.IScreenView;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rectangle;
import com.turpgames.utils.Util;

public class AutoPlayGameController {
	private final IScreenView view;
	private final Ball ball;
	private final Walls walls;

	public AutoPlayGameController(IScreenView view) {
		this.view = view;
		ball = Ball.defaultBall();
		walls = new Walls();
	}

	public void activate() {
		ball.reset();
		ball.beginMove();
		nextY = 200f;
		view.registerDrawable(ball, Game.LAYER_GAME);
		view.registerDrawable(walls, Game.LAYER_GAME);
	}

	public void deactivate() {
		ball.stopMoving();
		view.unregisterDrawable(ball);
		view.unregisterDrawable(walls);
	}

	private float nextY;
	public void update() {
		if (hasCollision()) {
			nextY = 200f;
			Sounds.gameover.play();
			ball.reset();
			ball.beginMove();
		}
		if (ball.isFallingDown() && ball.getLocation().y < nextY) {
			ball.hit(ball.getLocation().x + ball.getSize() / 2 + Util.Random.randInt(20) - 10);
			nextY = ball.getLocation().y + Util.Random.randInt(200) - 100;
		}
	}
	
	private boolean hasCollision() {
		float x = ball.getLocation().x;
		float y = ball.getLocation().y;
		float s = ball.getSize();

		Rectangle rect = walls.getRect();

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
}
