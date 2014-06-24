//package com.turpgames.physics.box2d.objects;
//
//import com.badlogic.gdx.physics.box2d.Contact;
//import com.badlogic.gdx.physics.box2d.ContactImpulse;
//import com.badlogic.gdx.physics.box2d.ContactListener;
//import com.badlogic.gdx.physics.box2d.Manifold;
//import com.turpgames.ballgame.utils.Sounds;
//import com.turpgames.ballgame.view.IScreenView;
//import com.turpgames.framework.v0.IDrawable;
//import com.turpgames.framework.v0.impl.InputListener;
//import com.turpgames.framework.v0.util.Game;
//import com.turpgames.physics.box2d.Box2DWorld;
//
//public class GameController2 {
//	private final IScreenView view;
//	private final Box2DWorld world;
//	private Ball2 ball;
//
//	public GameController2(IScreenView view) {
//		this.view = view;
//		this.world = new Box2DWorld();
//	}
//
//	public void activate() {
//		view.registerInputListener(listener);
//		registerGameDrawable(new Walls2(world));
//		registerGameDrawable(ball = newBall(150, 700, Ball2.Blue));
//		registerGameDrawable(newBall(325, 550, Ball2.Green, Ball2.Small));
//		registerGameDrawable(newBall(180, 170, Ball2.Yellow, Ball2.Large));
//		registerGameDrawable(newBall(55, 100, Ball2.Gray, Ball2.Medium));
//		registerGameDrawable(newBall(320, 350, Ball2.Purple, Ball2.Small));
//		registerGameDrawable(newBall(430, 270, Ball2.Red, Ball2.Large));
//		registerGameDrawable(newBall(240, 620, Ball2.Dark, Ball2.Small));
//		registerGameDrawable(newBall(120, 500, Ball2.Green, Ball2.Medium));
//		// registerGameDrawable(world);
//		world.getWorld().setContactListener(new ContactListener() {
//			@Override
//			public void preSolve(Contact contact, Manifold oldManifold) {
//				
//			}
//			
//			@Override
//			public void postSolve(Contact contact, ContactImpulse impulse) {
//				
//			}
//			
//			@Override
//			public void endContact(Contact contact) {
//				
//			}
//			
//			@Override
//			public void beginContact(Contact contact) {
//				Object o1 = contact.getFixtureA().getBody().getUserData();
//				Object o2 = contact.getFixtureB().getBody().getUserData();
//				
//				if (o1 == null || o2 == null) {
//					Sounds.ground.play();	
//					return;
//				}
//				
//				Ball2 b1 = (Ball2)o1;
//				Ball2 b2 = (Ball2)o2;
//				
//				b1.playHitEffect();
//				b2.playHitEffect();
//			}
//		});
//	}
//
//	public void deactivate() {
//	}
//
//	public void update() {
//		world.update();
//	}
//
//	private boolean onTap() {
//		return false;
//	}
//
//	private boolean onTouchDown(float x, float y) {
//		ball.hit2(Game.screenToViewportX(x), Game.screenToViewportY(y));
//		Sounds.hit.play();
//		return true;
//	}
//
//	private void registerGameDrawable(IDrawable drawable) {
//		view.registerDrawable(drawable, Game.LAYER_GAME);
//	}
//
//	private Ball2 newBall(float cx, float cy, int type) {
//		return newBall(cx, cy, type, Ball2.Medium);
//	}
//
//	private Ball2 newBall(float cx, float cy, int type, float r) {
//		return Ball2.newBuilder(type).setCenter(cx, cy).setRadius(r).build(world);
//	}
//
//	private final InputListener listener = new InputListener() {
//		@Override
//		public boolean touchDown(float x, float y, int pointer, int button) {
//			return onTouchDown(x, y);
//		}
//
//		@Override
//		public boolean tap(float x, float y, int count, int button) {
//			return onTap();
//		}
//	};
//}
