package com.turpgames.ballgame.utils;

import com.turpgames.framework.v0.client.ConnectionManager;
import com.turpgames.framework.v0.util.Debug;
import com.turpgames.framework.v0.util.Game;

public class BallGameAds
{

    private static long lastShown = 0L;

    public BallGameAds()
    {
    }

    public static void showAd()
    {
        Debug.println("BallGameAds.showAd");
        if (!ConnectionManager.hasConnection())
        {
            Debug.println("No connection!");
        } else
        {
            long l = System.currentTimeMillis();
            if (l - lastShown > 0x1d4c0L)
            {
                lastShown = l;
                Debug.println("calling Game.showPopUpAd");
                Game.showPopUpAd();
                return;
            }
        }
    }

}
