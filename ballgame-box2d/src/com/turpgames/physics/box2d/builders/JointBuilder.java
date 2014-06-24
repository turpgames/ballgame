package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;

@SuppressWarnings("unchecked")
public abstract class JointBuilder<TJointBuilder extends JointBuilder<TJointBuilder, TJointDef, TJoint>, TJointDef extends JointDef, TJoint extends Joint> {
	protected final TJointDef jointDef;

	protected JointBuilder(TJointDef jointDef) {
		this.jointDef = jointDef;
	}

	protected void reset() {
		jointDef.bodyA = null;
		jointDef.bodyB = null;
		jointDef.collideConnected = false;
	}

	public TJointBuilder setBodyA(Body bodyA) {
		jointDef.bodyA = bodyA;
		return (TJointBuilder) this;
	}

	public TJointBuilder setBodyB(Body bodyB) {
		jointDef.bodyB = bodyB;
		return (TJointBuilder) this;
	}

	public TJointBuilder setCollideConnected(boolean collideConnected) {
		jointDef.collideConnected = collideConnected;
		return (TJointBuilder) this;
	}

	public TJoint build(World world) {
		return (TJoint) world.createJoint(jointDef);
	}
}
