package com.turpgames.physics.box2d.builders;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;

public class ChainShapeBuilder {
	private final static ChainShapeBuilder instance = new ChainShapeBuilder();

	private final static Vector2[] arrPrototype = new Vector2[0];

	private final List<Vector2> vertices = new ArrayList<Vector2>();
	private Vector2 prevVertex;
	private Vector2 nextVertex;
	private float radius;
	private boolean isLooped;

	private ChainShapeBuilder() {

	}

	private void reset() {
		vertices.clear();
		isLooped = false;
		radius = 0;
		prevVertex = null;
		nextVertex = null;
	}

	public ChainShapeBuilder setRadius(float r) {
		radius = r;
		return this;
	}

	public ChainShapeBuilder setLooped(boolean looped) {
		isLooped = looped;
		return this;
	}

	public ChainShapeBuilder addVertex(Vector2 vertex) {
		vertices.add(vertex);
		return this;
	}

	public ChainShapeBuilder addVertex(float x, float y) {
		return addVertex(new Vector2(x, y));
	}
	
	public ChainShapeBuilder setPreVertex(Vector2 prevVertex) {
		this.prevVertex = prevVertex;
		return this;
	}
	
	public ChainShapeBuilder setPreVertex(float x, float y) {
		return setPreVertex(new Vector2(x, y));
	}
	
	public ChainShapeBuilder setNextVertex(Vector2 nextVertex) {
		this.nextVertex = nextVertex;
		return this;
	}
	
	public ChainShapeBuilder setNextVertex(float x, float y) {
		return setNextVertex(new Vector2(x, y));
	}

	public ChainShape build() {
		ChainShape chain = new ChainShape();
		chain.setRadius(radius);
		
		if (isLooped) {
			chain.createLoop(getVerticeArray());
		}
		else {
			chain.createChain(getVerticeArray());
			if (prevVertex != null)
				chain.setPrevVertex(prevVertex);
			if (nextVertex != null)
				chain.setNextVertex(nextVertex);
		}
		
		return chain;
	}

	private Vector2[] getVerticeArray() {
		return vertices.toArray(arrPrototype);
	}

	static ChainShapeBuilder newChain() {
		instance.reset();
		return instance;
	}
}
