package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class MouseJointBuilder extends JointBuilder<MouseJointBuilder, MouseJointDef, MouseJoint> {
	private final static MouseJointBuilder instance = new MouseJointBuilder();

	protected MouseJointBuilder() {
		super(new MouseJointDef());
	}

	@Override
	protected void reset() {
		super.reset();
		jointDef.target.set(0, 0);
		jointDef.maxForce = 0;
		jointDef.frequencyHz = 5.0f;
		jointDef.dampingRatio = 0.7f;
	}

	public MouseJointBuilder setTarget(float x, float y) {
		jointDef.target.set(x, y);
		return this;
	}

	public MouseJointBuilder setMaxForce(float maxForce) {
		jointDef.maxForce = maxForce;
		return this;
	}

	public MouseJointBuilder setFrequencyHz(float frequencyHz) {
		jointDef.frequencyHz = frequencyHz;
		return this;
	}

	public MouseJointBuilder setDampingRatio(float dampingRatio) {
		jointDef.dampingRatio = dampingRatio;
		return this;
	}

	static MouseJointBuilder newMouseJoint() {
		instance.reset();
		return instance;
	}
}
