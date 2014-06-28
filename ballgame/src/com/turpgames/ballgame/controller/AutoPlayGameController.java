package com.turpgames.ballgame.controller;

import com.turpgames.ballgame.components.GestureTips;
import com.turpgames.ballgame.components.TapImage;
import com.turpgames.ballgame.objects.Ball;
import com.turpgames.ballgame.objects.Walls;
import com.turpgames.ballgame.utils.R;
import com.turpgames.ballgame.utils.Sounds;
import com.turpgames.ballgame.view.IScreenView;
import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.framework.v0.util.Vector;

public class AutoPlayGameController {
	private final IScreenView view;
	private final Ball ball;
	private final Walls walls;
	private final TapImage tapImage;

	private final TextButton startButton;
	private final TextButton backToGameButton;
	private final GestureTips tips;

	private boolean isGameOver;
	private int hopCount;
	private float[] strategy;
	private int strategyIndex = 0;

	public AutoPlayGameController(IScreenView view) {
		this.view = view;
		this.ball = Ball.demoBall();
		this.walls = new Walls();
		this.tapImage = new TapImage();
		this.tips = new GestureTips();

		startButton = createButton("Start Demo", 300, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				startPlaying();
			}
		});

		backToGameButton = createButton("Back To Game", 200, new IButtonListener() {
			@Override
			public void onButtonTapped() {
				ScreenManager.instance.switchTo(R.screens.game, true);
			}
		});
	}

	public void activate() {
		reset();
		ball.reset();
		ball.setY(400f);
		view.registerDrawable(ball, Game.LAYER_GAME);
		view.registerDrawable(walls, Game.LAYER_GAME);
		view.registerDrawable(tapImage, Game.LAYER_GAME);
	}

	public void deactivate() {
		view.unregisterDrawable(ball);
		view.unregisterDrawable(walls);
		view.unregisterDrawable(tapImage);
		view.unregisterDrawable(tips);
		view.unregisterDrawable(startButton);
		view.unregisterDrawable(backToGameButton);
		view.unregisterDrawable(overlay);
	}

	private void startPlaying() {
		ball.reset();
		ball.setY(400f);
		ball.beginMove();
		startButton.deactivate();
		backToGameButton.deactivate();
		view.unregisterDrawable(tips);
		view.unregisterDrawable(startButton);
		view.unregisterDrawable(backToGameButton);
		view.unregisterDrawable(overlay);
		isGameOver = false;
	}

	private void reset() {
		ball.stopMoving();
		hopCount = 0;
		strategy = strategies[strategyIndex++ % strategies.length];
		
		isGameOver = false;
		startButton.activate();
		backToGameButton.activate();
		view.registerDrawable(tips, Game.LAYER_INFO);
		view.registerDrawable(startButton, Game.LAYER_INFO);
		view.registerDrawable(backToGameButton, Game.LAYER_INFO);
		view.registerDrawable(overlay, Game.LAYER_GAME + 1);
	}

	private static TextButton createButton(String text, float y, IButtonListener ibuttonlistener) {
		TextButton textbutton = new TextButton(Color.white(), R.colors.yellow);
		textbutton.setListener(ibuttonlistener);
		textbutton.setText(text);
		textbutton.deactivate();

		setLocation(textbutton, y);

		return textbutton;
	}

	private static void setLocation(TextButton btn, float y) {
		btn.getLocation().set((Game.getVirtualWidth() - btn.getWidth()) / 2.0F, y);
	}

	public void update() {
		if (isGameOver)
			return;

		if (walls.hasCollision(ball)) {
			reset();
			ball.update();
			Sounds.gameover.play();
			isGameOver = true;
			return;
		}

		Vector v = ball.getLocation();

		if (ball.isFallingDown() && hopCount < strategy.length && v.y < strategy[hopCount + 1]) {
			float dx = strategy[hopCount];
			float cx = v.x + ball.getSize() / 2;

			ball.hit(cx + Game.inchesToViewport(dx * 0.004f));

			tapImage.displayAt(cx, v.y, dx);
			hopCount += 2;
			Sounds.hit.play();
		}
	}

	private final IDrawable overlay = new IDrawable() {
		@Override
		public void draw() {
			ShapeDrawer.drawRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight(), R.colors.overlay, true, true);
		}
	};

	private final static float[] touchDown = new float[] {
			-5f, 300,
			10f, 250,
			5f, 225,
			-15f, 200,
			10f, 175,
			-10f, 100,
			15f, 50,
			-20f, 25
	};

	private final static float[] touchUp = new float[] {
			-5f, 200,
			10f, 250,
			5f, 275,
			-20f, 300,
			0f, 375,
			20f, 400,
			-15f, 450,
			20f, 500
	};

	private final static float[] touchLeft = new float[] {
			-10f, 200,
			15f, 250,
			5f, 300,
			-20f, 350,
			0f, 400,
			10f, 300,
			-10f, 100,
			30f, 200
	};

	private final static float[] touchRight = new float[] {
			10f, 200,
			-15f, 250,
			-5f, 300,
			20f, 350,
			0f, 400,
			-10f, 300,
			10f, 100,
			-30f, 200
	};

	private final static float[][] strategies = new float[][] { touchDown, touchUp, touchLeft, touchRight };
}
