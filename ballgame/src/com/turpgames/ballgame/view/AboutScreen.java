package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.components.Toolbar;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.ballgame.utils.Textures;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.ImageButton;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;

public class AboutScreen extends Screen {
	private final static float buttonSize = 128f;
	private final static int buttonPerRow = 3;
	
	private AboutButton facebookButton;
	private AboutButton twitterButton;
	private AboutButton webSiteButton;
	private AboutButton didYouLikeButton;
	private AboutButton ballgameButton;
	private AboutButton ichiguButton;

	@Override
	public void init() {
		super.init();

		initVersionText();
		initFacebookButton();
		initTwitterButton();
		initWebSiteButton();
		initDidYouLikeButton();
		initBallGameButton();
		initIchiguButton();

		registerDrawable(new BallGameLogo(), Game.LAYER_SCREEN);
		registerDrawable(Toolbar.getInstance(), Game.LAYER_INFO);
	}

	private void initVersionText() {
		Text text = new Text();
		text.setText("v" + Game.getVersion());
		text.setFontScale(0.66f);
		text.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		text.setPadY(125f);
		registerDrawable(text, Game.LAYER_SCREEN);
	}
	
	private float getX(int i) {
		float dx = (Game.getVirtualWidth() - buttonPerRow * buttonSize) / (buttonPerRow + 1);
		return (i + 1) * dx + buttonSize * i;
	}

	private void initFacebookButton() {
		facebookButton = createButton(Textures.facebook, StatActions.ClickedFacebookInAbout, Game.getParam("facebook-address"), buttonSize, getX(0), 400f);
		registerDrawable(facebookButton, Game.LAYER_SCREEN);
	}

	private void initTwitterButton() {
		twitterButton = createButton(Textures.twitter, StatActions.ClickedTwitterInAbout, Game.getParam("twitter-address"), buttonSize, getX(1), 400f);
		registerDrawable(twitterButton, Game.LAYER_SCREEN);
	}

	private void initWebSiteButton() {
		webSiteButton = createButton(Textures.turplogo, StatActions.ClickedWebSiteInAbout, Game.getParam("turp-address"), buttonSize, getX(2), 400f);
		registerDrawable(webSiteButton, Game.LAYER_SCREEN);

	}

	private void initDidYouLikeButton() {
		didYouLikeButton = createButton(Textures.doubleup, StatActions.ClickedDoubleUpInAbout, getDoubleUpStoreUrl(), buttonSize, getX(1), 150f);
		registerDrawable(didYouLikeButton, Game.LAYER_SCREEN);
	}

	private void initBallGameButton() {
		ballgameButton = createButton(Textures.ballgame, StatActions.ClickedBallGameInAbout, getBallGameStoreUrl(), buttonSize, getX(0), 150f);
		registerDrawable(ballgameButton, Game.LAYER_SCREEN);
	}

	private void initIchiguButton() {
		ichiguButton = createButton(Textures.ichigu, StatActions.ClickedIchiguInAbout, getIchiguStoreUrl(), buttonSize, getX(2), 150f);
		registerDrawable(ichiguButton, Game.LAYER_SCREEN);
	}

	private String getDoubleUpStoreUrl() {
		if (Game.isIOS()) {
			if (Game.getOSVersion().getMajor() < 7)
				return Game.getParam("doubleup-app-store-address-old");
			else
				return Game.getParam("doubleup-app-store-address-ios7");
		}
		else {
			return Game.getParam("doubleup-play-store-address");
		}
	}

	private String getBallGameStoreUrl() {
		if (Game.isIOS()) {
			if (Game.getOSVersion().getMajor() < 7)
				return Game.getParam("app-store-address-old");
			else
				return Game.getParam("app-store-address-ios7");
		}
		else {
			return Game.getParam("play-store-address");
		}
	}

	private String getIchiguStoreUrl() {
		if (Game.isIOS()) {
			if (Game.getOSVersion().getMajor() < 7)
				return Game.getParam("ichigu-app-store-address-old");
			else
				return Game.getParam("ichigu-app-store-address-ios7");
		}
		else {
			return Game.getParam("ichigu-play-store-address");
		}
	}

	private static AboutButton createButton(ITexture texture, final int statAction, final String url, float size, float x, float y) {
		AboutButton btn = new AboutButton();
		btn.setTexture(texture);
		btn.setWidth(size);
		btn.setHeight(size);

		btn.getLocation().set(x, y);
		btn.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				Game.openUrl(url);
				TurpClient.sendStat(statAction);
			}
		});

		return btn;
	}

	@Override
	protected void onAfterActivate() {
		Toolbar.getInstance().enable();
		Toolbar.getInstance().setListener(new com.turpgames.framework.v0.component.Toolbar.IToolbarListener() {
			@Override
			public void onToolbarBack() {
				onBack();
			}
		});

		facebookButton.activate();
		twitterButton.activate();
		webSiteButton.activate();
		didYouLikeButton.activate();
		ballgameButton.activate();
		ichiguButton.activate();
	}

	@Override
	protected boolean onBeforeDeactivate() {
		Toolbar.getInstance().disable();

		facebookButton.deactivate();
		twitterButton.deactivate();
		webSiteButton.deactivate();
		didYouLikeButton.deactivate();
		ballgameButton.deactivate();
		ichiguButton.deactivate();

		return super.onBeforeDeactivate();
	}

	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.screens.game, true);
		return true;
	}

	private static class AboutButton extends ImageButton {
		@Override
		public boolean ignoreViewport() {
			return false;
		}
	}
}
