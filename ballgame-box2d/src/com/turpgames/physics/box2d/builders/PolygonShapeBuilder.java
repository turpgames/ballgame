package com.turpgames.physics.box2d.builders;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PolygonShapeBuilder {
	private final static PolygonShapeBuilder instance = new PolygonShapeBuilder();
	private final static Vector2[] arrPrototype = new Vector2[0];

	private final List<Vector2> vertices = new ArrayList<Vector2>();
	private float radius;

	private PolygonShapeBuilder() {

	}

	private void reset() {
		vertices.clear();
		radius = 0;
	}

	public PolygonShapeBuilder setRadius(float r) {
		radius = r;
		return this;
	}

	public PolygonShapeBuilder addVertex(Vector2 vertex) {
		vertices.add(vertex);
		return this;
	}

	public PolygonShape build() {
		PolygonShape polygon = new PolygonShape();
		polygon.setRadius(radius);
		polygon.set(getVerticeArray());
		return polygon;
	}

	private Vector2[] getVerticeArray() {
		return vertices.toArray(arrPrototype);
	}

	static PolygonShape buildBox(float width, float height) {
		instance.reset();
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(width / 2f, height / 2f);
		return polygon;
	}
	
	static PolygonShape buildBox(float width, float height, float x, float y) {
		return buildBox(width, height, x + width / 2f, y + height / 2f, 0);
	}

	static PolygonShape buildBox(float width, float height, float centerX, float centerY, float angle) {
		instance.reset();
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(width / 2f, height / 2f, new Vector2(centerX, centerY), angle);
		return polygon;
	}

	static PolygonShapeBuilder newPolygon() {
		instance.reset();
		return instance;
	}
}
