package com.turpgames.physics.box2d.builders;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public final class Box2DBuilders {
	private Box2DBuilders() {

	}

	public static class Body {
		public static BodyBuilder staticBodyBuilder() {
			return BodyBuilder.newStaticBody();
		}

		public static BodyBuilder dynamicBodyBuilder() {
			return BodyBuilder.newDynamicBody();
		}

		public static BodyBuilder kinematicBodyBuilder() {
			return BodyBuilder.newKinematicBody();
		}
	}

	public static class Shape {
		public static PolygonShapeBuilder polygonBuilder() {
			return PolygonShapeBuilder.newPolygon();
		}
		
		public static CircleShape buildCircle(float r) {
			return circleBuilder().setRadius(r).build();
		}

		public static PolygonShape buildBox(float width, float height) {
			return PolygonShapeBuilder.buildBox(width, height);
		}

		public static PolygonShape buildBox(float width, float height, float x, float y) {
			return PolygonShapeBuilder.buildBox(width, height, x, y);
		}

		public static PolygonShape buildBox(float width, float height, float centerX, float centerY, float angle) {
			return PolygonShapeBuilder.buildBox(width, height, centerX, centerY, angle);
		}

		public static CircleShapeBuilder circleBuilder() {
			return CircleShapeBuilder.newCircle();
		}

		public static ChainShapeBuilder chainBuilder() {
			return ChainShapeBuilder.newChain();
		}

		public static LoopedChainShapeBuilder loopedChainBuilder() {
			return LoopedChainShapeBuilder.newLoopedChain();
		}
	}

	public static class Fixture {
		public static FixtureBuilder fixtureBuilder() {
			return FixtureBuilder.newFixture();
		}
	}
	
	public static class Joint {
		public static RopeJointBuilder ropeJointBuilder() {
			return RopeJointBuilder.newRopeJoint();
		}
		
		public static MouseJointBuilder mouseJointBuilder() {
			return MouseJointBuilder.newMouseJoint();
		}
		
		public static DistanceJointBuilder distanceJointBuilder() {
			return DistanceJointBuilder.newDistanceJoint();
		}
		
		public static RevoluteJointBuilder revoluteJointBuilder() {
			return RevoluteJointBuilder.newRevoluteJoint();
		}
	}
}
