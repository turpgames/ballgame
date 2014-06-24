package com.turpgames.ballgame.view;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.IInputListener;

public interface IScreenView
{
	public abstract void registerDrawable(IDrawable idrawable, int i);

	public abstract void registerInputListener(IInputListener iinputlistener);

	public abstract void unregisterDrawable(IDrawable idrawable);

	public abstract void unregisterInputListener(IInputListener iinputlistener);
}
