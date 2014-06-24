
package com.turpgames.ballgame.view;

import com.turpgames.ballgame.components.TurpLogo;
import com.turpgames.ballgame.updates.BallGameUpdateManager;
import com.turpgames.framework.v0.IResourceManager;
import com.turpgames.framework.v0.client.ConnectionManager;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;

public class SplashScreen extends Screen
{

    private Color progressColor;
    private IResourceManager resourceManager;

    public SplashScreen()
    {
    }

    private void switchToGame()
    {
        ScreenManager.instance.switchTo("game", false);
    }

    public void draw()
    {
        super.draw();
        float f = (Game.getVirtualWidth() - 50F) * resourceManager.getProgress();
        ShapeDrawer.drawRect((Game.getVirtualWidth() - f) / 2.0F, 100F, f, 20F, progressColor, true, false);
    }

    public void init()
    {
        ConnectionManager.init();
        super.init();
        registerDrawable(new TurpLogo(), 0);
        progressColor = new Color(com.turpgames.ballgame.utils.R.colors.turpYellow);
        resourceManager = Game.getResourceManager();
    }

    public void update()
    {
        if (!resourceManager.isLoading())
        {
            BallGameUpdateManager.runUpdates();
            switchToGame();
        }
    }
}
