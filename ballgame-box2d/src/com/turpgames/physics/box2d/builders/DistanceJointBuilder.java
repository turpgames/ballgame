package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;

public class DistanceJointBuilder extends JointBuilder<DistanceJointBuilder, DistanceJointDef, DistanceJoint> {
	private final static DistanceJointBuilder instance = new DistanceJointBuilder();

	private DistanceJointBuilder() {
		super(new DistanceJointDef());
	}

	@Override
	protected void reset() {
		super.reset();
		jointDef.localAnchorA.set(0, 0);
		jointDef.localAnchorB.set(0, 0);
		jointDef.length = 1;
		jointDef.frequencyHz = 0;
		jointDef.dampingRatio = 0;
	}

	public DistanceJointBuilder setLocalAnchorA(float x, float y) {
		jointDef.localAnchorA.set(x, y);
		return this;
	}

	public DistanceJointBuilder setLocalAnchorB(float x, float y) {
		jointDef.localAnchorB.set(x, y);
		return this;
	}

	public DistanceJointBuilder setLength(float length) {
		jointDef.length = length;
		return this;
	}	
	
	public DistanceJointBuilder setFrequencyHz(float frequencyHz) {
		jointDef.frequencyHz = frequencyHz;
		return this;
	}

	public DistanceJointBuilder setDampingRatio(float dampingRatio) {
		jointDef.dampingRatio = dampingRatio;
		return this;
	}

	static DistanceJointBuilder newDistanceJoint() {
		instance.reset();
		return instance;
	}
}
