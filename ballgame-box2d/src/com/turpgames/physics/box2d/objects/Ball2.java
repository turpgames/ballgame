//package com.turpgames.physics.box2d.objects;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.Shape;
//import com.turpgames.ballgame.utils.Sounds;
//import com.turpgames.ballgame.utils.Textures;
//import com.turpgames.framework.v0.IDrawable;
//import com.turpgames.framework.v0.ITexture;
//import com.turpgames.framework.v0.effects.BreathEffect;
//import com.turpgames.framework.v0.impl.GameObject;
//import com.turpgames.framework.v0.util.TextureDrawer;
//import com.turpgames.physics.box2d.Box2D;
//import com.turpgames.physics.box2d.Box2DWorld;
//import com.turpgames.physics.box2d.IBox2DObject;
//import com.turpgames.physics.box2d.builders.BodyBuilder;
//import com.turpgames.physics.box2d.builders.Box2DBuilders;
//
//public class Ball2 implements IDrawable, IBox2DObject {
//	public final static int Blue = 1;
//	public final static int Green = 2;
//	public final static int Red = 3;
//	public final static int Gray = 4;
//	public final static int Yellow = 5;
//	public final static int Purple = 6;
//	public final static int Dark = 7;
//
//	public final static float Small = 30f;
//	public final static float Medium = 40f;
//	public final static float Large = 50f;
//
//	private final static float hitX = 4f;
//	private final static float hitY = 6f;
//
//	private final Ball2Object ball;
//	private final Body body;
//
//	private final int type;
//	private final float radius;
//	private final boolean isElastic;
//
//	private BreathEffect effect;
//
//	private Ball2(Builder builder, Box2DWorld world) {
//		this.type = builder.type;
//		this.radius = builder.radius;
//		this.isElastic = type == Yellow;
//
//		ball = new Ball2Object(getBallTexture(builder.type));
//
//		ball.setWidth(builder.radius * 2);
//		ball.setHeight(builder.radius * 2);
//		ball.getLocation().set(
//				builder.cx - builder.radius,
//				builder.cy - builder.radius);
//
//		Shape circle = Box2DBuilders.Shape.buildCircle(
//				Box2D.viewportToWorld(builder.radius));
//
//		BodyBuilder bodyBuilder = builder.type == Blue
//				? Box2DBuilders.Body.dynamicBodyBuilder()
//				: Box2DBuilders.Body.staticBodyBuilder();
//
//		body = bodyBuilder
//				.setCenter(
//						Box2D.viewportToWorldX(builder.cx),
//						Box2D.viewportToWorldY(builder.cy))
//				.build(world.getWorld(),
//						Box2DBuilders.Fixture.fixtureBuilder()
//								.setElasticity(isElastic ? 1.5f : 0.8f)
//								.setDensity(1.2f)
//								.setFriction(0.1f)
//								.setShape(circle));
//
//		body.setUserData(this);
//
//		circle.dispose();
//
//		if (isElastic) {
//			effect = new BreathEffect(ball);
//			effect.setDuration(0.1f);
//			effect.setFinalScale(1.0f);
//			effect.setMaxFactor(1.1f);
//			effect.setMinFactor(1.0f);
//		}
//	}
//
//	@Override
//	public void draw() {
//		ball.draw();
//	}
//
//	public float getRadius() {
//		return radius;
//	}
//
//	public void syncWithBody() {
//		Vector2 bodyPos = body.getPosition();
//		float cx = Box2D.worldToViewportX(bodyPos.x);
//		float cy = Box2D.worldToViewportY(bodyPos.y);
//
//		ball.getRotation().origin.set(cx, cy);
//		ball.getLocation().set(cx - radius, cy - radius);
//		ball.getRotation().setRotationZ(MathUtils.radiansToDegrees * body.getAngle());
//	}
//
//	public void playHitEffect() {
//		playHitSound();
//		if (effect != null)
//			effect.start();
//	}
//
//	private void playHitSound() {
//		if (radius == Ball2.Small)
//			Sounds.small.play();
//		else if (radius == Ball2.Large)
//			Sounds.large.play();
//		else
//			Sounds.medium.play();
//	}
//
//	public void hit2(float x, float y) {
//		float vx = (ball.getLocation().x + radius - x);
//		float vy = (ball.getLocation().y + radius - y);
//		float f = (float) Math.sqrt(vx * vx + vy * vy);
//		body.setLinearVelocity((vx / f) * hitY, (vy / f) * hitY);
//	}
//
//	public void hit(float x) {
//		float f = (ball.getLocation().x + radius - x) / radius;
//		if (Math.abs(f) >= 0.5f)
//			f = f > 0 ? 0.5f : -0.5f;
//		body.setLinearVelocity(f * hitX, hitY);
//	}
//
//	public static Builder newBuilder(int type) {
//		return new Builder(type);
//	}
//
//	private static ITexture getBallTexture(int type) {
//		switch (type) {
//		case Blue:
//			return Textures.ball_blue;
//		case Green:
//			return Textures.ball_green;
//		case Red:
//			return Textures.ball_red;
//		case Gray:
//			return Textures.ball_gray;
//		case Yellow:
//			return Textures.ball_yellow;
//		case Purple:
//			return Textures.ball_purple;
//		case Dark:
//			return Textures.ball_dark;
//		default:
//			throw new UnsupportedOperationException("Unknown type code: " + type);
//		}
//	}
//
//	public static class Builder {
//		private final int type;
//		private float radius = Ball2.Medium;
//		private float cx;
//		private float cy;
//
//		private Builder(int type) {
//			this.type = type;
//		}
//
//		public Builder setRadius(float radius) {
//			this.radius = radius;
//			return this;
//		}
//
//		public Builder setCenter(float cx, float cy) {
//			this.cx = cx;
//			this.cy = cy;
//			return this;
//		}
//
//		public Ball2 build(Box2DWorld world) {
//			return new Ball2(this, world);
//		}
//	}
//
//	private static class Ball2Object extends GameObject {
//		private final ITexture ballTexture;
//
//		public Ball2Object(ITexture texture) {
//			ballTexture = texture;
//		}
//
//		@Override
//		public void draw() {
//			TextureDrawer.draw(ballTexture, this);
//		}
//	}
//}
