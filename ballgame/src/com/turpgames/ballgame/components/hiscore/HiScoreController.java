package com.turpgames.ballgame.components.hiscore;

import com.turpgames.ballgame.components.BallGameLogo;
import com.turpgames.ballgame.utils.BallGameMode;
import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.client.IServiceCallback;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.impl.TouchSlidingViewSwitcher;
import com.turpgames.framework.v0.util.Debug;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.service.message.GetHiScoresRequest;
import com.turpgames.service.message.GetHiScoresResponse;

public class HiScoreController implements IView {
	private final HiScoreInfo[] hiScoreInfos = new HiScoreInfo[] {
			new HiScoreInfo(GetHiScoresRequest.Today, "Today"),
			new HiScoreInfo(GetHiScoresRequest.LastWeek, "Last Week"),
			new HiScoreInfo(GetHiScoresRequest.LastMonth, "Last Month"),
			new HiScoreInfo(GetHiScoresRequest.AllTime, "All Time")
	};

	private final BallGameLogo logo;
	private final TouchSlidingViewSwitcher viewSwitcher;

	public HiScoreController() {
		viewSwitcher = new TouchSlidingViewSwitcher(false);

		for (HiScoreInfo info : hiScoreInfos)
			viewSwitcher.addView(info.view);

		viewSwitcher.setArea(0, 0, Game.getVirtualWidth(), Game.getVirtualHeight());
		
		logo = new BallGameLogo();
	}

	@Override
	public void draw() {
		logo.draw();
		viewSwitcher.draw();
	}

	@Override
	public String getId() {
		return "LeadersBoardController";
	}

	@Override
	public void activate() {
		loadScores();
		viewSwitcher.activate();
	}

	@Override
	public boolean deactivate() {
		viewSwitcher.deactivate();
		return true;
	}

	private void loadScores() {
		for (final HiScoreInfo info : hiScoreInfos) {
			TurpClient.getHiScores(info.days, GetHiScoresRequest.General, BallGameMode.defaultMode, true, 0, 10, new IServiceCallback<GetHiScoresResponse>() {
				@Override
				public void onSuccess(GetHiScoresResponse response) {
					info.view.bindData(response);
				}
				
				@Override
				public void onFail(Throwable t) {
					Debug.println("getHiscores failed");
				}
			});
		}
	}

	private static class HiScoreInfo {
		final int days;
		final HiScoreView view;

		public HiScoreInfo(int days, String title) {
			this.days = days;
			this.view = new HiScoreView(title);
		}
	}
}
