package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class RevoluteJointBuilder extends JointBuilder<RevoluteJointBuilder, RevoluteJointDef, RevoluteJoint> {
	private final static RevoluteJointBuilder instance = new RevoluteJointBuilder();

	private RevoluteJointBuilder() {
		super(new RevoluteJointDef());
	}

	@Override
	protected void reset() {
		super.reset();
		jointDef.localAnchorA.set(0, 0);
		jointDef.localAnchorB.set(0, 0);
		jointDef.referenceAngle = 0;
		jointDef.enableLimit = false;
		jointDef.lowerAngle = 0;
		jointDef.upperAngle = 0;
		jointDef.enableMotor = false;
		jointDef.motorSpeed = 0;
		jointDef.maxMotorTorque = 0;
	}

	public RevoluteJointBuilder setLocalAnchorA(float x, float y) {
		jointDef.localAnchorA.set(x, y);
		return this;
	}

	public RevoluteJointBuilder setLocalAnchorB(float x, float y) {
		jointDef.localAnchorB.set(x, y);
		return this;
	}

	public RevoluteJointBuilder setEnableLimit(boolean enableLimit) {
		jointDef.enableLimit = enableLimit;
		return this;
	}
	
	public RevoluteJointBuilder setLowerAngle(float lowerAngle) {
		jointDef.lowerAngle = lowerAngle;
		return this;
	}	
	
	public RevoluteJointBuilder setUpperAngle(float upperAngle) {
		jointDef.upperAngle = upperAngle;
		return this;
	}

	public RevoluteJointBuilder setEnableMotor(boolean enableMotor) {
		jointDef.enableMotor = enableMotor;
		return this;
	}

	public RevoluteJointBuilder setMotorSpeed(float motorSpeed) {
		jointDef.motorSpeed = motorSpeed;
		return this;
	}

	public RevoluteJointBuilder setMaxMotorTorque(float maxMotorTorque) {
		jointDef.maxMotorTorque = maxMotorTorque;
		return this;
	}

	static RevoluteJointBuilder newRevoluteJoint() {
		instance.reset();
		return instance;
	}
}
