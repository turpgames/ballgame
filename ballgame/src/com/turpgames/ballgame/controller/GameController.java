package com.turpgames.ballgame.controller;

import com.turpgames.ballgame.components.HelpView;
import com.turpgames.ballgame.components.ResultView;
import com.turpgames.ballgame.objects.Ball;
import com.turpgames.ballgame.objects.Walls;
import com.turpgames.ballgame.utils.BallGameAds;
import com.turpgames.ballgame.utils.Sounds;
import com.turpgames.ballgame.view.IScreenView;
import com.turpgames.framework.v0.client.IShareMessageBuilder;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.InputListener;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rectangle;

public class GameController {
	private final IScreenView view;
	private final Ball ball;
	private final Walls walls;

	private final HelpView help;
	private final Text scoreText;
	private final Text hiscoreText;
	private final Text infoText;
	private final ResultView resultView;
	private int score;

	private boolean isPlaying = false;
	private boolean gameOver = false;
	private int hiscore;

	public GameController(IScreenView view) {
		this.view = view;

		ball = new Ball();
		walls = new Walls();

		this.help = new HelpView();

		this.scoreText = new Text();
		this.scoreText.setFontScale(0.66f);
		this.scoreText.setAlignment(Text.HAlignLeft, Text.VAlignTop);
		this.scoreText.setPadding(Game.scale(10), Game.scale(10));

		this.hiscoreText = new Text();
		this.hiscoreText.setFontScale(0.66f);
		this.hiscoreText.setAlignment(Text.HAlignRight, Text.VAlignTop);
		this.hiscoreText.setPadding(Game.scale(10), Game.scale(10));

		this.infoText = new Text();
		this.infoText.setAlignment(Text.HAlignCenter, Text.VAlignCenter);
		this.infoText.setText("Touch To Begin");

		this.resultView = new ResultView(new ResultView.IListener() {

			@Override
			public void onShowLeadersBoard() {
				System.out.println("Leaders Board");
			}

			@Override
			public void onShareScore() {
				TurpClient.shareScoreOnFacebook(new IShareMessageBuilder() {
					@Override
					public String buildMessage() {
						return "I just made " + score + " hops in Ball Game!";
					}
				});
			}

			@Override
			public void onRestartGame() {
				restartGame();
			}
		});
	}

	private void beginPlaying() {
		isPlaying = true;
		ball.beginMove();
		score = 0;
		scoreText.setText(score + "");
		view.unregisterDrawable(infoText);
		view.unregisterDrawable(help);
	}

	private void endGame() {
		isPlaying = false;
		gameOver = true;
		ball.stopMoving();
		resultView.activate();
		view.unregisterInputListener(listener);
		view.registerDrawable(resultView, Game.LAYER_INFO);
		Sounds.gameover.play();
	}

	private void restartGame() {
		view.registerDrawable(help, Game.LAYER_GAME);
		BallGameAds.showAd();
		gameOver = false;
		isPlaying = false;
		ball.reset();
		score = 0;
		scoreText.setText(score + "");
		hiscore = Settings.getInteger("hi-score", 0);
		hiscoreText.setText("Hi: " + hiscore);
		resultView.deactivate();
		view.unregisterDrawable(resultView);
		view.registerDrawable(infoText, Game.LAYER_GAME);
		view.registerInputListener(listener);
	}

	private boolean onTap() {
		if (gameOver) {
			restartGame();
			return true;
		}
		return false;
	}

	private boolean onTouchDown(float x, float y) {
		if (gameOver) {
			return false;
		}
		else if (!isPlaying) {
			beginPlaying();
		} else {
			score++;
			scoreText.setText(score + "");
			if (score > hiscore) {
				hiscore = score;
				Settings.putInteger("hi-score", hiscore);
				hiscoreText.setText("Hi: " + hiscore);
			}
			ball.hit(Game.screenToViewportX(x));
			Sounds.hit.play();
		}
		return true;
	}

	public void activate() {
		view.registerDrawable(walls, Game.LAYER_GAME);
		view.registerDrawable(ball, Game.LAYER_GAME);
		view.registerDrawable(scoreText, Game.LAYER_INFO);
		view.registerDrawable(hiscoreText, Game.LAYER_INFO);

		restartGame();
	}

	public void deactivate() {
		view.unregisterInputListener(listener);
		view.unregisterDrawable(ball);
		view.unregisterDrawable(walls);
		view.unregisterDrawable(scoreText);
		view.unregisterDrawable(hiscoreText);
		view.unregisterDrawable(resultView);
		resultView.deactivate();
	}

	public void update() {
		if (isPlaying && hasCollision()) {
			ball.update();
			endGame();
		}
	}

	private boolean hasCollision() {
		float x = ball.getLocation().x;
		float y = ball.getLocation().y;
		float s = ball.getSize();

		Rectangle rect = walls.getRect();

		if (x < rect.x) {
			ball.getLocation().x = rect.x;
			return true;
		}
		if (y < rect.y) {
			ball.getLocation().y = rect.y;
			return true;
		}
		if (rect.x + rect.width < x + s) {
			ball.getLocation().x = rect.x + rect.width - s;
			return true;
		}
		if (rect.y + rect.height < y + s) {
			ball.getLocation().y = rect.y + rect.height - s;
			return true;
		}
		return false;
	}

	private final InputListener listener = new InputListener() {
		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			return onTouchDown(x, y);
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			return onTap();
		}
	};
}
