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
		void onRestartGame();
	}

	private final TextButton restartButton;
	private final TextButton aboutButton;
	private final TextButton hiScoresButton;
	private final BallGameLogo logo;

	public ResultView(final IListener listener) {
		float y = Game.getVirtualHeight() / 2.0f;

		restartButton = createButton("Play Again", y - 120f, new IButtonListener() {
			public void onButtonTapped() {
				listener.onRestartGame();
			}
		});

		aboutButton = createButton("About", y + 60f, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.about, false);
			}
		});

		hiScoresButton = createButton("Hi Scores", y - 30, new IButtonListener() {
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.hiscores, false);
			}
		});

		logo = new BallGameLogo();
	}

	private static TextButton createButton(String s, float y, IButtonListener ibuttonlistener) {
		TextButton btn = new TextButton(Color.white(), R.colors.yellow);
		btn.setListener(ibuttonlistener);
		btn.setFontScale(0.9f);
		btn.setText(s);
		btn.deactivate();

		btn.getLocation().set((Game.getVirtualWidth() - btn.getWidth()) / 2.0f, y);

		return btn;
	}

	public void activate() {
		restartButton.activate();
		aboutButton.activate();
		hiScoresButton.activate();
	}

	public void deactivate() {
		restartButton.deactivate();
		aboutButton.deactivate();
		hiScoresButton.deactivate();
	}

	public void draw() {
		restartButton.draw();
		aboutButton.draw();
		hiScoresButton.draw();
		logo.draw();
	}
}
