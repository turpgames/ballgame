package com.turpgames.ballgame.components;

import com.turpgames.ballgame.utils.Textures;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;

public class GestureTips implements IDrawable {
	private final GestureTipObj tapObj;
	private final GestureTipObj tiltObj;
	private final GestureTipObj swipeObj;

	public GestureTips() {
		tapObj = new GestureTipObj(Textures.tap);
		tiltObj = new GestureTipObj(Textures.tilt);
		swipeObj = new GestureTipObj(Textures.swipe);

		float w = Game.getVirtualWidth();
		float h = Game.getVirtualHeight();
		float s = tapObj.getWidth();
		float y = h - (s + 100f);
		
		tapObj.getLocation().set(w - s - 30f, y);
		tiltObj.getLocation().set(30f, y);
		swipeObj.getLocation().set((w - s) / 2, y);

		tapObj.setText("Just Tap!");
		tiltObj.setText("No Tilt,");
		swipeObj.setText("No Swipe,");

		tapObj.shiftText(10f);
		tiltObj.shiftText(30f);
		swipeObj.shiftText(10f);
	}

	@Override
	public void draw() {
		tapObj.draw();
		tiltObj.draw();
		swipeObj.draw();
	}

	private class GestureTipObj extends GameObject {
		private final ITexture texture;
		private final Text text;

		public GestureTipObj(ITexture texture) {
			this.texture = texture;
			setWidth(128f);
			setHeight(128f);

			this.text = new Text();
			this.text.setFontScale(0.66f);
			this.text.setWidth(getWidth());
		}

		public void setText(String text) {
			this.text.setText(text);
			float x = getLocation().x;
			float w = this.text.getWidth();
			this.text.setLocation(x + (getWidth() - w) / 2f, getLocation().y - 30f);
		}
		
		public void shiftText(float dx) {
			this.text.setLocation(this.text.getX() + dx, this.text.getY());
		}

		@Override
		public void draw() {
			TextureDrawer.draw(texture, this);
			text.draw();
		}
	}
}
