package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.impl.Text;

public class LeadersBoardScreen extends Screen
{

    public LeadersBoardScreen()
    {
    }

    public void init()
    {
        super.init();
        Text text = new Text();
        text.setAlignment(0, 0);
        text.setText("Leaders Board Here");
        registerDrawable(new BallGameLogo(), 100);
        registerDrawable(text, 100);
    }

    protected boolean onBack()
    {
        ScreenManager.instance.switchTo("menu", true);
        return true;
    }
}
