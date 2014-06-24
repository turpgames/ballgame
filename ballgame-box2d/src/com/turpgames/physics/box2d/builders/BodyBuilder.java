package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyBuilder {
	private final static BodyBuilder instance = new BodyBuilder();
	
	private final BodyDef bodyDef;
	
	private BodyBuilder() {
		bodyDef = new BodyDef();
	}
	
	private void reset() {
		// com.badlogic.gdx.physics.box2d.BodyDef defaults
		bodyDef.active = true;
		bodyDef.allowSleep = true;
		bodyDef.awake = true;
		bodyDef.bullet = false;
		bodyDef.fixedRotation = false;
		bodyDef.angle = 0;
		bodyDef.angularDamping = 0;
		bodyDef.angularVelocity = 0;
		bodyDef.gravityScale = 1;
		bodyDef.linearDamping = 0;
		bodyDef.linearVelocity.set(0,0);
		bodyDef.position.set(0,0);
		bodyDef.type = BodyType.StaticBody;
	}
	
	public BodyBuilder setCenter(float centerX, float centerY) {
		bodyDef.position.set(centerX, centerY);
		return this;
	}
	
	public BodyBuilder setAngle(float angle) {
		bodyDef.angle = angle;
		return this;
	}
	
	public BodyBuilder setLinearVelocity(float vx, float vy) {
		bodyDef.linearVelocity.set(vx, vy);
		return this;
	}
	
	public BodyBuilder setAngularVelocity(float angularVelocity) {
		bodyDef.angularVelocity = angularVelocity;
		return this;
	}
	
	public BodyBuilder setLinearDamping(float linearDamping) {
		bodyDef.linearDamping = linearDamping;
		return this;
	}
	
	public BodyBuilder setAngularDamping(float angularDamping) {
		bodyDef.angularDamping = angularDamping;
		return this;
	}
	
	public BodyBuilder setAllowSleep(boolean allowSleep) {
		bodyDef.allowSleep = allowSleep;
		return this;
	}
	
	public BodyBuilder setSleeping(boolean awake) {
		bodyDef.awake = awake;
		return this;
	}
	
	public BodyBuilder setFixedRotation(boolean fixedRotation) {
		bodyDef.fixedRotation = fixedRotation;
		return this;
	}
	
	public BodyBuilder setBullet(boolean bullet) {
		bodyDef.bullet = bullet;
		return this;
	}
	
	public BodyBuilder setActive(boolean active) {
		bodyDef.active = active;
		return this;
	}
	
	public BodyBuilder setGravityScale(float gravityScale) {
		bodyDef.gravityScale = gravityScale;
		return this;
	}
	
	public Body build(World world) {
		return world.createBody(bodyDef);
	}
	
	public Body build(World world, FixtureBuilder... fixtureBuilders) {
		Body body = world.createBody(bodyDef);
		for (FixtureBuilder fixtureBuilder : fixtureBuilders) {
			fixtureBuilder.build(body);
		}
		return body;
	}
	
	static BodyBuilder newStaticBody() {
		return newBody(BodyType.StaticBody);
	}
	
	static BodyBuilder newDynamicBody() {
		return newBody(BodyType.DynamicBody);
	}
	
	static BodyBuilder newKinematicBody() {
		return newBody(BodyType.KinematicBody);
	}
	
	private static BodyBuilder newBody(BodyType type) {
		instance.reset();
		instance.bodyDef.type = type;
		return instance;
	}
}
