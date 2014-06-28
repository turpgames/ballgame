package com.turpgames.ballgame.objects;

import com.turpgames.ballgame.utils.Textures;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.effects.rolling.IRollingEffectSubject;
import com.turpgames.framework.v0.effects.rolling.RollingEffect;
import com.turpgames.framework.v0.impl.DefaultMover;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;
import com.turpgames.framework.v0.util.Vector;

public class Ball implements IDrawable {
	private final static float physicsFactor = 100f;

	private final BallObject ball;

	private final float radius;
	private final float gravity;
	private final float hitX;
	private final float hitY;

	private final RollingEffect rollingEffect;

	private Ball(float r, float g, float fx, float fy) {
		this.radius = r;
		this.gravity = -g * physicsFactor;
		this.hitX = fx * physicsFactor;
		this.hitY = fy * physicsFactor;

		ball = new BallObject();
		ball.setWidth(2 * radius);
		ball.setHeight(2 * radius);
		ball.getAcceleration().set(0, gravity);

		rollingEffect = new RollingEffect(new IRollingEffectSubject() {
			@Override
			public void setRotation(float angle) {
				ball.getRotation().setRotationZ(angle);
				update();
			}
		});

		reset();
	}

	public void update() {
		ball.getRotation().setOrigin(ball.getLocation().x + radius, ball.getLocation().y + radius);
	}

	public void reset() {
		ball.getVelocity().set(0, 0);
		ball.getLocation().set((Game.getVirtualWidth() - ball.getWidth()) / 2, (Game.getVirtualHeight() - ball.getHeight()) * 0.66f);
		rollingEffect.setAngularVelocity(0);
		ball.getRotation().setRotationZ(0);
	}

	public Vector getLocation() {
		return ball.getLocation();
	}

	public float getSize() {
		return ball.getWidth();
	}

	public boolean isFallingDown() {
		return ball.getVelocity().y < 0;
	}

	public void setY(float y) {
		ball.getLocation().y = y;
	}

	public void hit(float x) {
		float dxPixels = ball.getLocation().x + radius - x;
		float dxInches = Game.viewportToInches(dxPixels);
		float adxPixels = Math.abs(dxPixels);

		if (Math.abs(dxInches) > 0.157f) {
			dxInches = dxInches < 0 ? -0.157f : 0.157f; // 0.157' = 0.4cm which is radius on iphone 5s
			adxPixels = radius;
		}

		ball.getVelocity().set(dxInches * hitX, hitY);
		rollingEffect.setAngularVelocity(dxInches * adxPixels * -300f);
	}

	public void beginMove() {
		ball.beginMove(new DefaultMover());
		rollingEffect.start();
	}

	public void stopMoving() {
		ball.stopMoving();
		rollingEffect.stop();
	}

	@Override
	public void draw() {
		ball.draw();
	}

	private static class BallObject extends GameObject {
		@Override
		public void draw() {
			TextureDrawer.draw(Textures.ball_blue, this);
		}
	}

	public static Ball defaultBall() {
		return new Ball(40f, 10f, 40f, 7.5f);
	}

	public static Ball demoBall() {
		return new Ball(40f, 2f, 16f, 3f);
	}
}
