package com.turpgames.physics.box2d.builders;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;

public class LoopedChainShapeBuilder {
	private final static LoopedChainShapeBuilder instance = new LoopedChainShapeBuilder();

	private final static Vector2[] arrPrototype = new Vector2[0];

	private final List<Vector2> vertices = new ArrayList<Vector2>();
	private float radius;

	private LoopedChainShapeBuilder() {

	}

	private void reset() {
		vertices.clear();
		radius = 0;
	}

	public LoopedChainShapeBuilder setRadius(float r) {
		radius = r;
		return this;
	}

	public LoopedChainShapeBuilder addVertex(Vector2 vertex) {
		vertices.add(vertex);
		return this;
	}

	public LoopedChainShapeBuilder addVertex(float x, float y) {
		return addVertex(new Vector2(x, y));
	}
	
	public ChainShape build() {
		ChainShape chain = new ChainShape();
		chain.setRadius(radius);
		
		chain.createLoop(getVerticeArray());
		
		return chain;
	}

	private Vector2[] getVerticeArray() {
		return vertices.toArray(arrPrototype);
	}

	static LoopedChainShapeBuilder newLoopedChain() {
		instance.reset();
		return instance;
	}
}
