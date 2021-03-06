package com.turpgames.ballgame.controller;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.components.HelpButton;
import com.turpgames.ballgame.components.ResultView;
import com.turpgames.ballgame.objects.Ball;
import com.turpgames.ballgame.objects.Walls;
import com.turpgames.ballgame.utils.BallGameAds;
import com.turpgames.ballgame.utils.BallGameMode;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.Sounds;
import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.ballgame.view.IScreenView;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.GameUtils;

public class GameController {
	private final IScreenView view;
	private final Ball ball;
	private final Walls walls;

	private final Text scoreText;
	private final Text hiscoreText;
	private final Text infoText;
	private final ResultView resultView;
	private final BallGameLogo logo;
	private final HelpButton helpButton;
	private int score;

	private boolean isPlaying = false;
	private boolean canStart = false;
	private int hiscore;

	public GameController(IScreenView view) {
		this.view = view;
		this.logo = new BallGameLogo();

		ball = Ball.defaultBall();
		walls = new Walls();

		this.scoreText = new Text();
		this.scoreText.setFontScale(0.66f);
		this.scoreText.setAlignment(Text.HAlignLeft, Text.VAlignTop);
		this.scoreText.setPadding(10, 10);

		this.hiscoreText = new Text();
		this.hiscoreText.setFontScale(0.66f);
		this.hiscoreText.setAlignment(Text.HAlignRight, Text.VAlignTop);
		this.hiscoreText.setPadding(10, 10);

		this.infoText = new Text();
		this.infoText.setAlignment(Text.HAlignCenter, Text.VAlignCenter);
		this.infoText.setText("Touch To Begin");

		this.helpButton = new HelpButton();
		helpButton.setListener(new IButtonListener() {
			@Override
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.help, false);
			}
		});

		this.resultView = new ResultView(new ResultView.IListener() {
			@Override
			public void onRestartGame() {
				restartGame();
			}
		});
	}

	private void beginPlaying() {
		isPlaying = true;
		canStart = false;
		ball.beginMove();
		score = 0;
		scoreText.setText(score + "");
		view.unregisterDrawable(logo);
		view.unregisterDrawable(infoText);
		view.unregisterDrawable(helpButton);
		helpButton.deactivate();
		TurpClient.sendStat(StatActions.StartPlaying);
	}

	private void endGame() {
		isPlaying = false;
		canStart = false;
		view.unregisterInputListener(listener);
		ball.stopMoving();
		resultView.activate();

		helpButton.activate();
		view.registerDrawable(helpButton, Game.LAYER_INFO);

		view.registerDrawable(resultView, Game.LAYER_INFO);
		Sounds.gameover.play();
		if (score > 15)
			TurpClient.sendScore(score, BallGameMode.defaultMode, null);
	}

	private void restartGame() {
		canStart = true;
		isPlaying = false;
		helpButton.activate();
		view.registerDrawable(helpButton, Game.LAYER_INFO);

		view.registerDrawable(logo, Game.LAYER_INFO);
		view.registerDrawable(infoText, Game.LAYER_INFO);
		BallGameAds.showAd();
		isPlaying = false;
		ball.reset();
		score = 0;
		scoreText.setText(score + "");
		hiscore = Settings.getInteger("hi-score", 0);
		hiscoreText.setText("Hi: " + hiscore);
		resultView.deactivate();
		view.unregisterDrawable(resultView);
		view.registerInputListener(listener);
	}

	private boolean onTouchDown(float x, float y) {
		if (isPlaying) {
			score++;
			scoreText.setText(score + "");
			if (score > hiscore) {
				hiscore = score;
				Settings.putInteger("hi-score", hiscore);
				hiscoreText.setText("Hi: " + hiscore);
			}
			ball.hit(Game.screenToViewportX(x));
			Sounds.hit.play();
		} else if (canStart) {
			if (GameUtils.isIn(x, y, helpButton))
				return false;
			beginPlaying();
		}
		return true;
	}

	public void activate(boolean restartGame) {
		view.registerDrawable(walls, Game.LAYER_GAME);
		view.registerDrawable(ball, Game.LAYER_GAME);
		view.registerDrawable(scoreText, Game.LAYER_INFO);
		view.registerDrawable(hiscoreText, Game.LAYER_INFO);

		if (restartGame) {
			restartGame();
		}
		else {
			resultView.activate();
			helpButton.activate();
		}
	}

	public void deactivate() {
		resultView.deactivate();
		helpButton.deactivate();
	}

	public void update() {
		if (isPlaying && walls.hasCollision(ball)) {
			ball.update();
			endGame();
		}
	}

	private final InputListener listener = new InputListener() {
		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			return onTouchDown(x, y);
		}
	};
}
