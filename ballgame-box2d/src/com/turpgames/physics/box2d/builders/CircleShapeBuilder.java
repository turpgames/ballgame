package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;

public class CircleShapeBuilder {
	private final static CircleShapeBuilder instance = new CircleShapeBuilder();
	
	private final Vector2 position = new Vector2();
	private float radius;
	
	private CircleShapeBuilder() {
		
	}
	
	private void reset() {
		position.set(0, 0);
		radius = 0;
	}
	
	public CircleShapeBuilder setRadius(float r) {
		radius = r;
		return this;
	}
	
	public CircleShapeBuilder setPosition(float x, float y) {
		position.set(x, y);
		return this;
	}
	
	public CircleShape build() {
		CircleShape circle = new CircleShape();
		circle.setPosition(position);
		circle.setRadius(radius);
		return circle;
	}
	
	static CircleShapeBuilder newCircle() {
		instance.reset();
		return instance;
	}
}
