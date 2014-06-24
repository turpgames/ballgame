package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

public class RopeJointBuilder extends JointBuilder<RopeJointBuilder, RopeJointDef, RopeJoint> {
	private final static RopeJointBuilder instance = new RopeJointBuilder();

	private RopeJointBuilder() {
		super(new RopeJointDef());
	}

	@Override
	protected void reset() {
		super.reset();
		jointDef.localAnchorA.set(0, 0);
		jointDef.localAnchorB.set(0, 0);
		jointDef.maxLength = 0;
	}

	public RopeJointBuilder setLocalAnchorA(float x, float y) {
		jointDef.localAnchorA.set(x, y);
		return this;
	}

	public RopeJointBuilder setLocalAnchorB(float x, float y) {
		jointDef.localAnchorB.set(x, y);
		return this;
	}

	public RopeJointBuilder setMaxLength(float maxLength) {
		jointDef.maxLength = maxLength;
		return this;
	}

	static RopeJointBuilder newRopeJoint() {
		instance.reset();
		return instance;
	}
}
