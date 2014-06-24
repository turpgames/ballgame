package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

public class FixtureBuilder {
	private final FixtureDef fixtureDef = new FixtureDef();
	
	private final static FixtureBuilder instance = new FixtureBuilder();
	
	private FixtureBuilder() {

	}

	private void reset() {
		// com.badlogic.gdx.physics.box2d.FixtureDef defaults
		fixtureDef.shape = null;
		fixtureDef.friction = 0.2f;
		fixtureDef.restitution = 0;
		fixtureDef.density = 0;
		fixtureDef.isSensor = false;
		fixtureDef.filter.categoryBits = 0x0001;
		fixtureDef.filter.maskBits = -1;
		fixtureDef.filter.groupIndex = 0;
	}
	
	public FixtureBuilder setShape(Shape shape) {
		fixtureDef.shape = shape;
		return this;
	}
	
	public FixtureBuilder setFriction(float friction) {
		fixtureDef.friction = friction;
		return this;
	}
	
	public FixtureBuilder setElasticity(float elasticity) {
		fixtureDef.restitution = elasticity;
		return this;
	}
	
	public FixtureBuilder setDensity(float density) {
		fixtureDef.density = density;
		return this;
	}
	
	public FixtureBuilder setSensor(boolean sensor) {
		fixtureDef.isSensor = sensor;
		return this;
	}
	
	public FixtureBuilder setFilterCategoryBits(short categoryBits) {
		fixtureDef.filter.categoryBits = categoryBits;
		return this;
	}
	
	public FixtureBuilder setFilterMaskBits(short maskBits) {
		fixtureDef.filter.maskBits = maskBits;
		return this;
	}
	
	public FixtureBuilder setFilterDensity(short groupIndex) {
		fixtureDef.filter.groupIndex = groupIndex;
		return this;
	}
	
	public Fixture build(Body body) {
		return body.createFixture(fixtureDef);
	}
	
	static FixtureBuilder newFixture() {
		instance.reset();
		return instance;
	}
}
