package com.turpgames.physics.box2d;

import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.util.Game;

public class Box2DWorld implements IDrawable {
	private final static float step = 1 / 60f;
	private final static int velocityIterations = 6;
	private final static int positionIterations = 2;

	private final World world;
	private final OrthographicCamera camera;
	private final Box2DDebugRenderer debugRenderer;

	private final static float viewportWidth = Game.descale(Game.getScreenWidth());
	private final static float viewportHeight = Game.descale(Game.getScreenHeight());

	private final static float worldWidth = Box2D.viewportToWorld(viewportWidth);
	private final static float worldHeight = Box2D.viewportToWorld(viewportHeight);

	public Box2DWorld() {
		this.world = new World(new Vector2(0, -10f), true);
		this.camera = new OrthographicCamera(worldWidth, worldHeight);
		this.debugRenderer = new Box2DDebugRenderer();

		debugRenderer.setDrawContacts(false);

		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
		camera.update();
	}

	public World getWorld() {
		return world;
	}

	public float getWidth() {
		return camera.viewportWidth;
	}

	public float getHeight() {
		return camera.viewportHeight;
	}

	private Array<Body> bodies = new Array<Body>();

	public void update() {
		world.step(step, velocityIterations, positionIterations);

		world.getBodies(bodies);
		Iterator<Body> bi = bodies.iterator();

		while (bi.hasNext()) {
			Body body = bi.next();

			IBox2DObject box2DObj = (IBox2DObject) body.getUserData();
			if (box2DObj != null) {
				box2DObj.syncWithBody();
			}
		}
	}

	public void draw() {
		debugRenderer.render(world, camera.combined);
	}
}
