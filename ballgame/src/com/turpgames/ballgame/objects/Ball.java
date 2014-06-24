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
	private final BallObject ball;
	private final float radius = 40f;

	private final static float physicsFactor = 100f;
	private final static float gravity = -10 * physicsFactor;
	private final static float hitX = 5f * physicsFactor;
	private final static float hitY = 7.5f * physicsFactor;
	
	private final RollingEffect rollingEffect;

	public Ball() {
		ball = new BallObject();
		ball.setWidth(2 * radius);
		ball.setHeight(2 * radius);
		ball.getAcceleration().set(0, gravity);
		
		rollingEffect = new RollingEffect(new IRollingEffectSubject() {
			@Override
			public void setRotation(float angle) {
				ball.getRotation().setRotationZ(angle);
				update() ;
			}
		});

		reset();
	}
	
	public void update() {
		ball.getRotation().setOrigin(
				ball.getLocation().x + radius, 
				ball.getLocation().y + radius);
	}

	public void reset() {
		ball.getVelocity().set(0, 0);
		ball.getLocation().set(
				(Game.getVirtualWidth() - ball.getWidth()) / 2,
				(Game.getVirtualHeight() - ball.getHeight()) * 0.66f
				);
		rollingEffect.setAngularVelocity(0);
		ball.getRotation().setRotationZ(0);
	}

	public Vector getLocation() {
		return ball.getLocation();
	}

	public float getSize() {
		return ball.getWidth();
	}

	public void hit(float x) {
		float f = (ball.getLocation().x + radius - x) / radius;
		if (Math.abs(f) >= 0.5f)
			f = f > 0 ? 0.5f : -0.5f;
		ball.getVelocity().set(f * hitX, hitY);
		rollingEffect.setAngularVelocity(f * -360f);
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
}
