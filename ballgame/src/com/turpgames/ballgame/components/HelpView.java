package com.turpgames.ballgame.components;

import com.turpgames.ballgame.utils.Textures;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;

public class HelpView implements IDrawable {
	private static final float itemSize = 150F;
	private final HelpItem tapBottom;
	private final HelpItem tapLeft;
	private final HelpItem tapRight;

	public HelpView() {
		tapRight = new HelpItem(Textures.tap_right);
		tapBottom = new HelpItem(Textures.tap_bottom);
		tapLeft = new HelpItem(Textures.tap_left);
		float f = (Game.getVirtualWidth() - 3 * itemSize) / 4F;
		tapRight.setLocation(f, itemSize);
		tapBottom.setLocation(itemSize + 2.0F * f, itemSize);
		tapLeft.setLocation(2 * itemSize + 3F * f, itemSize);
	}

	public void draw() {
		tapRight.draw();
		tapBottom.draw();
		tapLeft.draw();
	}

	class HelpItem extends GameObject {
		private final ITexture texture;

		public void draw() {
			TextureDrawer.draw(texture, this);
		}

		public void setLocation(float f, float f1) {
			getLocation().set(f, f1);
		}

		HelpItem(ITexture itexture) {
			texture = itexture;
			setWidth(150F);
			setHeight(150F);
		}
	}
}
