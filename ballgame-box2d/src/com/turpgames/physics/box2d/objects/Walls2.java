//package com.turpgames.physics.box2d.objects;
//
//import com.badlogic.gdx.physics.box2d.Shape;
//import com.turpgames.ballgame.utils.Textures;
//import com.turpgames.framework.v0.IDrawable;
//import com.turpgames.framework.v0.impl.GameObject;
//import com.turpgames.framework.v0.util.Color;
//import com.turpgames.framework.v0.util.Game;
//import com.turpgames.framework.v0.util.Rectangle;
//import com.turpgames.framework.v0.util.ShapeDrawer;
//import com.turpgames.framework.v0.util.TextureDrawer;
//import com.turpgames.physics.box2d.Box2D;
//import com.turpgames.physics.box2d.Box2DWorld;
//import com.turpgames.physics.box2d.builders.Box2DBuilders;
//
//public class Walls2 implements IDrawable {
//	private final static Color wallColor = Color.white();
//
//	private final static float x = 5;
//	private final static float y = 5;
//	private final static float w = Game.getVirtualWidth() - 2 * x;
//	private final static float h = Game.getVirtualHeight() - 2 * y;
//
//	private final static Rectangle rect = new Rectangle(x, y, w, h);
//
//	private WallsObject walls;
//
//	public Walls2(Box2DWorld world) {
//		walls = new WallsObject();
//		walls.getLocation().set(x, y);
//		walls.setWidth(w);
//		walls.setHeight(h);
//		walls.getColor().set(wallColor);
//
//		Shape chain = Box2DBuilders.Shape.loopedChainBuilder()
//				.addVertex(Box2D.viewportToWorldX(x), Box2D.viewportToWorldY(y))
//				.addVertex(Box2D.viewportToWorldX(x + w), Box2D.viewportToWorldY(y))
//				.addVertex(Box2D.viewportToWorldX(x + w), Box2D.viewportToWorldY(y + h))
//				.addVertex(Box2D.viewportToWorldX(x), Box2D.viewportToWorldY(y + h))
//				.build();
//		
//		Box2DBuilders.Body.staticBodyBuilder()
//				.build(world.getWorld(),
//						Box2DBuilders.Fixture.fixtureBuilder()
//								.setElasticity(0.8f)
//								.setDensity(1.2f)
//								.setFriction(0.1f)
//								.setShape(chain));
//		
//		chain.dispose();
//	}
//
//	@Override
//	public void draw() {
//		walls.draw();
//	}
//
//	public Rectangle getRect() {
//		return rect;
//	}
//
//	private static class WallsObject extends GameObject {
//		@Override
//		public void draw() {
//			TextureDrawer.draw(Textures.bg, this);
//			ShapeDrawer.drawRect(this, false);
//		}
//	}
//}
