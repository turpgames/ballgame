package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.components.Toolbar;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class AboutScreen extends Screen {

	private TextButton facebookButton;
	private TextButton storeButton;
	private TextButton twitterButton;
	private TextButton webSiteButton;

	public void init() {
		super.init();
		initVersionText();
		initFacebookButton();
		initTwitterButton();
		initWebSiteButton();
		initStoreButton();
		registerDrawable(new BallGameLogo(), 100);
		registerDrawable(Toolbar.getInstance(), Game.LAYER_INFO);
	}

	private void initFacebookButton() {
		facebookButton = createButton("turpgames@facebook", Game.getParam("facebook-address"), StatActions.ClickedFacebookInAbout, 500F);
		registerDrawable(facebookButton, 100);
	}

	private void initTwitterButton() {
		twitterButton = createButton("turpgames@twitter", Game.getParam("twitter-address"), StatActions.ClickedTwitterInAbout, 400F);
		registerDrawable(twitterButton, 100);
	}

	private void initWebSiteButton() {
		webSiteButton = createButton("www.turpgames.com", Game.getParam("turp-address"), StatActions.ClickedWebSiteInAbout, 300F);
		registerDrawable(webSiteButton, 100);
	}

	private void initStoreButton() {
		storeButton = createButton("Did you like Ball Game?", getStoreUrl(), StatActions.ClickedDidYouLikeInAbout, 200F);
		registerDrawable(storeButton, 100);
	}

	private void initVersionText() {
		Text text = new Text();
		text.setText((new StringBuilder("v")).append(Game.getVersion()).toString());
		text.setFontScale(0.66F);
		text.setAlignment(0, 2);
		text.setPadY(125F);
		registerDrawable(text, 100);
	}

	private static TextButton createButton(String text, final String url, final int statAction, float y) {
		TextButton textbutton = new TextButton(Color.white(), R.colors.yellow);
		textbutton.setText(text);
		textbutton.setFontScale(0.8F);
		textbutton.getLocation().set((Game.getVirtualWidth() - textbutton.getWidth()) / 2.0F, y);
		textbutton.setListener(new IButtonListener() {
			public void onButtonTapped() {
				Game.openUrl(url);
				TurpClient.sendStat(statAction);
			}
		});
		return textbutton;
	}

	private String getStoreUrl() {
		if (Game.isIOS()) {
			if (Game.getOSVersion().getMajor() < 7) {
				return Game.getParam("app-store-address-old");
			} else {
				return Game.getParam("app-store-address-ios7");
			}
		} else {
			return Game.getParam("play-store-address");
		}
	}

	protected void onAfterActivate() {
		facebookButton.activate();
		twitterButton.activate();
		webSiteButton.activate();
		storeButton.activate();
		TurpClient.sendStat(StatActions.EnterAboutScreen);
		
		Toolbar.getInstance().enable();
		Toolbar.getInstance().setListener(new com.turpgames.framework.v0.component.Toolbar.IToolbarListener() {
			@Override
			public void onToolbarBack() {
				onBack();
			}
		});
	}

	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.screens.game, true);
		return true;
	}

	protected boolean onBeforeDeactivate() {
		facebookButton.deactivate();
		twitterButton.deactivate();
		webSiteButton.deactivate();
		storeButton.deactivate();
		Toolbar.getInstance().disable();
		return super.onBeforeDeactivate();
	}
}
