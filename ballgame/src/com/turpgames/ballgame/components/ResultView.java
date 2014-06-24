package com.turpgames.ballgame.components;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.component.IButtonListener;
import com.turpgames.framework.v0.component.TextButton;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class ResultView implements IDrawable {
    public static interface IListener
    {
        public abstract void onRestartGame();

        public abstract void onShareScore();

        public abstract void onShowLeadersBoard();
    }

    private final TextButton leadersBoardButton;
    private final TextButton restartButton;
    private final TextButton shareScoreButton;

    public ResultView(final IListener listener)
    {
        float y = Game.getVirtualHeight() / 2.0F;
        shareScoreButton = createButton("Share Score", y, new IButtonListener() {
            public void onButtonTapped()
            {
                listener.onShareScore();
            }
        });
        
        restartButton = createButton("Play Again", y - 120F, new IButtonListener() {
            public void onButtonTapped()
            {
                listener.onRestartGame();
            }
        });
        
        leadersBoardButton = createButton("Leaders Board", y + 120F, new IButtonListener() {
            public void onButtonTapped()
            {
                listener.onShowLeadersBoard();
            }
        });
    }

    private static TextButton createButton(String s, float f, IButtonListener ibuttonlistener)
    {
        TextButton textbutton = new TextButton(Color.white(), Color.white());
        textbutton.setListener(ibuttonlistener);
        textbutton.setText(s);
        textbutton.getLocation().set((Game.getVirtualWidth() - textbutton.getWidth()) / 2.0F, f);
        textbutton.deactivate();
        return textbutton;
    }

    public void activate()
    {
        restartButton.activate();
        shareScoreButton.activate();
        leadersBoardButton.activate();
    }

    public void deactivate()
    {
        restartButton.deactivate();
        shareScoreButton.deactivate();
        leadersBoardButton.deactivate();
    }

    public void draw()
    {
        restartButton.draw();
        shareScoreButton.draw();
        leadersBoardButton.draw();
    }
}
