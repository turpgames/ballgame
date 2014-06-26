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

	private final TextButton restartButton;
	private final TextButton shareScoreButton;
	private final TextButton aboutButton;
	private final TextButton hiScoresButton;
	private final BallGameLogo logo;

	private boolean hideShareScoreButton;

	public ResultView(final IListener listener) {
		float y = Game.getVirtualHeight() / 2.0F;
		shareScoreButton = createButton("Share Score", y + 100f, new IButtonListener() {
			public void onButtonTapped() {
				listener.onShareScore();
			}
		});

		restartButton = createButton("Play Again", y, new IButtonListener() {
			public void onButtonTapped() {
				listener.onRestartGame();
			}
		});

		aboutButton = createButton("About", y - 200f, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.about, false);
			}
		});

		hiScoresButton = createButton("Hi Scores", y - 100f, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.leadersboard, false);
			}
		});

		logo = new BallGameLogo();
	}

	private static TextButton createButton(String s, float f, IButtonListener ibuttonlistener) {
		TextButton textbutton = new TextButton(Color.white(), R.colors.yellow);
		textbutton.setListener(ibuttonlistener);
		textbutton.setText(s);
		textbutton.getLocation().set((Game.getVirtualWidth() - textbutton.getWidth()) / 2.0F, f);
		textbutton.deactivate();
		return textbutton;
	}

	public void activate() {
		restartButton.activate();
		shareScoreButton.activate();
		aboutButton.activate();
		hiScoresButton.activate();
		hideShareScoreButton = false;
	}

	public void deactivate() {
		restartButton.deactivate();
		shareScoreButton.deactivate();
		aboutButton.deactivate();
		hiScoresButton.deactivate();
	}

	public void draw() {
		restartButton.draw();
		if (!hideShareScoreButton)
			shareScoreButton.draw();
		aboutButton.draw();
		hiScoresButton.draw();
		logo.draw();
	}

	public void hideShareScoreButton() {
		shareScoreButton.deactivate();
		hideShareScoreButton = true;
	}
}
