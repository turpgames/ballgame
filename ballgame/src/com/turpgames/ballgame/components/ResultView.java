package com.turpgames.ballgame.components;

import com.turpgames.ballgame.utils.R;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class ResultView implements IDrawable {
	public static interface IListener {
		public abstract void onRestartGame();

		public abstract void onShareScore();
	}

	// private final static String loginWithFacebook = "Login with Facebook";
	// private final static String shareScore = "Share Score";

	private final TextButton restartButton;
	// private final TextButton facebookButton;
	private final TextButton aboutButton;
	private final TextButton hiScoresButton;
	private final BallGameLogo logo;

	// private boolean hideShareScoreButton;

	public ResultView(final IListener listener) {
		float y = Game.getVirtualHeight() / 2.0F;
		// facebookButton = createButton(shareScore, y + 90f, new
		// IButtonListener() {
		// public void onButtonTapped() {
		// if (facebookButton.getText().equals(shareScore))
		// listener.onShareScore();
		// else
		// TurpClient.loginWithFacebook(new ICallback() {
		// @Override
		// public void onSuccess() {
		// facebookButton.setText(shareScore);
		// setLocation(facebookButton, facebookButton.getLocation().y);
		// }
		//
		// @Override
		// public void onFail(Throwable t) {
		//
		// }
		// });
		// }
		// });

		restartButton = createButton("Play Again", y - 150f, new IButtonListener() {
			public void onButtonTapped() {
				listener.onRestartGame();
			}
		});

		aboutButton = createButton("About", y + 50f, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.about, false);
			}
		});

		hiScoresButton = createButton("Hi Scores", y - 50f, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.hiscores, false);
			}
		});

		logo = new BallGameLogo();
	}

	private static TextButton createButton(String s, float f, IButtonListener ibuttonlistener) {
		TextButton textbutton = new TextButton(Color.white(), R.colors.yellow);
		textbutton.setListener(ibuttonlistener);
		textbutton.setText(s);
		textbutton.deactivate();

		setLocation(textbutton, f);

		return textbutton;
	}

	private static void setLocation(TextButton btn, float y) {
		btn.getLocation().set((Game.getVirtualWidth() - btn.getWidth()) / 2.0F, y);
	}

	public void activate() {
		restartButton.activate();
		// facebookButton.activate();
		aboutButton.activate();
		hiScoresButton.activate();
		// hideShareScoreButton = false;
		// if (!TurpClient.isRegistered() ||
		// TurpClient.getPlayer().getAuthProvider() == Player.AuthAnonymous)
		// facebookButton.setText(loginWithFacebook);
		// else {
		// facebookButton.setText(shareScore);
		//
		// }
		// setLocation(facebookButton, facebookButton.getLocation().y);
	}

	public void deactivate() {
		restartButton.deactivate();
		// facebookButton.deactivate();
		aboutButton.deactivate();
		hiScoresButton.deactivate();
	}

	public void draw() {
		restartButton.draw();
		// if (!hideShareScoreButton)
		// facebookButton.draw();
		aboutButton.draw();
		hiScoresButton.draw();
		logo.draw();
	}

	public void hideShareScoreButton() {
		// facebookButton.deactivate();
		// hideShareScoreButton = true;
	}
}
